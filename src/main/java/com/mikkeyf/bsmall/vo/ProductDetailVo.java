package com.mikkeyf.bsmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.vo
 * @className: ProductDetailVo
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/10 20:22
 * @version: 1.0
 */
@Data
public class ProductDetailVo {
    private Integer id;

    private Integer categoryId;

    private String name;

    private String subTitle;

    private String mainImage;

    private String subImage;

    private String detail;

    private BigDecimal price;

    private Integer stock;

    private Integer status;

    private Date createTime;

    private Date updateTime;
}
