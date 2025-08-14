package com.bluelakeapi.util;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;

public final class JsonUtil {
    private static final ObjectMapper defaultObjectMapper = buildDefaultObjectMapper();

    protected static ObjectMapper buildDefaultObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        objectMapper.setSerializationInclusion(Include.NON_NULL);
        return objectMapper;
    }

    public static <T> String toJson(T object) {
        return toJson(object, (ObjectMapper) null);
    }

    public static <T> String toJson(T object, ObjectMapper objectMapper) {
        if (null == objectMapper) {
            objectMapper = defaultObjectMapper;
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException var3) {
            throw new IllegalArgumentException(var3.getMessage());
        }
    }

    public static <T> T toObject(String json, Class<T> valueType) {
        return toObject(json, valueType, (TypeReference<T>) null);
    }

    public static <T> T toObject(String json, TypeReference<T> type) {
        return toObject(json, (Class<T>) null, type);
    }

    private static <T> T toObject(String json, Class<T> clazz, TypeReference<T> type) {
        T obj = null;
        if (!StringUtils.isEmpty(json)) {
            try {
                if (clazz != null) {
                    obj = defaultObjectMapper.readValue(json, clazz);
                } else {
                    obj = defaultObjectMapper.readValue(json, type);
                }
            } catch (IOException var5) {
                throw new IllegalArgumentException(var5.getMessage());
            }
        }

        return obj;
    }

    private JsonUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
