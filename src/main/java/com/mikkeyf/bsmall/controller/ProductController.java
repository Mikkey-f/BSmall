package com.mikkeyf.bsmall.controller;

import com.github.pagehelper.PageInfo;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.IProductService;
import com.mikkeyf.bsmall.vo.ProductDetailVo;
import com.mikkeyf.bsmall.vo.ProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.controller
 * @className: ProductController
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/10 20:15
 * @version: 1.0
 */
@RestController
public class ProductController {

    @Autowired
    IProductService productService;


    @GetMapping("/products")
    public Result<PageInfo<ProductVo>> list(@RequestParam(required = false) Integer categoryId,
                                            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        return productService.list(categoryId, pageNum, pageSize);
    }

    @GetMapping("/products/{id}")
    public Result<ProductDetailVo> list(@PathVariable Integer id) {

        return productService.getProductDetail(id);
    }
}
