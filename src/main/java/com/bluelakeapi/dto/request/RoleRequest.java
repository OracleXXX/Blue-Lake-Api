package com.bluelakeapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RoleRequest {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String displayName;

    @NotBlank
    private String description;

    @NotNull
    private Integer level;

}
