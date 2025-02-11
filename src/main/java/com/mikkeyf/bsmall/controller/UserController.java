package com.mikkeyf.bsmall.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mikkeyf.bsmall.enums.ResponseEnum;
import com.mikkeyf.bsmall.form.UserLoginForm;
import com.mikkeyf.bsmall.form.UserRegisterForm;
import com.mikkeyf.bsmall.pojo.LoginTicket;
import com.mikkeyf.bsmall.pojo.User;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.IUserService;
import com.mikkeyf.bsmall.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.mikkeyf.bsmall.Constants.BSMallConstants.DEFAULT_EXPIRED_SECONDS;
import static com.mikkeyf.bsmall.Constants.BSMallConstants.REMEMBER_EXPIRED_SECONDS;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.controller
 * @className: UserController
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/4 20:55
 * @version: 1.0
 */
@RestController
@Slf4j
public class UserController {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Autowired
    private IUserService userService;

    @PostMapping("/user/register")
    public Result<User> register(@RequestBody UserRegisterForm userRegisterForm) {

        if (StringUtils.isBlank(userRegisterForm.getUsername())) {
            log.error("用户名为空");
            return Result.error(ResponseEnum.PARAM_ERROR.getData());
        }
        if (StringUtils.isBlank(userRegisterForm.getPassword())) {
            log.error("密码为空");
            return Result.error(ResponseEnum.PARAM_ERROR.getData());
        }
        if (StringUtils.isBlank(userRegisterForm.getEmail())) {
            log.error("邮箱为空");
            return Result.error(ResponseEnum.PARAM_ERROR.getData());
        }

        User user = new User();
        BeanUtils.copyProperties(userRegisterForm, user);

        return userService.register(user);
    }

    @PostMapping("/user/login")
    public Result<User> login(@RequestBody UserLoginForm userLoginForm,
                              HttpServletResponse response) {

        if (StringUtils.isBlank(userLoginForm.getUsername())) {
            log.error("用户名为空");
            return Result.error(ResponseEnum.PARAM_ERROR.getData());
        }
        if (StringUtils.isBlank(userLoginForm.getPassword())) {
            log.error("密码为空");
            return Result.error(ResponseEnum.PARAM_ERROR.getData());
        }

        int expiredSeconds = userLoginForm.isRememberMe() ? REMEMBER_EXPIRED_SECONDS : DEFAULT_EXPIRED_SECONDS;
        Result<LoginTicket> login = userService.login(userLoginForm.getUsername(), userLoginForm.getPassword(), expiredSeconds);

        Cookie cookie = new Cookie("ticket", login.getData().getTicket());
        cookie.setMaxAge(expiredSeconds);
        cookie.setPath(contextPath);
        response.addCookie(cookie);

        return Result.success();

    }

}
