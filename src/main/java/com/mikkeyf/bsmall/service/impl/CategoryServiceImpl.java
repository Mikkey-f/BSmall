package com.mikkeyf.bsmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikkeyf.bsmall.dao.CategoryMapper;
import com.mikkeyf.bsmall.dao.UserMapper;
import com.mikkeyf.bsmall.pojo.Category;
import com.mikkeyf.bsmall.pojo.User;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.ICategoryService;
import com.mikkeyf.bsmall.vo.CategoryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

import static com.mikkeyf.bsmall.Constants.BSMallConstants.ROOT_PARENT_ID;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service.impl
 * @className: CategoryServiceImpl
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/8 20:11
 * @version: 1.0
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 获得所有目录
     * @return
     */
    public Result<List<CategoryVo>> selectAll() {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        List<Category> categories = categoryMapper.selectList(wrapper);

        List<CategoryVo> categoryVoList = new ArrayList<>();
        for (Category category : categories) {
            if (category.getParentId().equals(ROOT_PARENT_ID)) {
                categoryVoList.add(categoryToCategoryVo(category));
            }
        }

        categoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
        findSubCategories(categoryVoList, categories);

        return Result.success(categoryVoList);
    }

    @Override
    public void findSubCategoryId(Integer id, Set<Integer> resultSet) {
        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        List<Category> categories = categoryMapper.selectList(wrapper);
        findSubCategory(categories, id, resultSet);
    }

    private void findSubCategory(List<Category> categories , Integer id, Set<Integer> resultSet) {
        for (Category category : categories) {
            if (category.getParentId().equals(id)) {
                resultSet.add(category.getId());

                findSubCategory(categories, category.getId(), resultSet);
            }
        }
    }


    /**
     * 查找各自子目录
     * @param categoryVoList
     * @param categories
     */
    private void findSubCategories(List<CategoryVo> categoryVoList, List<Category> categories) {
        for (CategoryVo categoryVo : categoryVoList) {
            List<CategoryVo> subCategoryVoList = new ArrayList<>();
            for (Category category : categories) {

                if (category.getParentId().equals(categoryVo.getId())) {
                    subCategoryVoList.add(categoryToCategoryVo(category));
                }
            }
            subCategoryVoList.sort(Comparator.comparing(CategoryVo::getSortOrder).reversed());
            categoryVo.setSubCategories(subCategoryVoList);

            findSubCategories(subCategoryVoList, categories);
        }
    }

    /**
     * category对象变为categoryVo对象
     * @param category
     * @return
     */
    private CategoryVo categoryToCategoryVo(Category category) {
        CategoryVo categoryVo = new CategoryVo();
        BeanUtils.copyProperties(category, categoryVo);
        return categoryVo;
    }


}

