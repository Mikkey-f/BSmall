package com.mikkeyf.bsmall.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;
/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.config
 * @className: MyMetaObjectHandler
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/16 23:01
 * @version: 1.0
 */


@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    // 插入时填充
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date()); // 插入时填充创建时间
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date()); // 插入时填充更新时间
    }

    // 更新时填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date()); // 更新时填充更新时间
    }
}

