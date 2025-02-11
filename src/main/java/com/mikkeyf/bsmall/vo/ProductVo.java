package com.mikkeyf.bsmall.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.vo
 * @className: ProductVo
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/8 22:09
 * @version: 1.0
 */
@Data
public class ProductVo {

    private Integer id;

    private Integer categoryId;

    private String name;

    private String subTitle;

    private String mainImage;

    private Integer status;

    private BigDecimal price;
}
