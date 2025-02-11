package com.mikkeyf.bsmall.service.impl;

import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.IProductService;
import com.mikkeyf.bsmall.vo.ProductDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class ProductServiceImplTest {

    @Autowired
    private IProductService productService;

    @Test
    void list() {
        productService.list(null, 1, 3);
        
    }

    @Test
    void getProductDetail() {
        Result<ProductDetailVo> productDetail = productService.getProductDetail(266);
        log.info("productDetail={}", productDetail);
    }
}