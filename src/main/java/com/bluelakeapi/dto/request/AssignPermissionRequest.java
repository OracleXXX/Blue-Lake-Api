package com.bluelakeapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class AssignPermissionRequest {

    @Min(1)
    private Long roleId;

    @Min(1)
    private Long permissionId;

}
