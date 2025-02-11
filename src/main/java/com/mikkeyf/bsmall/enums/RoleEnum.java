package com.mikkeyf.bsmall.enums;

import lombok.Getter;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.enums
 * @className: RoleEnum
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/4 20:39
 * @version: 1.0
 * 0-管理员，1-普通用户
 */
@Getter
public enum RoleEnum {
    ADMIN(0),
    CUSTOMER(1),
    ;

    Integer code;

    RoleEnum(Integer code) {
        this.code = code;
    }
}
