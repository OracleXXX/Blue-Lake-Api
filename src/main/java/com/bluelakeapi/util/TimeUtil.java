package com.bluelakeapi.util;

import lombok.experimental.UtilityClass;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

@UtilityClass
public class TimeUtil {

    /**
     * @return 20201216
     */
    public static String getYmd() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.applyPattern("yyyyMMdd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 根据时区，格式化年月日
     *
     * @param timeZone 时区
     * @param unixTime 毫秒
     * @return
     */
    public static String getYmd(String timeZone, Long unixTime) {
        if ("0".equals(timeZone)) {
            timeZone = "+" + timeZone;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone(ZoneId.of(timeZone)));
        simpleDateFormat.applyPattern("yyyyMMdd");
        return simpleDateFormat.format(unixTime);
    }

    /**
     * @param time timestamp
     * @return 2021-11-17 22:44:00
     */
    public static String formatTime(Long time) {
        Date date = new Date(time);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(date);
    }

    /**
     * @return unixStamp
     */
    public static Long getCurrentTime() {
        return System.currentTimeMillis() / 1000;
    }

}
