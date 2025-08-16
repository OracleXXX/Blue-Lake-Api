package com.bluelakeapi.manager.rbac;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bluelakeapi.constant.DbConstant;
import com.bluelakeapi.dao.BaseDAO;
import com.bluelakeapi.dao.rbac.BusinessMapper;
import com.bluelakeapi.model.rbac.BusinessDO;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 业务表 服务类
 * </p>
 *
 * @author Auto Generate
 * @since 2025-08-15
 */
@Component
public class BusinessManager extends BaseDAO<BusinessMapper, BusinessDO> {
    /**
     * @param name business name
     * @return Business DO
     */
    public BusinessDO getByName(String name) {
        QueryWrapper<BusinessDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(BusinessDO::getName, name)
                .eq(BusinessDO::getIsDelete, DbConstant.IS_NOT_DEL)
                .last("LIMIT 1");
        return selectOne(queryWrapper);
    }

    /**
     * @param displayName 业务展示名称
     * @param pageNumber 页码
     * @param pageSize 每页条数
     * @return PageInfo<BusinessDO>
     */
    public PageInfo<BusinessDO> list(String displayName, Integer pageNumber, Integer pageSize) {
        QueryWrapper<BusinessDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(StringUtils.isNotBlank(displayName), BusinessDO::getDisplayName, displayName)
                .eq(BusinessDO::getIsDelete, DbConstant.IS_NOT_DEL)
                .orderByDesc(BusinessDO::getUpdateTime);
        return selectPage(queryWrapper, pageNumber, pageSize);
    }


}
