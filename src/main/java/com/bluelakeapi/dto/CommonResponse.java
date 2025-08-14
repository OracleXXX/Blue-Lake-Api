package com.bluelakeapi.dto;

import com.bluelakeapi.constant.CommonResponseStatus;
import lombok.Data;

@Data
public class CommonResponse<T> {

    private int code;

    private String msg;

    private T data;

    private CommonResponse() {
    }

    private CommonResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private CommonResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    private CommonResponse(CommonResponseStatus commonResponseStatus) {
        this.code = commonResponseStatus.getCode();
        this.msg = commonResponseStatus.getMsg();
    }

    private CommonResponse(CommonResponseStatus status, T data) {
        this.code = status.getCode();
        this.msg = status.getMsg();
        this.data = data;
    }

    public static <T> CommonResponse<T> getResult(int code, String msg) {
        return new CommonResponse<>(code, msg);
    }

    public static <T> CommonResponse<T> getResult(int code, String msg, T data) {
        return new CommonResponse<>(code, msg, data);
    }

    public static <T> CommonResponse<T> getResult(CommonResponseStatus status) {
        return new CommonResponse<>(status);
    }

    public static <T> CommonResponse<T> getResult(CommonResponseStatus status, T data) {
        return new CommonResponse<>(status, data);
    }

    public static <T> CommonResponse<T> getSuccessResult() {
        return new CommonResponse<>(CommonResponseStatus.SUCCESS);
    }

    public static <T> CommonResponse<T> getSuccessResult(T data) {
        return new CommonResponse<>(CommonResponseStatus.SUCCESS, data);
    }

}
