package com.bluelakeapi.dto.response;

import com.bluelakeapi.dto.bo.RoleBo;
import lombok.Data;

import java.util.List;

@Data
public class ListRoleResponse {

    private List<RoleBo> roles;

    private Integer totalCount;

}
