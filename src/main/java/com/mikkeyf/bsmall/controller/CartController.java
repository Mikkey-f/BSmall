package com.mikkeyf.bsmall.controller;

import com.mikkeyf.bsmall.form.CartAddForm;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.vo.CartVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.mikkeyf.bsmall.enums.ResponseEnum.PARAM_ERROR;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.controller
 * @className: CartController
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/10 21:33
 * @version: 1.0
 */
@RestController
public class CartController {

    @PostMapping("/carts")
    public Result<CartVo> add(@RequestBody CartAddForm cartAddForm) {
        if (cartAddForm.getProductId() == null) {
            return Result.error(PARAM_ERROR.getData());
        }

        return null;
    }
}
