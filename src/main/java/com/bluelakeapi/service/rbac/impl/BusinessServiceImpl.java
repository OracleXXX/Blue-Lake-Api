package com.bluelakeapi.service.rbac.impl;

import com.bluelakeapi.dto.request.BusinessRequest;
import com.bluelakeapi.dto.request.ListBusinessRequest;
import com.bluelakeapi.dto.response.GetBusinessResponse;
import com.bluelakeapi.dto.response.ListBusinessResponse;
import com.bluelakeapi.dto.response.SuccessResponse;
import com.bluelakeapi.service.rbac.BusinessService;
import org.springframework.stereotype.Service;

@Service
public class BusinessServiceImpl implements BusinessService {
    @Override
    public ListBusinessResponse listBusiness(ListBusinessRequest listBusinessRequest) {
        return null;
    }

    @Override
    public GetBusinessResponse getBusiness(String name) {
        return null;
    }

    @Override
    public SuccessResponse CreateBusiness(BusinessRequest businessRequest) {
        return null;
    }

    @Override
    public SuccessResponse UpdateBusiness(BusinessRequest businessRequest) {
        return null;
    }

    @Override
    public SuccessResponse DeleteBusiness(String name) {
        return null;
    }
}
