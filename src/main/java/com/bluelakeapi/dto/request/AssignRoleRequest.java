package com.bluelakeapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import java.util.List;

@Data
public class AssignRoleRequest {

    @Min(1)
    private Long employeeId;

    private List<Long> roleIds;

}
