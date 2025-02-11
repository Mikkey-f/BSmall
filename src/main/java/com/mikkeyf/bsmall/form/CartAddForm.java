package com.mikkeyf.bsmall.form;

import lombok.Data;
import org.jetbrains.annotations.NotNull;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.form
 * @className: CartAddForm
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/10 21:32
 * @version: 1.0
 */
@Data
public class CartAddForm {


    private Integer productId;

    private Boolean selected = true;
}
