package com.bluelakeapi.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class Common {

    public static String calculateSessiontId(String uid1, String uid2) {
        return new BigDecimal(uid1).add(new BigDecimal(uid2)).toString();
    }

}
