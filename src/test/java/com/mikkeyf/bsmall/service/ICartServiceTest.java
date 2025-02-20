package com.mikkeyf.bsmall.service;

import com.google.gson.Gson;
import com.mikkeyf.bsmall.form.CartAddForm;
import com.mikkeyf.bsmall.form.CartUpdateForm;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.vo.CartVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ICartServiceTest {

    @Autowired
    private ICartService cartService;

    private Gson gson = new Gson();

    @Test
    public void add() {
        CartAddForm cartAddForm = new CartAddForm();
        cartAddForm.setProductId(28);
        cartAddForm.setSelected(true);
        Result<CartVo> add = cartService.add(1, cartAddForm);
        System.out.println(add);
    }

    @Test
    public void list() {
        Result<CartVo> list = cartService.list(1);
        log.info("list={}", gson.toJson(list));
    }

    @Test
    public void update() {
        Result<CartVo> list = cartService.update(1, 26, new CartUpdateForm(10));
        log.info("list={}", gson.toJson(list));
    }

    @Test
    public void delete() {
        Result<CartVo> list = cartService.delete(1, 26);
        log.info("list={}", gson.toJson(list));
    }

    @Test
    public void selectedAll() {
        Result<CartVo> list = cartService.selectedAll(1);
        log.info("list={}", gson.toJson(list));
    }

    @Test
    public void unSelectedAll() {
        Result<CartVo> list = cartService.unSelectedAll(1);
        log.info("list={}", gson.toJson(list));
    }
}