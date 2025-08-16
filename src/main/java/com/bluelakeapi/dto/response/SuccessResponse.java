package com.bluelakeapi.dto.response;

import lombok.Data;

@Data
public class SuccessResponse {

    private Boolean success;

    public static SuccessResponse success() {
        SuccessResponse response = new SuccessResponse();
        response.setSuccess(true);
        return response;
    }

    public static SuccessResponse fail() {
        SuccessResponse response = new SuccessResponse();
        response.setSuccess(false);
        return response;
    }

    private Long id;

}
