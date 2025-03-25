package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.AuditInfo;
import cc.mrbird.febs.cos.entity.OrderInfo;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.IAuditInfoService;
import cc.mrbird.febs.cos.service.IOrderInfoService;
import cc.mrbird.febs.cos.service.IStaffInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import cc.mrbird.febs.system.service.UserService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
@RestController
@RequestMapping("/cos/staff-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StaffInfoController {

    private final IStaffInfoService staffInfoService;

    private final IOrderInfoService orderInfoService;

    private final IUserInfoService userInfoService1;

    private final UserService userInfoService;

    private final IAuditInfoService auditInfoService;

    /**
     * 分页获取员工信息
     *
     * @param page      分页对象
     * @param staffInfo 员工信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<StaffInfo> page, StaffInfo staffInfo) {
        return R.ok(staffInfoService.selectStaffPage(page, staffInfo));
    }

    /**
     * 获取员工评价
     *
     * @return 结果
     */
    @GetMapping("/evaluate")
    public R evaluate() {
        return R.ok(staffInfoService.evaluate());
    }

    /**
     * 更新员工上下线状态
     *
     * @param staffId 员工ID
     * @param status  上下线状态
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @GetMapping("/onPutFlag")
    public R onPutFlag(Integer staffId, Integer status) {
        StaffInfo staffInfo = staffInfoService.getById(staffId);
        staffInfo.setStatus(status);
        staffInfoService.updateById(staffInfo);
        // 更新用户类型
        if (status == 1) {
            userInfoService1.update(Wrappers.<UserInfo>lambdaUpdate().set(UserInfo::getType, 2));
            auditInfoService.update(Wrappers.<AuditInfo>lambdaUpdate().set(AuditInfo::getAuditStatus, 1).eq(AuditInfo::getUserId, staffInfo.getUserId()));
        } else {
            userInfoService1.update(Wrappers.<UserInfo>lambdaUpdate().set(UserInfo::getType, 1));
            auditInfoService.update(Wrappers.<AuditInfo>lambdaUpdate().set(AuditInfo::getAuditStatus, 2).eq(AuditInfo::getUserId, staffInfo.getUserId()));
        }
        return R.ok(true);
    }

    /**
     * 获取ID获取员工详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(staffInfoService.getById(id));
    }

    /**
     * 获取ID获取员工详情
     *
     * @param userId 用户ID
     * @return 结果
     */
    @GetMapping("/staff/{id}")
    public R detailByStaff(@PathVariable("id") Integer userId) {
        LinkedHashMap<String, Object> result = new LinkedHashMap<String, Object>() {
            {
                put("staff", null);
                put("order", Collections.emptyList());
            }
        };
        StaffInfo staffInfo = staffInfoService.getOne(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getUserId, userId));
        result.put("staff", staffInfo);
        result.put("order", orderInfoService.list(Wrappers.<OrderInfo>lambdaQuery().eq(OrderInfo::getStaffIds, staffInfo.getId())));
        return R.ok(result);
    }

    /**
     * 获取ID获取员工详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/detail/{id}")
    public R detailByUserId(@PathVariable("id") Integer id) {
        return R.ok(staffInfoService.getOne(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getUserId, id)));
    }

    /**
     * 获取员工信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(staffInfoService.list());
    }

    /**
     * 获取在职员工信息列表
     *
     * @return 结果
     */
    @GetMapping("/list/online")
    public R listByOnline() {
        return R.ok(staffInfoService.list(Wrappers.<StaffInfo>lambdaQuery().eq(StaffInfo::getStatus, 1)));
    }

    /**
     * 新增员工信息
     *
     * @param staffInfo 员工信息
     * @return 结果
     */
    @PostMapping
    public R save(StaffInfo staffInfo) throws Exception {
        staffInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        staffInfo.setCode("STF-" + System.currentTimeMillis());
        userInfoService.registMerchant(staffInfo.getCode(), "1234qwer", staffInfo);
        return R.ok(true);
    }

    /**
     * 修改员工信息
     *
     * @param staffInfo 员工信息
     * @return 结果
     */
    @PutMapping
    public R edit(StaffInfo staffInfo) {
        return R.ok(staffInfoService.updateById(staffInfo));
    }

    /**
     * 删除员工信息
     *
     * @param ids ids
     * @return 员工信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(staffInfoService.removeByIds(ids));
    }

}
