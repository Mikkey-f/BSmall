package com.mikkeyf.bsmall.util;

import com.mikkeyf.bsmall.enums.ResponseEnum;
import com.mikkeyf.bsmall.result.Result;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.util
 * @className: CookieUtil
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/7 0:12
 * @version: 1.0
 */
public class CookieUtil {

    public static String getValue(HttpServletRequest request, String name) {
        if(request == null || name == null) {
            throw new IllegalArgumentException("参数为空!");
        }

        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }

        return null;
    }
}
