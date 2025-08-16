package com.bluelakeapi.constant;

public enum CommonResponseStatus {

    /**
     * 状态码
     **/
    SUCCESS(0, "ok"),
    PARAMS_ERROR(400, "params error"),
    FAIL(500, "server error"),
    /**
     * 6XX 登录相关
     */
    NO_TOKEN(600, "token is empty"),
    TOKEN_INVALID(601, "token is invalid"),
    TOKEN_EXPIRED(602, "token has expired"),
    VERIFY_CODE_EXPIRED(603, "verify code expired"),
    VERIFY_CODE_NOT_MATCH(604, "verify code not match"),
    PASSWORD_NOT_MATCH(605, "password not match"),
    ACCOUNT_WAS_BLOCKED(606, "account has been blocked"),
    PASSWORD_WAS_SAME_LAST(607, "password was same to last"),
    THIRD_LOGIN_ERROR(608, "user not login or login code expired"),    //三方登录异常,
    ACCOUNT_FORMAT_INVALID(609, "account format is invalid"),
    PASSWORD_INVALID(610, "password not math rule"), //密码不符合要求
    /**
     * 7XX用户账号相关
     */
    USER_NOT_EXIST(700, "user not exist"),
    /**
     * 8XX 权限相关
     */
    NO_CHANNEL_UNDER_GROUP(801, "no channel under the group"),
    USER_HAS_JOINED_GROUP(802, "user has joined the group"),

    DB_INSERT_ERROR(10020, "db_insert_error"),
    DB_UPDATE_ERROR(10021, "db_update_error"),
    DB_DELETE_ERROR(10022, "db_delete_error"),
    DATA_NOT_EXIST(10023, "data not exist"),
    TAG_NAME_NOT_EXIST(10700, "tag name not exist"),
    TAG_NAME_ALREADY_EXIST(10701, "tag name already exist"),
    DATA_ALREADY_EXIST(10702, "data already exist");

    private int code;

    private String msg;

    CommonResponseStatus(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}