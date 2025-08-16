package com.bluelakeapi.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
public class PermissionRequest {

    private Long id;

    @Min(1)
    private Long businessId;

    @NotBlank
    private String action;

    @NotBlank
    private String displayName;

    @NotBlank
    private String description;

}
