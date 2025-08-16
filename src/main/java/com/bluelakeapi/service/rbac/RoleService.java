package com.bluelakeapi.service.rbac;


import com.bluelakeapi.dto.request.AssignAllPermissionRequest;
import com.bluelakeapi.dto.request.AssignEmployeeRequest;
import com.bluelakeapi.dto.request.AssignPermissionRequest;
import com.bluelakeapi.dto.request.RoleRequest;
import com.bluelakeapi.dto.response.*;
import org.springframework.stereotype.Service;

@Service
public interface RoleService {

    GetRoleResponse get(Long id);

    ListRoleResponse list();

    SuccessResponse create(RoleRequest roleRequest);

    SuccessResponse update(RoleRequest roleRequest);

    SuccessResponse delete(Long id);

    ListPermissionResponse listRolePermissions(Long roleId);

    SuccessResponse assignPermissions(AssignPermissionRequest assignPermissionRequest);

    SuccessResponse assignAllPermissions(AssignAllPermissionRequest assignAllPermissionRequest);

    SuccessResponse deleteRolePermission(Long rolePermissionId);

    ListEmployeeResponse listRoleEmployees(Long roleId);

    SuccessResponse assignEmployees(AssignEmployeeRequest assignEmployeeRequest);

    SuccessResponse deleteRoleEmployee(Long roleEmployeeId);


}
