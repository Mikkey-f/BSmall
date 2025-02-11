package com.mikkeyf.bsmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mikkeyf.bsmall.pojo.OrderItem;
import org.apache.ibatis.annotations.Mapper;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.dao
 * @className: OrderItemMapper
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/1/25 0:46
 * @version: 1.0
 */
@Mapper
public interface OrderItemMapper extends BaseMapper<OrderItem> {
}
