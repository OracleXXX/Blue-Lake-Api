package com.bluelakeapi.util;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;

@UtilityClass
public class ListUtil {

    /**
     * 用于多表联合分页查询时，区分参数为空和查询结果为空
     * 参数为空时，后续查询应该跳过该参数校验
     * 查询结果为空时，后续查询应该永久为空。
     * 所以该方法用于查询结果->如果查询结果为空时，给查询结果强制添加一个null值
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> formatList(List<T> t) {
        if (CollectionUtils.isNotEmpty(t)) {
            return t;
        } else {
            return Collections.singletonList(null);
        }
    }

}
