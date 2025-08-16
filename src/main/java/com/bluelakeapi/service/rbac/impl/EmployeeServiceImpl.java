package com.bluelakeapi.service.rbac.impl;

import com.bluelakeapi.dto.request.*;
import com.bluelakeapi.dto.response.*;
import com.bluelakeapi.service.rbac.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Override
    public ListEmployeeResponse list(ListEmployeeRequest listEmployeeRequest) {
        return null;
    }

    @Override
    public GetEmployeeResponse get(Long employeeId) {
        return null;
    }

    @Override
    public SuccessResponse create(EmployeeRequest employeeRequest) {
        return null;
    }

    @Override
    public SuccessResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
        return null;
    }

    @Override
    public SuccessResponse update(EmployeeRequest employeeRequest) {
        return null;
    }

    @Override
    public SuccessResponse delete(Long id) {
        return null;
    }

    @Override
    public ListRoleResponse listEmployeeRoles(Long employeeId) {
        return null;
    }

    @Override
    public Integer getLevel(Long employeeId) {
        return null;
    }

    @Override
    public SuccessResponse assignRoles(AssignRoleRequest assignRoleRequest) {
        return null;
    }

    @Override
    public ListPermissionResponse listEmployeePermissions(Long employeeId) {
        return null;
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        return null;
    }
}
