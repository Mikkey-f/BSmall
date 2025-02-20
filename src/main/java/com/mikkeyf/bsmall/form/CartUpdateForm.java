package com.mikkeyf.bsmall.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.form
 * @className: CartUpdateForm
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/15 23:31
 * @version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartUpdateForm {

    private Integer quantity;

    private Boolean selected = true;

    public CartUpdateForm(Integer quantity) {
        this.quantity = quantity;
    }

    public CartUpdateForm(Boolean selected) {
        this.selected = selected;
    }
}
