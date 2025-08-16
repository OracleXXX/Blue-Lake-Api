package com.bluelakeapi.dto.response;

import lombok.Data;

@Data
public class LoginResponse {

    private String token;

    private String nickname;

    private String employeeId;

}
