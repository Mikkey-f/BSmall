package com.mikkeyf.bsmall.vo;

import lombok.Data;

import java.util.List;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.vo
 * @className: CategoryVo
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/8 20:44
 * @version: 1.0
 */
@Data
public class CategoryVo {

    private Integer id;

    private Integer parentId;

    private String name;

    private Integer sortOrder;

    private List<CategoryVo> subCategories;
}
