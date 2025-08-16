package com.bluelakeapi.dto.response;

import com.bluelakeapi.dto.bo.EmployeeBo;
import lombok.Data;

@Data
public class GetEmployeeResponse {

    private EmployeeBo employee;
}
