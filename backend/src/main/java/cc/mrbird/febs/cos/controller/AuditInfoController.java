package cc.mrbird.febs.cos.controller;


import cc.mrbird.febs.common.utils.R;
import cc.mrbird.febs.cos.entity.AuditInfo;
import cc.mrbird.febs.cos.entity.StaffInfo;
import cc.mrbird.febs.cos.entity.UserInfo;
import cc.mrbird.febs.cos.service.IAuditInfoService;
import cc.mrbird.febs.cos.service.IStaffInfoService;
import cc.mrbird.febs.cos.service.IUserInfoService;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author FanK fan1ke2ke@gmail.com
 */
@RestController
@RequestMapping("/cos/audit-info")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuditInfoController {

    private final IAuditInfoService auditInfoService;

    private final IUserInfoService userInfoService;

    private final IStaffInfoService staffInfoService;

    /**
     * 分页获取审核信息
     *
     * @param page      分页对象
     * @param auditInfo 审核信息
     * @return 结果
     */
    @GetMapping("/page")
    public R page(Page<AuditInfo> page, AuditInfo auditInfo) {
        return R.ok(auditInfoService.queryAuditPage(page, auditInfo));
    }

    /**
     * 审核
     *
     * @param auditId 审核ID
     * @param type    状态
     * @return 结果
     */
    @PutMapping("/check")
    @Transactional(rollbackFor = Exception.class)
    public R check(@RequestParam Integer auditId, @RequestParam Integer type) {
        // 更新用户审核信息
        AuditInfo auditInfo = auditInfoService.getById(auditId);
        UserInfo userInfo = userInfoService.getById(auditInfo.getUserId());

        auditInfo.setAuditStatus(type);
        if (type == 1) {
            userInfo.setType("2");
            userInfoService.updateById(userInfo);
        }

        // 添加员工信息
        if (type == 1) {
            StaffInfo staffInfo = new StaffInfo();
            staffInfo.setCode("STF-" + System.currentTimeMillis());
            staffInfo.setName(userInfo.getName());
            staffInfo.setPhone(userInfo.getPhone());
            staffInfo.setUserId(userInfo.getId());
            staffInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
            staffInfo.setImages(userInfo.getImages());
            staffInfo.setStatus(1);
            staffInfo.setSex(Integer.valueOf(userInfo.getSex()));
            staffInfoService.save(staffInfo);
        }
        // 更新审核时间
        auditInfo.setStatusDate(DateUtil.formatDateTime(new Date()));
        return R.ok(auditInfoService.updateById(auditInfo));
    }

    /**
     * 获取ID获取审核详情
     *
     * @param id 主键
     * @return 结果
     */
    @GetMapping("/{id}")
    public R detail(@PathVariable("id") Integer id) {
        return R.ok(auditInfoService.getById(id));
    }

    /**
     * 获取审核信息列表
     *
     * @return 结果
     */
    @GetMapping("/list")
    public R list() {
        return R.ok(auditInfoService.list());
    }

    /**
     * 新增审核信息
     *
     * @param auditInfo 审核信息
     * @return 结果
     */
    @PostMapping
    public R save(AuditInfo auditInfo) {
        // 设置所属用户
        auditInfo.setCreateDate(DateUtil.formatDateTime(new Date()));
        auditInfo.setAuditStatus(0);
        return R.ok(auditInfoService.save(auditInfo));
    }

    /**
     * 修改审核信息
     *
     * @param auditInfo 审核信息
     * @return 结果
     */
    @PutMapping
    public R edit(AuditInfo auditInfo) {
        return R.ok(auditInfoService.updateById(auditInfo));
    }

    /**
     * 删除审核信息
     *
     * @param ids ids
     * @return 审核信息
     */
    @DeleteMapping("/{ids}")
    public R deleteByIds(@PathVariable("ids") List<Integer> ids) {
        return R.ok(auditInfoService.removeByIds(ids));
    }
}
