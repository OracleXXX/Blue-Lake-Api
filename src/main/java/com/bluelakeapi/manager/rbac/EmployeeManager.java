package com.bluelakeapi.manager.rbac;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.bluelakeapi.constant.DbConstant;
import com.bluelakeapi.dao.BaseDAO;
import com.bluelakeapi.dao.rbac.EmployeeMapper;
import com.bluelakeapi.model.rbac.EmployeeDO;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 * 员工表 服务类
 * </p>
 *
 * @author Auto Generate
 * @since 2025-08-15
 */
@Component
public class EmployeeManager extends BaseDAO<EmployeeMapper, EmployeeDO> {
    /**
     * @param nickname    员工昵称
     * @param employeeIds 员工id
     * @param pageNumber  页码
     * @param pageSize    每页数量
     * @return PageInfo<EmployeeDO>
     */
    public PageInfo<EmployeeDO> listByNickNameAndIdIn(String nickname, List<String> employeeIds, Integer pageNumber, Integer pageSize) {
        QueryWrapper<EmployeeDO> queryWrapper = newQueryWrapper();
        queryWrapper.lambda()
                .like(StringUtils.isNotBlank(nickname), EmployeeDO::getNickname, nickname)
                .in(CollectionUtils.isNotEmpty(employeeIds), EmployeeDO::getEmployeeId, employeeIds)
                .eq(EmployeeDO::getIsDelete, DbConstant.IS_NOT_DEL)
                .orderByAsc(EmployeeDO::getNickname);
        return selectPage(queryWrapper, pageNumber, pageSize);
    }

    /**
     * @param email 邮箱/用户名
     * @return EmployeeDO
     */
    public EmployeeDO findByEmail(String email) {
        QueryWrapper<EmployeeDO> queryWrapper = newQueryWrapper();
        queryWrapper.lambda()
                .eq(EmployeeDO::getEmail, email)
                .eq(EmployeeDO::getIsDelete, DbConstant.IS_NOT_DEL)
                .last("LIMIT 1");
        return selectOne(queryWrapper);
    }

    /**
     * @param employeeId 员工uid
     * @return EmployeeDO
     */
    public EmployeeDO findByEmployeeId(String employeeId) {
        QueryWrapper<EmployeeDO> queryWrapper = newQueryWrapper();
        queryWrapper.lambda()
                .eq(EmployeeDO::getEmployeeId, employeeId)
                .eq(EmployeeDO::getIsDelete, DbConstant.IS_NOT_DEL)
                .last("LIMIT 1");
        return selectOne(queryWrapper);
    }

    /**
     * @param employeeId 员工uid
     * @param email      邮箱/用户名
     * @return EmployeeDO
     */
    public EmployeeDO findByEmployeeIdAndEmail(String employeeId, String email) {
        QueryWrapper<EmployeeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(EmployeeDO::getEmployeeId, employeeId)
                .eq(EmployeeDO::getEmail, email)
                .eq(EmployeeDO::getIsDelete, DbConstant.IS_NOT_DEL)
                .last("LIMIT 1");
        return selectOne(queryWrapper);
    }

    /**
     * @param employeeId 员工uid
     */
    public void logicDeleteByEmployeeId(String employeeId) {
        UpdateWrapper<EmployeeDO> updateWrapper = newUpdateWrapper();
        updateWrapper.lambda()
                .eq(EmployeeDO::getEmployeeId, employeeId)
                .set(EmployeeDO::getIsDelete, DbConstant.IS_DEL);
        update(updateWrapper, new EmployeeDO());
    }

    // List<EmployeeV1Do> findAllByIdIn(List<Long> employeeIdList);

    /**
     * @param employeeIds 员工uid 列表
     * @return List<EmployeeDO>
     */
    public List<EmployeeDO> findALlByIdIn(List<String> employeeIds) {
        QueryWrapper<EmployeeDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .in(CollectionUtils.isNotEmpty(employeeIds), EmployeeDO::getEmployeeId, employeeIds)
                .eq(EmployeeDO::getIsDelete, DbConstant.IS_NOT_DEL)
                .orderByAsc(EmployeeDO::getNickname);
        return selectList(queryWrapper);
    }

}
