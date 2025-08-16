package com.bluelakeapi.dto.request;

import lombok.Data;

@Data
public class DeleteRolePermissionRequest {

    private Long roleId;

    private Long permissionId;

}
