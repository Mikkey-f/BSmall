package com.mikkeyf.bsmall.enums;

import lombok.Getter;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.enums
 * @className: ResponseEnum
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/4 21:33
 * @version: 1.0
 */
@Getter
public enum ResponseEnum {
    ERROR(0, "意外错误"),
    SUCCESS(1, "成功"),
    PASSWORD_ERROR(2, "密码错误"),
    USERNAME_EXIST(3, "用户已存在"),
    PARAM_ERROR(4, "参数错误"),
    EMAIL_EXIST(5, "邮箱已注册"),
    DATABASE_ERROR(6, "数据库错误"),
    USERNAME_OR_PASSWORD_ERROR(7, "用户名或密码错误"),
    NOT_THIS_PRODUCT(8, "没有此商品"),
    PRODUCT_OFF_SALE_OR_DELETE(9, "商品下架或者删除"),
    NEED_LOGIN(10, "用户未登录,请先登录")
    ;

    Integer code;

    String data;

    ResponseEnum(Integer code, String data) {
        this.code = code;
        this.data = data;
    }
}
