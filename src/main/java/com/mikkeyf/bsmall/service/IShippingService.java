package com.mikkeyf.bsmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.mikkeyf.bsmall.form.ShippingForm;
import com.mikkeyf.bsmall.pojo.Cart;
import com.mikkeyf.bsmall.pojo.Shipping;
import com.mikkeyf.bsmall.result.Result;

import java.util.Map;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service
 * @className: IShippingService
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/16 22:35
 * @version: 1.0
 */
public interface IShippingService  extends IService<Shipping> {

    Result<Map<String, Integer>> add(Integer userId, ShippingForm form);

    Result delete(Integer userId, Integer shippingId);

    Result update(Integer userId, Integer shippingId, ShippingForm form);

    Result<PageInfo> list(Integer userId, Integer pageNum, Integer pageSize);
}
