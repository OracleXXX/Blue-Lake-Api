package com.bluelakeapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DeleteRoleEmployeeRequest {

    @NotBlank
    private Long roleId;

    @NotBlank
    private Long employeeId;
}
