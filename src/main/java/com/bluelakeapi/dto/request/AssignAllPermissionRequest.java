package com.bluelakeapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class AssignAllPermissionRequest {

    @NotNull(message = "role id must be not blank")
    private Long roleId;

    @NotNull(message = "permission ids must be not blank")
    private List<Long> permissionIds;

}
