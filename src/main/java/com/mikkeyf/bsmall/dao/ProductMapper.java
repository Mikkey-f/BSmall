package com.mikkeyf.bsmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mikkeyf.bsmall.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.dao
 * @className: Product
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/1/25 0:47
 * @version: 1.0
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> selectByCategoryIdSet(Set<Integer> categoryIdSet);
}
