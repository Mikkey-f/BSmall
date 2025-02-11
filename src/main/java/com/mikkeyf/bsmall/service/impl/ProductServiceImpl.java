package com.mikkeyf.bsmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mikkeyf.bsmall.dao.ProductMapper;
import com.mikkeyf.bsmall.pojo.Product;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.ICategoryService;
import com.mikkeyf.bsmall.service.IProductService;
import com.mikkeyf.bsmall.vo.ProductDetailVo;
import com.mikkeyf.bsmall.vo.ProductVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.mikkeyf.bsmall.enums.ProductStatusEnum.DELETE;
import static com.mikkeyf.bsmall.enums.ProductStatusEnum.OFF_SALE;
import static com.mikkeyf.bsmall.enums.ResponseEnum.NOT_THIS_PRODUCT;
import static com.mikkeyf.bsmall.enums.ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service.impl
 * @className: ProductServiceImpl
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/8 22:49
 * @version: 1.0
 */
@Service
@Slf4j
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Result<PageInfo<ProductVo>> list(Integer categoryId, Integer pageNum, Integer pageSize) {

        Set<Integer> categoryIdset = new HashSet<>();

        if (categoryId != null) {
            categoryService.findSubCategoryId(categoryId, categoryIdset);
            categoryIdset.add(categoryId);
        }

        PageHelper.startPage(pageNum, pageSize);
        categoryIdset = categoryIdset.isEmpty() ? null : categoryIdset;
        List<Product> products = productMapper.selectByCategoryIdSet(categoryIdset);

        List<ProductVo> productVoList = products.stream()
                .map(e -> {
                    ProductVo productVo = new ProductVo();
                    BeanUtils.copyProperties(e, productVo);
                    return productVo;
                })
                .collect(Collectors.toList());

        PageInfo<ProductVo> pageInfo = new PageInfo<>(productVoList);
        pageInfo.setList(productVoList);
        return Result.success(pageInfo);
    }

    @Override
    public Result<ProductDetailVo> getProductDetail(Integer productId) {

        Product product = productMapper.selectById(productId);
        if (product == null) {
            return Result.error(NOT_THIS_PRODUCT.getData());
        }

        if (product.getStatus().equals(OFF_SALE.getCode())
                || product.getStatus().equals(DELETE.getCode())) {
            return Result.error(PRODUCT_OFF_SALE_OR_DELETE.getData());
        }

        ProductDetailVo productDetailVo = new ProductDetailVo();
        BeanUtils.copyProperties(product, productDetailVo);

        return Result.success(productDetailVo);
    }
}
