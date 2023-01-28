package com.util.idmaker;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class GenLong15ForTokenUtil {

    private static final int minNum = 1000;

    private static final AtomicInteger bigAtomic = new AtomicInteger(minNum);

    private static volatile Integer machineCode = null;

    private static AtomicLong recordSecond = null;

    private static final long EPOCH = 590000000L;

    static {
        checkAndInit();
    }

    private static void checkAndInit() {
        if (machineCode == null) {
            synchronized (GenLong15ForTokenUtil.class) {
                if (machineCode == null) {
                    initData();
                }
            }
        }
    }

    private static void initData() {
        recordSecond = new AtomicLong(nowSecond());
        try {
            String machineIp = InetAddress.getLocalHost().getHostAddress();
            String codeStr = machineIp.substring(machineIp.length() - 1);
            machineCode = new BigInteger(codeStr).intValue();
        } catch (UnknownHostException e) {
            machineCode = 3;
        }
    }

    /**
     * 获取15位长度的自增长Key
     * 每秒可生成8970个不重复编号
     *
     * @return long
     */
    public static Long genLong15() {

        //如果周期时间不是当前时间，重置周期时间，以及重置序列号
        long nowSecond = nowSecond();
        long time = recordSecond.get();
        if (nowSecond > time) {
            synchronized (GenLong15ForTokenUtil.class) {
                nowSecond = nowSecond();
                time = recordSecond.get();
                if (nowSecond > time) {
                    recordSecond = new AtomicLong(nowSecond);
                    time = recordSecond.get();
                    bigAtomic.set(minNum);
                }
            }
        }

        int seq = bigAtomic.getAndIncrement();

        //如果序列号达到最大，重置当前时间，并等待下一周期
        if (seq >= 9999) {
            synchronized (GenLong15ForTokenUtil.class) {
                time = recordSecond.get();
                seq = bigAtomic.getAndIncrement();
                if (seq >= 9999) {
                    recordSecond = new AtomicLong(nextSecond(recordSecond.get()));
                    time = recordSecond.get();
                    bigAtomic.set(minNum);
                    seq = bigAtomic.getAndIncrement();
                }
            }
        }
        String differ = String.valueOf(time - EPOCH);
        return new BigInteger(differ + seq + machineCode).longValue();
    }

    /**
     * 获取下一不同秒的时间戳，不能与最后的时间戳一样
     */
    private static long nextSecond(long second) {
        long now = nowSecond();
        while (now <= second) {
            now = nowSecond();
        }
        return now;
    }

    /**
     * 当前的秒级时间戳
     *
     * @return
     */
    private static long nowSecond() {
        return LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
    }
}
