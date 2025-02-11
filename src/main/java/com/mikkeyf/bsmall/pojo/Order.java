package com.mikkeyf.bsmall.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.pojo
 * @className: Order
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/1/25 0:15
 * @version: 1.0
 */
@Data
@TableName("mall_order")
@AllArgsConstructor
public class Order {
    private Integer id;

    private String orderNo;

    private Integer userId;

    private Integer totalPrice;

    private String receiverName;

    private String receiverMobile;

    private String receiverAddress;

    private Integer orderStatus;

    private Integer postage;

    private Integer paymentType;

    private Date deliveryTime;

    private Date payTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;
}
