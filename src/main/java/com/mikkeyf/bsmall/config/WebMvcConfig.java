package com.mikkeyf.bsmall.config;

import com.fasterxml.jackson.databind.annotation.JsonAppend;
import com.mikkeyf.bsmall.controller.Interceptor.LoginTicketInterceptor;
import com.mikkeyf.bsmall.pojo.LoginTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.config
 * @className: WebMvcConfig
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/7 0:46
 * @version: 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private LoginTicketInterceptor loginTicketInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginTicketInterceptor)
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.png")
                .excludePathPatterns("/**/*.jpg");

//        registry.addInterceptor(loginRequiredInterceptor)
//                .excludePathPatterns("/**/*.css")
//                .excludePathPatterns("/**/*.js")
//                .excludePathPatterns("/**/*.png")
//                .excludePathPatterns("/**/*.jpg");
    }
}
