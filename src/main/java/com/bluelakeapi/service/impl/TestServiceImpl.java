package com.bluelakeapi.service.impl;

import com.bluelakeapi.dto.bo.TestDataDto;
import com.bluelakeapi.dto.request.ListTestDataRequest;
import com.bluelakeapi.dto.response.ListTestDateResponse;
import com.bluelakeapi.manager.test.TestDataManager;
import com.bluelakeapi.model.test.TestDataDO;
import com.bluelakeapi.service.TestService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TestServiceImpl implements TestService {

    @Resource
    private TestDataManager testDataManager;

    @Override
    public ListTestDateResponse listTestData(ListTestDataRequest listTestDataRequest) {
        ListTestDateResponse listTestDateResponse = new ListTestDateResponse();
        PageInfo<TestDataDO> testDataDtoPageInfo = testDataManager.listTestData(listTestDataRequest.getPageNumber(), listTestDataRequest.getPageSize());
       // List<TestDataDO> testDataDtoPageInfo = testDataManager.listTestData(listTestDataRequest.getPageNumber(), listTestDataRequest.getPageSize());
        listTestDateResponse.setTestDatas(testDataDtoPageInfo.getList().stream().map(x -> {
            TestDataDto testDataDto = new TestDataDto();
            testDataDto.setId(x.getId());
            testDataDto.setName(x.getName());
            return testDataDto;

        }).collect(Collectors.toList()));
        listTestDateResponse.setPageNumber(listTestDataRequest.getPageNumber());
        listTestDateResponse.setPageSize(listTestDataRequest.getPageSize());

        return listTestDateResponse;
    }
}
