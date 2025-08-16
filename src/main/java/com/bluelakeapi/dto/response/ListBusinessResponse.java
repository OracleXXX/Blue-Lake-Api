package com.bluelakeapi.dto.response;

import com.bluelakeapi.dto.bo.BusinessBo;
import com.bluelakeapi.dto.bo.PageDto;
import lombok.Data;

import java.util.List;

@Data
public class ListBusinessResponse{

    private List<BusinessBo> businesses;

    private PageDto pages;
}
