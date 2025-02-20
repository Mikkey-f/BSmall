package com.mikkeyf.bsmall.vo;

import com.mikkeyf.bsmall.pojo.Shipping;
import com.mikkeyf.bsmall.pojo.ShippingOrder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.vo
 * @className: OrderVo
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/18 21:09
 * @version: 1.0
 */
@Data
public class OrderVo {

    private String orderNo;

    private BigDecimal payment;

    private Integer paymentType;

    private Integer postage;

    private Integer status;

    private Date paymentTime;

    private Date sendTime;

    private Date endTime;

    private Date closeTime;

    private Date createTime;

    private List<OrderItemVo> orderItemVoList;

    private Integer shippingId;

    private ShippingOrder shippingItemVo;
}
