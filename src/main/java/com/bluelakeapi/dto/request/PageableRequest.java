package com.bluelakeapi.dto.request;

import com.bluelakeapi.constant.Setting;
import lombok.Data;

@Data
public class PageableRequest {

    private Integer pageNumber = Setting.DEFAULT_PAGE_NUMBER;

    private Integer pageSize = Setting.DEFAULT_PAGE_SIZE;
}
