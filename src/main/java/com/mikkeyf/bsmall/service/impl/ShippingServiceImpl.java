package com.mikkeyf.bsmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mikkeyf.bsmall.dao.CartMapper;
import com.mikkeyf.bsmall.dao.ShippingMapper;
import com.mikkeyf.bsmall.form.ShippingForm;
import com.mikkeyf.bsmall.pojo.Cart;
import com.mikkeyf.bsmall.pojo.Shipping;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.ICartService;
import com.mikkeyf.bsmall.service.IShippingService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mikkeyf.bsmall.enums.ResponseEnum.*;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service.impl
 * @className: ShippingServiceImpl
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/16 22:40
 * @version: 1.0
 */
@Service
public class ShippingServiceImpl extends ServiceImpl<ShippingMapper, Shipping> implements IShippingService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Override
    public Result<Map<String, Integer>> add(Integer userId, ShippingForm form) {
        Shipping shipping = new Shipping();
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(userId);
        int row = shippingMapper.insert(shipping);
        if (row == 0) {
            return Result.error(DATABASE_ERROR.getData());
        }

        HashMap<String, Integer> map = new HashMap<>();
        map.put("shippingId", shipping.getId());

        return Result.success(map);
    }

    @Override
    public Result delete(Integer userId, Integer shippingId) {
        QueryWrapper<Shipping> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", shippingId);
        int row = shippingMapper.delete(queryWrapper);
        if (row == 0) {
            return Result.error(DELETE_SHIPPING_FAIL.getData());
        }

        return Result.success();
    }

    @Override
    public Result update(Integer userId, Integer shippingId, ShippingForm form) {
        Shipping shipping = shippingMapper.selectById(shippingId);
        BeanUtils.copyProperties(form, shipping);
        shipping.setUserId(userId);
        shipping.setId(shippingId);
        QueryWrapper<Shipping> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", shippingId);
        int row = shippingMapper.update(shipping, queryWrapper);
        if (row == 0) {
            return Result.error(UPDATE_SHIPPING_FAIL.getData());
        }
        return Result.success();
    }

    @Override
    public Result<PageInfo> list(Integer userId, Integer pageNum, Integer pageSize) {
        QueryWrapper<Shipping> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);

        List<Shipping> shippings = shippingMapper.selectList(queryWrapper);
        PageHelper.startPage(pageNum, pageSize);

        PageInfo<Shipping> shippingPageInfo = new PageInfo<>(shippings);

        return Result.success(shippingPageInfo);
    }
}
