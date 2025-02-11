package com.mikkeyf.bsmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mikkeyf.bsmall.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @projectName:    BSmall 
 * @package:        com.mikkeyf.bsmall.dao
 * @className:      CategoryMapper
 * @author:     mikkeyf
 * @description:  TODO  
 * @date:    2025/1/24 21:56
 * @version:    1.0
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {

}
