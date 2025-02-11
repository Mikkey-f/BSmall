package com.mikkeyf.bsmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mikkeyf.bsmall.pojo.LoginTicket;
import com.mikkeyf.bsmall.pojo.User;
import com.mikkeyf.bsmall.result.Result;
import org.springframework.stereotype.Service;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service
 * @className: IUserService
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/4 19:39
 * @version: 1.0
 * @Description: 对mall_user表进行操作
 */
public interface IUserService extends IService<User> {

    /**
     * 商城用户注册
     * @param user
     * @return
     */
    public Result<User> register(User user);

    /**
     * 商城用户登录
     * @param username
     * @param password
     * @param expiredSeconds
     * @return
     */
    public Result<LoginTicket> login(String username, String password, int expiredSeconds);


}
