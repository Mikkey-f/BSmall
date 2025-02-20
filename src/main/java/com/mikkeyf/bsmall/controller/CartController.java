package com.mikkeyf.bsmall.controller;

import com.mikkeyf.bsmall.form.CartAddForm;
import com.mikkeyf.bsmall.form.CartUpdateForm;
import com.mikkeyf.bsmall.pojo.User;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.ICartService;
import com.mikkeyf.bsmall.util.HostHolder;
import com.mikkeyf.bsmall.vo.CartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    ICartService cartService;

    @Autowired
    HostHolder hostHolder;

    @PostMapping("/carts")
    public Result<CartVo> add(@RequestBody CartAddForm cartAddForm) {
        if (cartAddForm.getProductId() == null) {
            return Result.error(PARAM_ERROR.getData());
        }

        User user = hostHolder.getUser();
        return cartService.add(user.getId(), cartAddForm);
    }

    @GetMapping("/carts")
    public Result<CartVo> list() {
        User user = hostHolder.getUser();
        return cartService.list(user.getId());
    }

    @PutMapping("/carts/{productId}")
    public Result<CartVo> update(@PathVariable Integer productId,
                                 @RequestBody CartUpdateForm cartUpdateForm) {
        User user = hostHolder.getUser();
        return cartService.update(user.getId(), productId, cartUpdateForm);
    }

    @DeleteMapping("/carts/{productId}")
    public Result<CartVo> delete(@PathVariable Integer productId) {
        User user = hostHolder.getUser();
        return cartService.delete(user.getId(), productId);
    }

    @PutMapping("/carts/selectedAll")
    public Result<CartVo> selectedAll() {
        User user = hostHolder.getUser();
        return cartService.selectedAll(user.getId());
    }

    @PutMapping("/carts/unSelectedAll")
    public Result<CartVo> unSelectedAll() {
        User user = hostHolder.getUser();
        return cartService.unSelectedAll(user.getId());
    }
}
