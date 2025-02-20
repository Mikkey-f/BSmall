package com.mikkeyf.bsmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.vo
 * @className: OrderItemVo
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/18 21:12
 * @version: 1.0
 */
@Data
public class OrderItemVo {

    private String orderNo;

    private Integer productId;

    private String productName;

    private String productImage;

    private BigDecimal currentUnitPrice;

    private Integer quantity;

    private BigDecimal totalPrice;

    private Date createTime;
}
