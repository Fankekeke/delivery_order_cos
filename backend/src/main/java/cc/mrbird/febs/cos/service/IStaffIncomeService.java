package cc.mrbird.febs.cos.service;

import cc.mrbird.febs.cos.entity.StaffIncome;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.LinkedHashMap;

/**
 * @author FanK
 */
public interface IStaffIncomeService extends IService<StaffIncome> {

    /**
     * 分页获取公告信息
     *
     * @param page        分页对象
     * @param staffIncome 公告信息
     * @return 结果
     */
    IPage<LinkedHashMap<String, Object>> selectStaffIncomePage(Page<StaffIncome> page, StaffIncome staffIncome);
}
