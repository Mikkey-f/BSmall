package com.mikkeyf.bsmall.enums;

import lombok.Getter;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.enums
 * @className: ProductStatusEnum
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/10 20:46
 * @version: 1.0
 * 商品状态：1-在售，2-下架，3-删除
 */
@Getter
public enum ProductStatusEnum {
    ON_SALE(1),

    OFF_SALE(2),

    DELETE(3),
    ;
    Integer code;


    ProductStatusEnum(Integer code) {
        this.code = code;
    }
}
