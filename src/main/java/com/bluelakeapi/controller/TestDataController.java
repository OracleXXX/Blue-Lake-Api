package com.bluelakeapi.controller;

import com.bluelakeapi.dto.CommonResponse;
import com.bluelakeapi.dto.request.ListTestDataRequest;
import com.bluelakeapi.dto.response.ListTestDateResponse;
import com.bluelakeapi.service.TestService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admin/testData")

public class TestDataController {
    @Resource
    private TestService testService;

    @GetMapping
    public CommonResponse<ListTestDateResponse> listTestData(ListTestDataRequest request) {

        return CommonResponse.getSuccessResult(testService.listTestData(request));
    }

}
