package com.bluelakeapi.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class EnvUtils {

    private static final int ENV_ID = EnvUtils.getEnvId();

    /**
     * 返回环境id：0生产环境 1灰度环境 2测试环境
     */
    public static int getEnvId() {
        EnvUtil.Environment env = EnvUtil.getEnv();
        switch (env) {
            case DEV:
                return 3;
            case TEST:
                return 2;
            case GRAY:
                return 1;
            case PROD:
                return 0;
            default:
                throw new IllegalArgumentException("env type invalid:" + env);
        }
    }

}
