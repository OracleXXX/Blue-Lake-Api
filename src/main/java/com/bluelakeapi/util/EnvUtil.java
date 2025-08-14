package com.bluelakeapi.util;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@UtilityClass
public class EnvUtil {
    /**
     * 缓存初始化过的环境标记
     */
    private static Environment iEnv = null;
    /**
     * 启动参数环境标记
     */
    private final static String SPRING_PROFILES_ACTIVE_KEY = "spring.profiles.active";

    public static final String APPLICATION_YML_CLASSPATH = "application.yml";

    private static Map applicationYamlMap = new HashMap();

    static {
        InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(APPLICATION_YML_CLASSPATH);
        if (in != null) {
            try {
                Yaml applicationYaml = new Yaml();
                applicationYamlMap = applicationYaml.loadAs(in, Map.class);
            } finally {
                try {
                    in.close();
                } catch (IOException e) {
                    //
                }
            }
        }
    }

    /**
     * 获取当前运行环境
     *
     * @return 环境
     */
    public static Environment getEnv() {
        if (null == iEnv) {
            initEnv();
        }
        return iEnv;
    }

    /**
     * 初始化环境
     * <p>
     * 目前仅支持通过启动参数设置
     */
    private static synchronized void initEnv() {
        if (null != iEnv) {
            return;
        }

        String env = System.getProperty(SPRING_PROFILES_ACTIVE_KEY);

        if (StringUtils.isEmpty(env)) {
            Map springMap = (Map) applicationYamlMap.get("spring");
            if (springMap != null) {
                Map profilesMap = (Map) springMap.get("profiles");
                if (profilesMap != null) {
                    env = MapUtils.getString(profilesMap, "active", null);
                }
            }
        }
        // 未设置环境参数的默认为DEV
        if (StringUtils.isEmpty(env)) {
            log.info("can't find env config ,default dev");
            iEnv = Environment.DEV;
            return;
        }
        // 忽略大小写
        env = env.trim().toLowerCase();
        switch (env) {
            case "prod":
            case "pro":
                iEnv = Environment.PROD;
                break;
            case "gray":
                iEnv = Environment.GRAY;
                break;
            case "test":
                iEnv = Environment.TEST;
                break;
            case "dev":
                iEnv = Environment.DEV;
                break;
            default:
                log.warn("can't find env config, default dev - wrong env:{}", env);
                iEnv = Environment.DEV;
        }
    }

    /**
     * 判断是否开发环境
     *
     * @return 返回是否开发环境
     */
    public static boolean isDevEnv() {
        if (null == iEnv) {
            initEnv();
        }
        return Environment.DEV.equals(iEnv);
    }

    /**
     * 判断是否正式环境
     *
     * @return 返回是否正式环境
     */
    public static boolean isProdEnv() {
        if (null == iEnv) {
            initEnv();
        }
        return Environment.PROD.equals(iEnv);
    }

    /**
     * 判断是否灰度环境
     *
     * @return 返回是否灰度环境
     */
    public boolean isGrayEnv() {
        if (null == iEnv) {
            initEnv();
        }
        return Environment.GRAY.equals(iEnv);
    }

    /**
     * 判断是否测试环境
     *
     * @return 返回是否灰度环境
     */
    public boolean isTestEnv() {
        if (null == iEnv) {
            initEnv();
        }
        return Environment.TEST.equals(iEnv);
    }

    /**
     * 环境定义
     */
    public enum Environment {
        /**
         * 开发环境
         */
        DEV,
        /**
         * 测试环境
         */
        TEST,
        /**
         * 灰度环境
         */
        GRAY,
        /**
         * 生产环境
         */
        PROD
    }
}
