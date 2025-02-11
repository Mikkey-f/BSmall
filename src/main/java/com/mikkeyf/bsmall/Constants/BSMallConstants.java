package com.mikkeyf.bsmall.Constants;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.Constants
 * @className: BsMallConstants
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/4 23:47
 * @version: 1.0
 */
public class BSMallConstants {
    public static final String CURRENT_USER = "currentUser";
    /**
     * 默认状态的登录凭证的超时时间
     */
    public static final int DEFAULT_EXPIRED_SECONDS = 3600 * 12;

    /**
     * 记住状态下的登录凭证超时时间
     */
    public static final int REMEMBER_EXPIRED_SECONDS = 3600 * 24 * 100;

    /**
     * 根目录
     */
    public static Integer ROOT_PARENT_ID = 0;
}
