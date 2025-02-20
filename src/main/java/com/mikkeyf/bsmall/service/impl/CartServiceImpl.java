package com.mikkeyf.bsmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.mikkeyf.bsmall.dao.CartMapper;
import com.mikkeyf.bsmall.dao.CategoryMapper;
import com.mikkeyf.bsmall.dao.ProductMapper;
import com.mikkeyf.bsmall.enums.ProductStatusEnum;
import com.mikkeyf.bsmall.form.CartAddForm;
import com.mikkeyf.bsmall.form.CartUpdateForm;
import com.mikkeyf.bsmall.pojo.Cart;
import com.mikkeyf.bsmall.pojo.Category;
import com.mikkeyf.bsmall.pojo.Product;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.ICartService;
import com.mikkeyf.bsmall.service.ICategoryService;
import com.mikkeyf.bsmall.util.HostHolder;
import com.mikkeyf.bsmall.util.RedisKeyUtil;
import com.mikkeyf.bsmall.vo.CartProductVo;
import com.mikkeyf.bsmall.vo.CartVo;
import io.netty.util.internal.StringUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.mikkeyf.bsmall.enums.ResponseEnum.*;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service.impl
 * @className: CartServiceImpl
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/15 21:25
 * @version: 1.0
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private Gson gson = new Gson();

    public Result<CartVo> add(Integer userId, CartAddForm cartAddForm) {
        Integer quantity = 1;
        Product product = productMapper.selectById(cartAddForm.getProductId());
        if (product == null) {
            return Result.error(NOT_THIS_PRODUCT.getData());
        }

        if (!product.getStatus().equals(ProductStatusEnum.ON_SALE.getCode())) {
            return Result.error(PRODUCT_OFF_SALE_OR_DELETE.getData());
        }

        if (product.getStock() <= 0) {
            return Result.error(PRODUCT_STOCK_ERROR.getData());
        }

        String userCartKey = RedisKeyUtil.getUserCartKey(userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String value = opsForHash.get(userCartKey, String.valueOf(product.getId()));

        Cart cart;
        if (StringUtils.isEmpty(value)) {
            cart = new Cart(product.getId(), quantity, cartAddForm.getSelected());
        } else {
            cart = gson.fromJson(value, Cart.class);
            cart.setQuantity(cart.getQuantity() + 1);
        }
        //写入到redis
        opsForHash.put(userCartKey, String.valueOf(product.getId()),
                gson.toJson(cart));

        return list(userId);
    }

    @Override
    public Result<CartVo> list(Integer userId) {
        String userCartKey = RedisKeyUtil.getUserCartKey(userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();

        Map<String, String> entries = opsForHash.entries(userCartKey);
        List<CartProductVo> cartProductVoList = new ArrayList<>();
        CartVo cartVo = new CartVo();

        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        List<Product> products = productMapper.selectList(queryWrapper);

        boolean selectedAll = true;
        Integer cartTotalQuantity = 0;
        BigDecimal cartTotalPrice = BigDecimal.ZERO;

        for (Map.Entry<String, String> entry : entries.entrySet()) {
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            for (Product product : products) {
                if (product.getId().equals(cart.getProductId())) {
                    CartProductVo cartProductVo = new CartProductVo();
                    BeanUtils.copyProperties(cart, cartProductVo);
                    cartProductVo.setProductName(product.getName());
                    cartProductVo.setProductPrice(product.getPrice());
                    cartProductVo.setProductStatus(product.getStatus());
                    cartProductVo.setProductStock(product.getStock());
                    cartProductVo.setProductMainImage(product.getMainImage());
                    cartProductVo.setProductSubtitle(product.getSubtitle());
                    cartProductVo.setProductTotalPrice(product.getPrice().multiply(BigDecimal.valueOf(cart.getQuantity())));
                    cartProductVoList.add(cartProductVo);
                    if (!cart.getProductSelected()) {
                        selectedAll = false;
                    } else {
                        cartTotalPrice = cartTotalPrice.add(cartProductVo.getProductTotalPrice());
                        cartTotalQuantity += cart.getQuantity();
                    }
                }
            }

        }

        cartVo.setProductVoList(cartProductVoList);
        cartVo.setSelectedAll(selectedAll);
        cartVo.setCartTotalQuantity(cartTotalQuantity);
        cartVo.setCartTotalPrice(cartTotalPrice);

        return Result.success(cartVo);
    }

    @Override
    public Result<CartVo> update(Integer userId, Integer productId, CartUpdateForm cartUpdateForm) {
        String userCartKey = RedisKeyUtil.getUserCartKey(userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String value = opsForHash.get(userCartKey, String.valueOf(productId));


        if (StringUtils.isEmpty(value)) {
            return Result.error(NOT_THIS_PRODUCT.getData());
        }

        Cart cart = gson.fromJson(value, Cart.class);
        if (cartUpdateForm.getQuantity() != null &&
                cartUpdateForm.getQuantity() >= 0) {
            cart.setQuantity(cartUpdateForm.getQuantity());
        }
        if (cartUpdateForm.getSelected() != null) {
            cart.setProductSelected(cartUpdateForm.getSelected());
        }

        opsForHash.put(userCartKey, String.valueOf(productId), gson.toJson(cart));

        return list(userId);
    }

    @Override
    public Result<CartVo> delete(Integer userId, Integer productId) {
        String userCartKey = RedisKeyUtil.getUserCartKey(userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
        String value = opsForHash.get(userCartKey, String.valueOf(productId));


        if (StringUtils.isEmpty(value)) {
            return Result.error(NOT_THIS_PRODUCT.getData());
        }

        opsForHash.delete(userCartKey, String.valueOf(productId));
        return list(userId);
    }

    @Override
    public Result<CartVo> selectedAll(Integer userId) {
        String userCartKey = RedisKeyUtil.getUserCartKey(userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();

        Map<String, String> entries = opsForHash.entries(userCartKey);
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            cart.setProductSelected(true);
            opsForHash.put(userCartKey, String.valueOf(cart.getProductId()), gson.toJson(cart));
        }

        return list(userId);
    }

    @Override
    public Result<CartVo> unSelectedAll(Integer userId) {
        String userCartKey = RedisKeyUtil.getUserCartKey(userId);
        HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();

        Map<String, String> entries = opsForHash.entries(userCartKey);
        for (Map.Entry<String, String> entry : entries.entrySet()) {
            Cart cart = gson.fromJson(entry.getValue(), Cart.class);
            cart.setProductSelected(false);
            opsForHash.put(userCartKey, String.valueOf(cart.getProductId()), gson.toJson(cart));
        }

        return list(userId);
    }


}
