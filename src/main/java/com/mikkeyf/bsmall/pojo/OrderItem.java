package com.mikkeyf.bsmall.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.pojo
 * @className: OrderItem
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/1/25 0:15
 * @version: 1.0
 */
@Data
@TableName("mall_order_item")
@AllArgsConstructor
public class OrderItem {

    private Integer id;

    private Integer userId;

    private String orderNo;

    private Integer productId;

    private String productName;

    private String productImg;

    private Integer currentUnitPrice;

    private Integer quantity;

    private Integer totalPrice;

    private Date createTime;

    private Date updateTime;
}
