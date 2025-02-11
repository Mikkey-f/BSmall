package com.mikkeyf.bsmall.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mikkeyf.bsmall.pojo.Category;
import com.mikkeyf.bsmall.pojo.LoginTicket;
import org.apache.ibatis.annotations.Mapper;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.dao
 * @className: LoginTicketMapper
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/6 23:37
 * @version: 1.0
 */
@Mapper
public interface LoginTicketMapper extends BaseMapper<LoginTicket> {
}
