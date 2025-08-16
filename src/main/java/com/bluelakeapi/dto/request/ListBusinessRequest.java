package com.bluelakeapi.dto.request;

import lombok.Data;

@Data
public class ListBusinessRequest extends PageableRequest{
    private String displayName = "";
}
