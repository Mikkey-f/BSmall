package com.mikkeyf.bsmall.service;

import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.vo.OrderVo;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service
 * @className: IOrderService
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/18 21:20
 * @version: 1.0
 */
public interface IOrderService {

    Result<OrderVo> create(Integer userId, Integer shippingId);
}
