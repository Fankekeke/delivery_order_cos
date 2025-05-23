package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.common.exception.FebsException;
import cc.mrbird.febs.cos.entity.DiscountInfo;
import cc.mrbird.febs.cos.entity.OrderInfo;
import cc.mrbird.febs.cos.entity.WithdrawInfo;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author FanK
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    /**
     * 分页获取订单信息
     *
     * @param page      分页对象
     * @param orderInfo 订单信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectOrderPage(Page<OrderInfo> page, OrderInfo orderInfo);

    /**
     * 未审核订单列表
     *
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> notCheckList();

    /**
     * 根据用户ID查询订单信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryOrderByUserId(Integer userId);

    /**
     * 获取配送员所有订单
     *
     * @param userId 配送员ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryOrderListByStaffId(Integer userId);

    /**
     * 获取订单付款信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    OrderInfo getPriceTotal(OrderInfo orderInfo) throws FebsException;

    /**
     * 员工接单
     *
     * @param orderId 订单ID
     * @param staffId 员工ID
     * @return 结果
     */
    boolean checkOrder(Integer orderId, Integer staffId);

    /**
     * 查询收益记录详情
     *
     * @param incomeId 记录ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectIncomeDetail(Integer incomeId);

    /**
     * 提现记录详情
     *
     * @param withdrawId 提现记录ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectWithdrawDetail(Integer withdrawId);

    /**
     * 添加订单信息
     *
     * @param orderInfo 订单信息
     * @return 结果
     */
    boolean saveOrder(OrderInfo orderInfo);

    /**
     * 订单收货
     *
     * @param orderId 订单编号
     * @return 结果
     */
    boolean auditOrderFinish(Integer orderId);

    /**
     * 订单支付
     *
     * @param orderCode 订单编号
     * @return 结果
     */
    boolean orderPay(String orderCode);

    /**
     * 管理员审核提现申请
     *
     * @param withdrawInfo 提现记录
     * @return 结果
     */
    boolean auditWithdraw(WithdrawInfo withdrawInfo) throws FebsException;

    /**
     * 根据用户ID获取优惠券
     *
     * @param userId 用户ID
     * @return 结果
     */
    List<DiscountInfo> selectDiscountByUser(Integer userId);

    /**
     * 获取ID获取评价详情
     *
     * @param id 主键
     * @return 结果
     */
    LinkedHashMap<String, Object> selectEvaluate(Integer id);

    /**
     * 获取ID获取订单详情
     *
     * @param id 主键
     * @return 结果
     */
    LinkedHashMap<String, Object> selectOrderDetail(Integer id);

    /**
     * 员工获取统计信息
     *
     * @param userId 员工ID
     * @return 结果
     */
    LinkedHashMap<String, Object> selectHomeDataByMerchant(Integer userId);

    /**
     * 员工获取推荐订单
     *
     * @param longitude 经度
     * @param latitude  纬度
     * @param userId    员工ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryOrderRecommend(BigDecimal longitude, BigDecimal latitude, Integer userId);

    /**
     * 根据用户ID获取主页信息
     *
     * @param userId 用户ID
     * @return 结果
     */
    LinkedHashMap<String, Object> queryHomeByUserId(BigDecimal longitude, BigDecimal latitude, Integer userId);

    /**
     * 员工获取订单信息
     *
     * @param staffId 员工ID
     * @return 结果
     */
    List<LinkedHashMap<String, Object>> queryOrderByStaff(Integer staffId);

    /**
     * 管理员获取主页统计数据
     *
     * @return 结果
     */
    LinkedHashMap<String, Object> homeDataByAdmin();
}
