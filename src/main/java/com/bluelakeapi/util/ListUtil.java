package com.bluelakeapi.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class ListUtil {

    public static <T> List<T> formatList(List<T> t) {
        if (CollectionUtils.isNotEmpty(t)) {
            return t;
        } else {
            return Collections.singletonList(null);
        }
    }

}
