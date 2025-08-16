package com.bluelakeapi.dto.request;

import lombok.Data;

@Data
public class ListEmployeeRequest {

    private String nickname;

    private Long roleId;
}
