package com.bluelakeapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class AssignEmployeeRequest {

    @Min(1)
    private Long employeeId;

    @Min(1)
    private Long roleId;

}
