package com.bluelakeapi.dto.bo;

import lombok.Data;

@Data
public class PermissionBo {

    private Long id;

    private Long businessId;

    private String businessName;

    private String action;

    private String displayName;

    private String description;

    private Long createTime;

    private Long rolePermissionId;

    private Integer sortNum;

}
