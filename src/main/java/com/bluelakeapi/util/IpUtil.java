package com.bluelakeapi.util;

import lombok.experimental.UtilityClass;

import java.net.InetAddress;
import java.net.UnknownHostException;

@UtilityClass
public class IpUtil {

    /**
     * 获取本机IP
     *
     * @return 返回本机ip
     */
    public static String getLocalIp() {
        //K8S的CNI模式下启动
        String ip = "";
        // 无法获取
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            //
        }
        return ip;
    }

}

