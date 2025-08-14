package com.bluelakeapi.dto.bo;

import lombok.Data;

@Data
public class TestDataDto {

    private Long id;

    private String name;

    private Long createTime;

    private Long updateTime;

    private Integer isDelete;
}
