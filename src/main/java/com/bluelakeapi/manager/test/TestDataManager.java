package com.bluelakeapi.manager.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bluelakeapi.constant.DbConstant;
import com.bluelakeapi.dao.BaseDAO;
import com.bluelakeapi.dao.test.TestDataMapper;
import com.bluelakeapi.model.test.TestDataDO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 测试表 服务类
 * </p>
 *
 * @author Auto Generate
 * @since 2025-08-13
 */
@Component
public class TestDataManager extends BaseDAO<TestDataMapper, TestDataDO> {

    /**
     * @param id
     */
    public void logicDeleteById(Long id) {
        UpdateWrapper<TestDataDO> updateWrapper = newUpdateWrapper();
        updateWrapper.lambda()
                .eq(TestDataDO::getId, id)
                .set(TestDataDO::getIsDelete, DbConstant.IS_DEL);
        update(updateWrapper, new TestDataDO());
    }

    /**
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public PageInfo<TestDataDO> listTestData(Integer pageNumber, Integer pageSize) {
        QueryWrapper<TestDataDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .eq(TestDataDO::getIsDelete, DbConstant.IS_NOT_DEL);

        return selectPage(queryWrapper, pageNumber, pageSize);
    }

}
