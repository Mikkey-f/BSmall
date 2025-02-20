package com.mikkeyf.bsmall.service.impl;

import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.IOrderService;
import com.mikkeyf.bsmall.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
class OrderServiceImplTest {

    @Autowired
    IOrderService orderService;

    @Test
    void create() {
        Result<OrderVo> orderVoResult = orderService.create(5, 13);
        log.info("{}",orderVoResult);
    }
}