package com.bluelakeapi.dto.request;

import lombok.Data;

@Data
public class ListPermissionRequest extends PageableRequest{

    private Long businessId;
}
