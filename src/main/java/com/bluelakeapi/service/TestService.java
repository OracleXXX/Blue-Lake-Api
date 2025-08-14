package com.bluelakeapi.service;

import com.bluelakeapi.dto.request.ListTestDataRequest;
import com.bluelakeapi.dto.response.ListTestDateResponse;
import org.springframework.stereotype.Service;


public interface TestService {
    ListTestDateResponse listTestData(ListTestDataRequest listTestDataRequest);
}
