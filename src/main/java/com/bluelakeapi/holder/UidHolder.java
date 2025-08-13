package com.bluelakeapi.holder;

import org.springframework.core.NamedThreadLocal;

public class UidHolder {

    private static final ThreadLocal<Long> uidHolder = new NamedThreadLocal<>("uid");

    public static void resetUid() {
        uidHolder.remove();
    }

    public static Long getUid() {
        return uidHolder.get();
    }

    public static void setUid(Long uid) {
        uidHolder.set(uid);
    }

}