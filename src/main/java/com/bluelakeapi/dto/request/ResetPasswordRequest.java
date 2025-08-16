package com.bluelakeapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ResetPasswordRequest {

    private Long employeeId;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String newPassword;
}
