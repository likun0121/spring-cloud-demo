package com.lk.order.util;

import java.util.Random;

/**
 * @author LK
 */
public class KeyUtil {

    /**
     * 生产唯一主键
     * @return 格式：时间戳+随机数 该方法并不是唯一随机数
     */
    public static String genUniqueKey() {
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;
        return System.currentTimeMillis() + String.valueOf(number);
    }

}
