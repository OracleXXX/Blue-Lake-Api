package com.bluelakeapi.dto.response;

import com.bluelakeapi.dto.bo.TestDataDto;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListTestDateResponse extends PageResponse{

    private List<TestDataDto> testDatas;
}
