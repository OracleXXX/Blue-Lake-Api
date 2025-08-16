package com.bluelakeapi.dto.bo;

import lombok.Data;

@Data
public class RoleBo {

    private Long id;

    private String name;

    private String displayName;

    private String description;

    private Long createTime;

    private Integer level;

    private Long employeeRoleId;

}
