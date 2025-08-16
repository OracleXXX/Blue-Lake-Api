package com.bluelakeapi.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BusinessRequest {

    private Long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "display name is required")
    private String displayName;

    @NotBlank(message = "description is required")
    private String description;
}
