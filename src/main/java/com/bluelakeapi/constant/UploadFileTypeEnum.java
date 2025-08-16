package com.bluelakeapi.constant;

/**
 * @author Guanxiong Xue
 */
public enum UploadFileTypeEnum {
    /**
     * 上传的文件分类
     */
    IMAGE("image"),
    VIDEO("video"),
    HTML("html");

    UploadFileTypeEnum(String type) {
        this.type = type;
    }

    private final String type;

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }

}
