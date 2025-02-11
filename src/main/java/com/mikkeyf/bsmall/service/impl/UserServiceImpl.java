package com.mikkeyf.bsmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikkeyf.bsmall.dao.LoginTicketMapper;
import com.mikkeyf.bsmall.dao.UserMapper;
import com.mikkeyf.bsmall.enums.ResponseEnum;
import com.mikkeyf.bsmall.enums.RoleEnum;
import com.mikkeyf.bsmall.pojo.LoginTicket;
import com.mikkeyf.bsmall.pojo.User;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.IUserService;
import com.mikkeyf.bsmall.util.BSmallUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service.impl
 * @className: UserServiceImpl
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/4 19:44
 * @version: 1.0
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;
    /**
     * 商城用户注册
     * @param user
     * @return
     */
    @Override
    public Result<User> register(User user) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", user.getUsername());
        //username不能重复
        Long count = userMapper.selectCount(query);
        if(count > 0) {
            return Result.error(ResponseEnum.USERNAME_EXIST.getData());
        }
        query.clear();
        //email不能重复
        query.eq("email", user.getEmail());
        count = userMapper.selectCount(query);
        if(count > 0) {
            return Result.error(ResponseEnum.EMAIL_EXIST.getData());
        }

        user.setRole(RoleEnum.CUSTOMER.getCode());

        //MD5
        String s = DigestUtils.md5DigestAsHex(
                user.getPassword().getBytes(StandardCharsets.UTF_8));
        user.setPassword(s);

        //写入数据库
        int resultCount = userMapper.insert(user);
        if (resultCount == 0) {
            return Result.error(ResponseEnum.DATABASE_ERROR.getData());
        }

        return Result.success();
    }

    /**
     * 商城用户登录
     *
     * @param username
     * @param password
     * @param expiredSeconds
     * @return
     */
    @Override
    public Result<LoginTicket> login(String username, String password, int expiredSeconds) {
        QueryWrapper<User> query = new QueryWrapper<>();
        query.eq("username", username);
        User user = userMapper.selectOne(query);
        if (user == null) {
            return Result.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getData());
        }

        if (!user.getPassword().equalsIgnoreCase(DigestUtils.
                md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8)))) {
            return Result.error(ResponseEnum.USERNAME_OR_PASSWORD_ERROR.getData());
        }

        //生成登录凭证
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setUserId(user.getId());
        loginTicket.setTicket(BSmallUtil.generateUUID());
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + expiredSeconds * 1000));
        loginTicketMapper.insert(loginTicket);


        return Result.success(loginTicket);
    }

    /**
     * 查找身份凭证
     * @param ticket
     * @return
     */

    public LoginTicket findLoginTicket(String ticket) {
        QueryWrapper<LoginTicket> query = new QueryWrapper<>();
        query.eq("ticket", ticket);
        LoginTicket loginTicket = loginTicketMapper.selectOne(query);
        return loginTicket;
    }
}
