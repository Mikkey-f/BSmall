package com.mikkeyf.bsmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mikkeyf.bsmall.pojo.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.dao
 * @className: OrderMapper
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/1/25 0:45
 * @version: 1.0
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
