package com.mikkeyf.bsmall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.vo
 * @className: cartProductVo
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/10 21:25
 * @version: 1.0
 */
@Data
public class CartProductVo {

    private Integer productId;

    // 购买的数量
    private Integer quantity;

    private String productName;

    private String productSubtitle;

    private String productMainImage;

    private BigDecimal productPrice;

    private Integer productStatus;

    // 总价=数量*单价
    private BigDecimal productTotalPrice;

    private Integer productStock;

    // 商品是否选中
    private Boolean productSelected;
}
