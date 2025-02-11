package com.mikkeyf.bsmall.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.pojo
 * @className: payInfo
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/1/25 0:15
 * @version: 1.0
 */
@Data
@TableName("mall_pay_info")
@AllArgsConstructor
public class PayInfo {

    private Integer id;

    private Integer userId;

    private Integer orderNo;

    private Integer payPlatform;

    private String platformNumber;

    private String platformStatus;

    private double payAmount;

    private Date createTime;

    private Date updateTime;
}
