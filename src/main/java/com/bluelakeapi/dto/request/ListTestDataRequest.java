package com.bluelakeapi.dto.request;

import lombok.Data;

@Data
public class ListTestDataRequest {

    //private Long id;

    private Integer pageNumber = 1;

    private Integer pageSize = 10;

}
