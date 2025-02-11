package com.mikkeyf.bsmall.util;

import java.util.UUID;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.util
 * @className: BSmallUtil
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/6 23:50
 * @version: 1.0
 */
public class BSmallUtil {
    // 生成随机字符串
    public static String generateUUID() {
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
