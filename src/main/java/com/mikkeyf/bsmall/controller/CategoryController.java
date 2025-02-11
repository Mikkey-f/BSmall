package com.mikkeyf.bsmall.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mikkeyf.bsmall.dao.CategoryMapper;
import com.mikkeyf.bsmall.pojo.Category;
import com.mikkeyf.bsmall.pojo.User;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.ICategoryService;
import com.mikkeyf.bsmall.service.impl.CategoryServiceImpl;
import com.mikkeyf.bsmall.util.HostHolder;
import com.mikkeyf.bsmall.vo.CategoryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mikkeyf.bsmall.Constants.BSMallConstants.ROOT_PARENT_ID;
import static com.mikkeyf.bsmall.enums.ResponseEnum.NEED_LOGIN;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.controller
 * @className: CategoryController
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/8 20:18
 * @version: 1.0
 */
@RestController
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private HostHolder hostHolder;
    @GetMapping("/categories")
    public Result<List<CategoryVo>> selectAll() {
        User user = hostHolder.getUser();
        if (user == null) {
            return Result.error(NEED_LOGIN.getData());
        }

        //categoryService.list(wrapper)
        return categoryService.selectAll();
    }
}
