package com.bluelakeapi.constant;

import lombok.Getter;

@Getter
public enum BusinessIdEnum {

    USER("user", 1),

    ;

    private final String business;
    private final Integer id;

    BusinessIdEnum(String business, Integer id) {
        this.business = business;
        this.id = id;
    }

}
