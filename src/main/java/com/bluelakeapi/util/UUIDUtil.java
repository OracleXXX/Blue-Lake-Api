package com.bluelakeapi.util;

import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public final class UUIDUtil {

    public static String getUuidStringWithoutHyphen() {
        return getUuidStringWithoutHyphen(UUID.randomUUID());
    }

    public static String getUuidStringWithoutHyphen(UUID uuid) {
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();
        return digits(mostSigBits >> 32, 8)
                + digits(mostSigBits >> 16, 4)
                + digits(mostSigBits, 4)
                + digits(leastSigBits >> 48, 4)
                + digits(leastSigBits, 12);
    }

    private static String digits(long val, int digits) {
        long hi = 1L << digits * 4;
        return Long.toHexString(hi | val & hi - 1L).substring(1);
    }

    public static String getUuidStringWithHyphen() {
        return UUID.randomUUID().toString();
    }

}