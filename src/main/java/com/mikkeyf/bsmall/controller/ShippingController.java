package com.mikkeyf.bsmall.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mikkeyf.bsmall.form.ShippingForm;
import com.mikkeyf.bsmall.pojo.Shipping;
import com.mikkeyf.bsmall.pojo.User;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.IShippingService;
import com.mikkeyf.bsmall.util.HostHolder;
import io.netty.util.concurrent.SucceededFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static com.mikkeyf.bsmall.enums.ResponseEnum.PARAM_ERROR;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.controller
 * @className: ShippingController
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/17 0:05
 * @version: 1.0
 */
@RestController
public class ShippingController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    IShippingService shippingService;

    @PostMapping("/shippings")
    public Result<Map<String, Integer>> add(@RequestBody ShippingForm form) {
        if (StringUtils.isBlank(form.getReceiverAddress()) || StringUtils.isBlank(form.getReceiverCity())
        || StringUtils.isBlank(form.getReceiverDistrict()) || StringUtils.isBlank(form.getReceiverName())
        || StringUtils.isBlank(form.getReceiverPhone()) || StringUtils.isBlank(form.getReceiverZip())
        || StringUtils.isBlank(form.getReceiverMobile()) || StringUtils.isBlank(form.getReceiverProvince())) {
            return Result.error(PARAM_ERROR.getData());
        }
        User user = hostHolder.getUser();
        return shippingService.add(user.getId(), form);
    }

    @DeleteMapping("/shippings/{shippingId}")
    public Result delete(@PathVariable Integer shippingId) {
        User user = hostHolder.getUser();
        return shippingService.delete(user.getId(), shippingId);
    }

    @PutMapping("/shippings/{shippingId}")
    public Result update(@PathVariable Integer shippingId,
                         @RequestBody ShippingForm form) {
        if (StringUtils.isBlank(form.getReceiverAddress()) || StringUtils.isBlank(form.getReceiverCity())
                || StringUtils.isBlank(form.getReceiverDistrict()) || StringUtils.isBlank(form.getReceiverName())
                || StringUtils.isBlank(form.getReceiverPhone()) || StringUtils.isBlank(form.getReceiverZip())
                || StringUtils.isBlank(form.getReceiverMobile()) || StringUtils.isBlank(form.getReceiverProvince())) {
            return Result.error(PARAM_ERROR.getData());
        }
        User user = hostHolder.getUser();
        return shippingService.update(user.getId(), shippingId, form);
    }

    @GetMapping("/shippings")
    public Result list(Integer pageNum, Integer pageSize) {
        User user = hostHolder.getUser();
        return shippingService.list(user.getId(), pageNum, pageSize);
    }
}
