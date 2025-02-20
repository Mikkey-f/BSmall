package com.mikkeyf.bsmall.util;

import lombok.Data;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.util
 * @className: RedisKeyUtil
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/15 21:55
 * @version: 1.0
 */

public class RedisKeyUtil {
    private static final String SPLIT = ":";
    private final static String CART_REDIS_KEY_TEMPLATE = "cart";

    public static String getUserCartKey(int userId) {
        return CART_REDIS_KEY_TEMPLATE + SPLIT + userId;
    }

}
