package com.bluelakeapi.dto.bo;

import lombok.Data;

import java.util.List;

@Data
public class PermissionGroupByBusinessBo {

    private Long businessId;

    private String businessName;

    private String displayName;

    private List<PermissionBo> permissions;

}
