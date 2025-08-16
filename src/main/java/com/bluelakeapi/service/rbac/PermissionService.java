package com.bluelakeapi.service.rbac;


import com.bluelakeapi.dto.request.ListPermissionRequest;
import com.bluelakeapi.dto.request.PermissionRequest;
import com.bluelakeapi.dto.response.ListPermissionGroupByBusinessResponse;
import com.bluelakeapi.dto.response.ListPermissionResponse;
import com.bluelakeapi.dto.response.SuccessResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PermissionService {

    ListPermissionResponse listByBusinessId(Long businessId);

    ListPermissionResponse list(ListPermissionRequest listPermissionRequest);

    SuccessResponse create(PermissionRequest createPermissionRequest);

    //SuccessResponse updateSortNum(Long id, UpdateSortNumRequest updateSortNumRequest);

    SuccessResponse delete(Long id);

    boolean checkPermission(Long employeeId, String businessName, String action);

    ListPermissionGroupByBusinessResponse listPermissionsGroupByBusiness();

    List<String> listAdminUidList();

}
