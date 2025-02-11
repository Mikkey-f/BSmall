package com.mikkeyf.bsmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.mikkeyf.bsmall.pojo.Category;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.vo.CategoryVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service
 * @className: ICategoryService
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/8 20:09
 * @version: 1.0
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 查找所有类别
     * @return
     */
    public Result<List<CategoryVo>> selectAll();


    public void findSubCategoryId(Integer id, Set<Integer> resultSet);
}
