package com.mikkeyf.bsmall.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.pojo
 * @className: product
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/1/25 0:15
 * @version: 1.0
 */
@Data
@TableName("mall_product")
@AllArgsConstructor
public class Product {

    private Integer id;

    private Integer categoryId;

    private String name;

    private String subtitle;

    private String mainImage;

    private String subImages;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
