package com.bluelakeapi.service.rbac.impl;

import com.bluelakeapi.dto.request.AssignAllPermissionRequest;
import com.bluelakeapi.dto.request.AssignEmployeeRequest;
import com.bluelakeapi.dto.request.AssignPermissionRequest;
import com.bluelakeapi.dto.request.RoleRequest;
import com.bluelakeapi.dto.response.*;
import com.bluelakeapi.service.rbac.RoleService;

public class RoleServiceImpl implements RoleService {
    @Override
    public GetRoleResponse get(Long id) {
        return null;
    }

    @Override
    public ListRoleResponse list() {
        return null;
    }

    @Override
    public SuccessResponse create(RoleRequest roleRequest) {
        return null;
    }

    @Override
    public SuccessResponse update(RoleRequest roleRequest) {
        return null;
    }

    @Override
    public SuccessResponse delete(Long id) {
        return null;
    }

    @Override
    public ListPermissionResponse listRolePermissions(Long roleId) {
        return null;
    }

    @Override
    public SuccessResponse assignPermissions(AssignPermissionRequest assignPermissionRequest) {
        return null;
    }

    @Override
    public SuccessResponse assignAllPermissions(AssignAllPermissionRequest assignAllPermissionRequest) {
        return null;
    }

    @Override
    public SuccessResponse deleteRolePermission(Long rolePermissionId) {
        return null;
    }

    @Override
    public ListEmployeeResponse listRoleEmployees(Long roleId) {
        return null;
    }

    @Override
    public SuccessResponse assignEmployees(AssignEmployeeRequest assignEmployeeRequest) {
        return null;
    }

    @Override
    public SuccessResponse deleteRoleEmployee(Long roleEmployeeId) {
        return null;
    }
}
