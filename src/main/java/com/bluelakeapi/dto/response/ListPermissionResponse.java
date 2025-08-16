package com.bluelakeapi.dto.response;

import com.bluelakeapi.dto.bo.PageDto;
import com.bluelakeapi.dto.bo.PermissionBo;
import lombok.Data;

import java.util.List;

@Data
public class ListPermissionResponse {

    private List<PermissionBo> permissions;

    private PageDto pages;

}
