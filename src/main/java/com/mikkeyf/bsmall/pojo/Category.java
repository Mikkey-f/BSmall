package com.mikkeyf.bsmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.Date;

/**
 * @projectName:    BSmall 
 * @package:        com.mikkeyf.bsmall.pojo
 * @className:      Category
 * @author:     mikkeyf
 * @description:  TODO  
 * @date:    2025/1/24 21:26
 * @version:    1.0
 */
@Data
@TableName("mall_category")
@AllArgsConstructor
public class Category {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer parentId;

    private String name;

    private Integer status;

    //@TableField(value = "1")
    private Integer sortOrder;

    private Date createTime;

    private Date updateTime;
}
