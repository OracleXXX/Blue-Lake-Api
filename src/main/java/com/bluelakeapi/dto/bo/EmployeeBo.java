package com.bluelakeapi.dto.bo;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeBo {

    private Long id;

    private String email;

    private String nickname;

    private String libaUid;

    private Long createTime;

    private List<RoleBo> roles;

}
