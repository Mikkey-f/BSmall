package com.mikkeyf.bsmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mikkeyf.bsmall.form.CartAddForm;
import com.mikkeyf.bsmall.form.CartUpdateForm;
import com.mikkeyf.bsmall.pojo.Cart;
import com.mikkeyf.bsmall.pojo.Category;
import com.mikkeyf.bsmall.pojo.Product;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.vo.CartVo;

import static com.mikkeyf.bsmall.enums.ResponseEnum.NOT_THIS_PRODUCT;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service
 * @className: ICartService
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/15 21:25
 * @version: 1.0
 */
public interface ICartService extends IService<Cart> {
    Result<CartVo> add(Integer userId, CartAddForm cartAddForm);

    Result<CartVo> list(Integer userId);

    Result<CartVo> update(Integer userId, Integer productId, CartUpdateForm cartUpdateForm);

    Result<CartVo> delete(Integer userId, Integer productId);

    Result<CartVo> selectedAll(Integer userId);

    Result<CartVo> unSelectedAll(Integer userId);

}