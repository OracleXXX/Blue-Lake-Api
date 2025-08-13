package com.bluelakeapi.util;

import com.bluelakeapi.constant.BusinessIdEnum;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.ConcurrentHashMap;

@UtilityClass
public class SeqIdUtil {

    private static final ConcurrentHashMap<String, SnowflakeIdWorker> snowflakeIdWorkers = new ConcurrentHashMap();

    /**
     * 生成唯一ID
     *
     * @return
     */
    public static String nextId(BusinessIdEnum businessIdEnum) {
        snowflakeIdWorkers.computeIfAbsent(businessIdEnum.getBusiness(), e -> new SnowflakeIdWorker(businessIdEnum.getId()));
        return snowflakeIdWorkers.get(businessIdEnum.getBusiness()).nextId();
    }

    /**
     * 生成16进制唯一ID
     *
     * @return
     */
    public static String nextRadix16Id(BusinessIdEnum businessIdEnum) {
        snowflakeIdWorkers.computeIfAbsent(businessIdEnum.getBusiness(), e -> new SnowflakeIdWorker(businessIdEnum.getId()));
        return snowflakeIdWorkers.get(businessIdEnum.getBusiness()).nextRadix16Id();
    }

    /**
     * 根据seqId反解出业务id
     */
    public static long parseBusinessId(String seqId) {
        if (StringUtils.isBlank(seqId)) {
            throw new IllegalArgumentException("Illegal seqId:" + seqId);
        }
        return (Long.parseLong(seqId) >> 15) & 31;
    }

    public static long parseEnvId(String seqId) {
        if (StringUtils.isBlank(seqId)) {
            throw new IllegalArgumentException("Illegal seqId:" + seqId);
        }
        return (Long.parseLong(seqId) >> 20) & 3;
    }

}

