package com.mikkeyf.bsmall.enums;

import lombok.Getter;

/**
 * 订单状态 0-已取消 10-未付款 20-已付款 40-已发货 50-交易成功 60-交易关闭
 */

@Getter
public enum OrderStatusEnum {

    CANCELED(0, "已取消"),

    NO_PAY(10, "未付款"),

    PAID(20, "已付款"),

    SHIPPED(40, "已发货"),

    TRADE_SUCCESS(50, "交易成功"),

    TRADE_CLOSE(60, "交易关闭"),
    ;

    Integer code;

    String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
