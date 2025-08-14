package com.bluelakeapi.util;

import cn.hutool.core.util.StrUtil;
import com.bluelakeapi.exception.CommonException;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

/**
 * 根据UID生成唯一ID，可用于邀请码
 */
@UtilityClass
public class ShortIdUtil {

    private static final String SOURCE_ID = "LzNbyWUdk56EcfjmDZJsSFeKARuQBvxi3P7X84MohtITwVaqng1r2HlYOGCp9";

    public static String generateShortId(String seqId) {
        long seqIdLong = Long.parseLong(seqId);
        StringBuilder sb = new StringBuilder();
        while (seqIdLong != 0L) {
            long mod = seqIdLong % SOURCE_ID.length();
            seqIdLong = (seqIdLong - mod) / SOURCE_ID.length();
            sb.append(SOURCE_ID.charAt(Integer.parseInt(mod + "")));
        }
        return sb.toString();
    }

    public static String parseToUid(String shortId) {
        if (StringUtils.isBlank(shortId)) {
            throw new CommonException(500, "short id is invalid");
        }
        Long seqId = 0L;
        for (int i = shortId.length() - 1; i >= 0; i--) {
            seqId = seqId * SOURCE_ID.length() + StrUtil.indexOf(SOURCE_ID, shortId.charAt(i));
        }
        return String.valueOf(seqId);
    }

}
