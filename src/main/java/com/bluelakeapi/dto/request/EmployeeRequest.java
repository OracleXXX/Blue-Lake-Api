package com.bluelakeapi.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeRequest {

    private Long id;

    private String email;

    private String password;

    private String nickname;

    private String libaUid;

    private List<Long> roleIds;

}
