package com.mikkeyf.bsmall.service.impl;

import com.github.pagehelper.PageInfo;
import com.mikkeyf.bsmall.form.ShippingForm;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.IShippingService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ShippingServiceImplTest {

    @Autowired
    private IShippingService shippingService;

    private Integer userId = 1;

    @Test
    void add() {
        ShippingForm shippingForm = new ShippingForm();
        shippingForm.setReceiverAddress("nnn");
        shippingForm.setReceiverCity("sc");
        shippingForm.setReceiverDistrict("nnn");
        shippingForm.setReceiverMobile("nnn");
        shippingForm.setReceiverName("nnn");
        shippingForm.setReceiverZip("6666");
        Result<Map<String, Integer>> add = shippingService.add(userId, shippingForm);
        log.info("add={}", add);
    }

    @Test
    void delete() {
        shippingService.delete(userId, 5);
    }

    @Test
    void update() {
        ShippingForm shippingForm = new ShippingForm();
        shippingForm.setReceiverAddress("hahaha");
        shippingForm.setReceiverCity("hahaha");
        shippingForm.setReceiverDistrict("vhahaha");
        shippingForm.setReceiverMobile("hahaha");
        shippingForm.setReceiverName("hahaha");
        shippingForm.setReceiverZip("hahaha");
        Result<Map<String, Integer>> update = shippingService.update(userId, 12, shippingForm);
        log.info("update={}", update);
    }

    @Test
    void list() {
        Result<PageInfo> list = shippingService.list(userId, 1, 10);
        log.info("list={}", list);
    }
}