package com.mikkeyf.bsmall.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.vo
 * @className: CartVo
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/10 21:21
 * @version: 1.0
 */
@Data
public class CartVo {

    private List<cartProductVo> productVoList;

    private boolean selectedAll;

    private BigDecimal cartTotalPrice;

    private Integer cartTotalQuantity;


}
