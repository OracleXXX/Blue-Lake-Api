package com.bluelakeapi.service.rbac;


import com.bluelakeapi.dto.request.*;
import com.bluelakeapi.dto.response.*;
import org.springframework.stereotype.Service;

@Service
public interface EmployeeService {

    ListEmployeeResponse list(ListEmployeeRequest listEmployeeRequest);

    GetEmployeeResponse get(Long employeeId);

    SuccessResponse create(EmployeeRequest employeeRequest);

    SuccessResponse resetPassword(ResetPasswordRequest resetPasswordRequest);

    SuccessResponse update(EmployeeRequest employeeRequest);

    SuccessResponse delete(Long id);

    ListRoleResponse listEmployeeRoles(Long employeeId);

    Integer getLevel(Long employeeId);

    SuccessResponse assignRoles(AssignRoleRequest assignRoleRequest);

    // SuccessResponse assignLibaUid(AssignLibaUidRequest assignLibaUidRequest);

    // SuccessResponse removeLibaUid(RemoveLibaUidRequest removeLibaUidRequest);

    ListPermissionResponse listEmployeePermissions(Long employeeId);

    LoginResponse login(LoginRequest loginRequest);


}
