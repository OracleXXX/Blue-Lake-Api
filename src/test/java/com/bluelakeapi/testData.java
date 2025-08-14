package com.bluelakeapi;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.bluelakeapi.dto.response.ListTestDateResponse;
import com.bluelakeapi.manager.test.TestDataManager;
import com.bluelakeapi.model.test.TestDataDO;
import org.junit.Test;

import javax.annotation.Resource;

public class testData extends BaseTest{

    @Resource
    TestDataManager testDataManager;

    @Test
    public void test(){
        System.out.println(testDataManager.listTestData(1, 10));
    }

    @Test
    public void LambdaTest (){
        LambdaUpdateWrapper<TestDataDO> wrapper = Wrappers.lambdaUpdate(TestDataDO.class);
        wrapper.set(TestDataDO::getName, "test");
        System.out.println(wrapper.getSqlSet());
    }
}
