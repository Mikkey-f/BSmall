package com.mikkeyf.bsmall.service;

import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.vo.CategoryVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@SpringBootTest
class ICategoryServiceTest {

    @Autowired
    private ICategoryService categoryService;

    @Test
    void selectAll() {
        Result<List<CategoryVo>> listResult = categoryService.selectAll();
        Result.success(listResult);
    }

    @Test
    void findSubCategoryId() {
        Set<Integer>set = new HashSet<>();
        categoryService.findSubCategoryId(100001, set);
        log.info("set={}", set);
    }
}