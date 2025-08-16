package com.bluelakeapi.service.rbac;


import com.bluelakeapi.dto.request.BusinessRequest;
import com.bluelakeapi.dto.request.ListBusinessRequest;
import com.bluelakeapi.dto.response.GetBusinessResponse;
import com.bluelakeapi.dto.response.ListBusinessResponse;
import com.bluelakeapi.dto.response.SuccessResponse;
import org.springframework.stereotype.Service;

@Service
public interface BusinessService {

    ListBusinessResponse listBusiness(ListBusinessRequest listBusinessRequest);

    GetBusinessResponse getBusiness(String name);

    SuccessResponse CreateBusiness(BusinessRequest businessRequest);

    SuccessResponse UpdateBusiness(BusinessRequest businessRequest);

    SuccessResponse DeleteBusiness(String name);

}
