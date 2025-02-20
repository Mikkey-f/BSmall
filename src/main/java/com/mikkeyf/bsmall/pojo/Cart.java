package com.mikkeyf.bsmall.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.pojo
 * @className: Cart
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/1/25 0:58
 * @version: 1.0
 */
@Data
@AllArgsConstructor
public class Cart {
    private Integer productId;

    private Integer quantity;

    private Boolean productSelected;

}
