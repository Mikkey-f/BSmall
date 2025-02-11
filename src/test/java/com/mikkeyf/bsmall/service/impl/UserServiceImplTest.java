package com.mikkeyf.bsmall.service.impl;

import com.mikkeyf.bsmall.enums.RoleEnum;
import com.mikkeyf.bsmall.pojo.User;
import com.mikkeyf.bsmall.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.management.Query;
import javax.management.relation.Role;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    void register() {
        User user = new User("simth", "1234", "123456@qq.com", RoleEnum.ADMIN.getCode());
        userService.register(user);
    }
}