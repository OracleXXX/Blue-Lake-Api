package com.bluelakeapi.dto.response;

import com.bluelakeapi.dto.bo.PermissionGroupByBusinessBo;
import lombok.Data;

import java.util.List;

@Data
public class ListPermissionGroupByBusinessResponse {

    List<PermissionGroupByBusinessBo> permissionGroupByBusiness;

}
