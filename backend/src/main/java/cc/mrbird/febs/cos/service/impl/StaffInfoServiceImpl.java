package cc.mrbird.febs.cos.service.impl;

import cc.mrbird.febs.cos.dao.OrderInfoMapper;
import cc.mrbird.febs.cos.entity.EvaluateInfo;
import cc.mrbird.febs.cos.entity.OrderInfo;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.dao.StaffInfoMapper;
import cc.mrbird.febs.cos.service.IEvaluateInfoService;
import cc.mrbird.febs.cos.service.IOrderInfoService;
import cc.mrbird.febs.cos.service.IStaffInfoService;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author FanK
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StaffInfoServiceImpl extends ServiceImpl<StaffInfoMapper, StaffInfo> implements IStaffInfoService {

    private final OrderInfoMapper orderInfoService;

    private final IEvaluateInfoService evaluateInfoService;

    /**
     * 分页获取员工信息
     *
     * @param page      分页对象
     * @param staffInfo 员工信息
     * @return 结果
     */
    @Override
    public IPage<LinkedHashMap<String, Object>> selectStaffPage(Page<StaffInfo> page, StaffInfo staffInfo) {
        return baseMapper.selectStaffPage(page, staffInfo);
    }

    /**
     * 获取员工评价
     *
     * @return 结果
     */
    @Override
    public List<StaffInfo> evaluate() {
        // 返回数据
        List<StaffInfo> staffInfoList = this.list(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getStatus, 1));
        // 订单评价
        List<EvaluateInfo> evaluateInfoList = evaluateInfoService.list();
        Map<Integer, BigDecimal> scoreMap = evaluateInfoList.stream().collect(Collectors.toMap(EvaluateInfo::getOrderId, EvaluateInfo::getScore));
        // 订单信息
        List<OrderInfo> orderInfoList = orderInfoService.selectList(Wrappers.<OrderInfo>lambdaQuery().eq(OrderInfo::getStatus, 3));
        Map<String, List<OrderInfo>> orderInfoMap = orderInfoList.stream().collect(Collectors.groupingBy(OrderInfo::getStaffIds));
        for (StaffInfo staffInfo : staffInfoList) {
            // 根据用户获取订单
            if (CollectionUtil.isEmpty(orderInfoMap)) {
                staffInfo.setOrderNum(0);
                staffInfo.setScore(null);
            } else {
                List<OrderInfo> currentOrderInfoList = orderInfoMap.get(staffInfo.getId().toString());
                if (CollectionUtil.isEmpty(currentOrderInfoList)) {
                    staffInfo.setOrderNum(0);
                    staffInfo.setScore(null);
                } else {
                    // 获取订单ID
                    List<Integer> orderIdList = currentOrderInfoList.stream().map(OrderInfo::getId).collect(Collectors.toList());
                    if (CollectionUtil.isEmpty(scoreMap)) {
                        staffInfo.setScore(null);
                    } else {
                        BigDecimal totalScore = BigDecimal.ZERO;
                        for (Integer integer : orderIdList) {
                            totalScore = NumberUtil.add(totalScore, scoreMap.get(integer) == null ? BigDecimal.ZERO : scoreMap.get(integer));
                        }
                        if (totalScore.compareTo(BigDecimal.ZERO) == 0) {
                            staffInfo.setScore(null);
                        } else {
                            staffInfo.setOrderNum(currentOrderInfoList.size());
                            staffInfo.setScore(NumberUtil.div(totalScore, staffInfo.getOrderNum()));
                        }
                    }
                }
            }
        }
        return staffInfoList;
    }
}
