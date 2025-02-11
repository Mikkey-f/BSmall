package com.mikkeyf.bsmall.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.mikkeyf.bsmall.pojo.Product;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.vo.ProductDetailVo;
import com.mikkeyf.bsmall.vo.ProductVo;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service
 * @className: IProductService
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/8 22:47
 * @version: 1.0
 */
public interface IProductService extends IService<Product> {

    Result<PageInfo<ProductVo>> list(Integer categoryId, Integer pageNum, Integer pageSize);

    Result<ProductDetailVo> getProductDetail(Integer productId);
}
