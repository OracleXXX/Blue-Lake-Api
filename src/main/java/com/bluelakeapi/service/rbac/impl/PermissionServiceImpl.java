package com.bluelakeapi.service.rbac.impl;

import com.bluelakeapi.dto.request.ListPermissionRequest;
import com.bluelakeapi.dto.request.PermissionRequest;
import com.bluelakeapi.dto.response.ListPermissionGroupByBusinessResponse;
import com.bluelakeapi.dto.response.ListPermissionResponse;
import com.bluelakeapi.dto.response.SuccessResponse;
import com.bluelakeapi.service.rbac.PermissionService;

import java.util.List;

public class PermissionServiceImpl implements PermissionService {
    @Override
    public ListPermissionResponse listByBusinessId(Long businessId) {
        return null;
    }

    @Override
    public ListPermissionResponse list(ListPermissionRequest listPermissionRequest) {
        return null;
    }

    @Override
    public SuccessResponse create(PermissionRequest createPermissionRequest) {
        return null;
    }

    @Override
    public SuccessResponse delete(Long id) {
        return null;
    }

    @Override
    public boolean checkPermission(Long employeeId, String businessName, String action) {
        return false;
    }

    @Override
    public ListPermissionGroupByBusinessResponse listPermissionsGroupByBusiness() {
        return null;
    }

    @Override
    public List<String> listAdminUidList() {
        return null;
    }
}
