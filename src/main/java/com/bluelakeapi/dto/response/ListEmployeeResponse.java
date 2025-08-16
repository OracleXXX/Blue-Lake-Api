package com.bluelakeapi.dto.response;

import com.bluelakeapi.dto.bo.EmployeeBo;
import lombok.Data;

import java.util.List;

@Data
public class ListEmployeeResponse {

    private List<EmployeeBo> employees;

    private Integer totalCount;

}
