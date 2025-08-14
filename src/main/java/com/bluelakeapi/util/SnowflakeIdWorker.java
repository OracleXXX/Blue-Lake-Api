package com.bluelakeapi.util;

import cn.hutool.core.util.RandomUtil;
import lombok.extern.slf4j.Slf4j;

import java.math.BigInteger;
import java.util.Random;

@Slf4j
public class SnowflakeIdWorker {

    /**
     * 起始时间戳(2022-01-01)，用于用当前时间戳减去这个时间戳，算出偏移量
     **/
    private final long startTime = 1640966400000L;

    /**
     * 序列号在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 3L;

    /**
     * 业务id所占的位数
     */
    private final long businessIdBits = 5L;

    /**
     * 环境标识所占用的位数
     */
    private final long envIdBits = 2L;

    /**
     * 支持的最大机器id，结果是7 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = ~(-1L << workerIdBits);

    /**
     * 支持的最大业务标识id，结果是31
     */
    private final long maxBusinessId = ~(-1L << businessIdBits);

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 业务标识id向左移15位(12+3)
     */
    public final long businessIdShift = sequenceBits + workerIdBits;

    /**
     * 环境向左移20位(5+3+12)
     */
    private final long envLeftShift = sequenceBits + workerIdBits + businessIdBits;

    /**
     * 时间截向左移22位(2+5+3+12)
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits + businessIdBits + envIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = ~(-1L << sequenceBits);

    /**
     * 工作机器ID(0~7)
     */
    private final long workerId;

    /**
     * 业务ID(0~31)
     */
    private final long businessId;

    /**
     * 环境标识（0-3）
     */
    private final long envId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param businessId 业务ID,数据范围为0~31
     */
    public SnowflakeIdWorker(long businessId) {
        if (businessId > maxBusinessId || businessId < 0) {
            throw new IllegalArgumentException(String.format("businessId can't be greater than %d or less than 0", maxBusinessId));
        }
        this.businessId = businessId;
        this.workerId = getWorkId();
        this.envId = EnvUtils.getEnvId();
    }

    /**
     * 获取字节数组，相加取余
     *
     * @param s
     * @param max
     * @return
     */
    private long getHostId(String s, int max) {
        byte[] bytes = s.getBytes();
        long sum = 0;
        for (int b : bytes) {
            sum += b;
        }
        return sum % (max + 1);
    }

    /**
     * 获取机器ID
     *
     * @return
     */
    private long getWorkId() {
        try {
            return getHostId(IpUtil.getLocalIp(), 7);
        } catch (Exception e) {
            int workId = new Random().nextInt(8);
            log.info("GetHostAddress fail, Random workId:{}", workId);
            return workId;
        }
    }

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized String nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards. Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = RandomUtil.randomLong(sequenceMask - 1);
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        return (((timestamp - startTime) << timestampLeftShift) // 时间戳 64-23
                | (envId << envLeftShift) // 环境标识 22-21
                | (businessId << businessIdShift) // 业务ID 20-16
                | (workerId << workerIdShift) //工作机器 15-13
                | sequence) + ""; //序列号 12-1
    }

    /**
     * 返回16进制ID
     *
     * @return
     */
    public String nextRadix16Id() {
        return new BigInteger(nextId()).toString(16);
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

}