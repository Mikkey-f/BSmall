package com.mikkeyf.bsmall.form;

import lombok.Data;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.form
 * @className: ShippingForm
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/16 22:34
 * @version: 1.0
 */
@Data
public class ShippingForm {
    private String receiverName;

    private String receiverPhone;

    private String receiverMobile;

    private String receiverProvince;

    private String receiverCity;

    private String receiverDistrict;

    private String receiverAddress;

    private String receiverZip;
}
