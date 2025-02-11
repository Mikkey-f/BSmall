package com.mikkeyf.bsmall.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.pojo
 * @className: LoginTicket
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/6 23:34
 * @version: 1.0
 */
@Data
@TableName("login_ticket")
@AllArgsConstructor
@NoArgsConstructor
public class LoginTicket {

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer userId;

    private String ticket;

    private Integer status;

    private Date expired;


}
