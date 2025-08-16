package com.bluelakeapi.dto.bo;

import com.bluelakeapi.constant.Setting;
import lombok.Data;

@Data
public class PageDto {

    private Integer pageNumber = Setting.DEFAULT_PAGE_NUMBER;

    private Integer pageSize = Setting.DEFAULT_PAGE_SIZE;

    private Integer totalCount;
}
