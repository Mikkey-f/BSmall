package com.mikkeyf.bsmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mikkeyf.bsmall.Constants.BSMallConstants;
import com.mikkeyf.bsmall.dao.*;
import com.mikkeyf.bsmall.enums.OrderStatusEnum;
import com.mikkeyf.bsmall.enums.PaymentTypeEnum;
import com.mikkeyf.bsmall.enums.ProductStatusEnum;
import com.mikkeyf.bsmall.enums.ResponseEnum;
import com.mikkeyf.bsmall.pojo.*;
import com.mikkeyf.bsmall.result.Result;
import com.mikkeyf.bsmall.service.ICartService;
import com.mikkeyf.bsmall.service.IOrderService;
import com.mikkeyf.bsmall.service.IProductService;
import com.mikkeyf.bsmall.vo.CartProductVo;
import com.mikkeyf.bsmall.vo.CartVo;
import com.mikkeyf.bsmall.vo.OrderItemVo;
import com.mikkeyf.bsmall.vo.OrderVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static com.mikkeyf.bsmall.enums.ResponseEnum.SHIPPING_NOT_EXIST;

/**
 * @projectName: BSmall
 * @package: com.mikkeyf.bsmall.service.impl
 * @className: OrderServiceImpl
 * @author: mikkeyf
 * @description: TODO
 * @date: 2025/2/18 21:23
 * @version: 1.0
 */
@Transactional
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

    @Autowired
    private ShippingMapper shippingMapper;

    @Autowired
    private ICartService cartService;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Result<OrderVo> create(Integer userId, Integer shippingId) {
        // 收货地址校验
        QueryWrapper<Shipping> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("id", shippingId);
        Shipping shipping = shippingMapper.selectOne(queryWrapper);
        if (shipping == null) {
            return Result.error(SHIPPING_NOT_EXIST.getData());
        }

        // 获取购物车，校验
        Result<CartVo> result = cartService.list(userId);
        List<CartProductVo> list = new ArrayList<>();
        for (CartProductVo cartProductVo : result.getData().getProductVoList()) {
            if (cartProductVo.getProductSelected()) {
                list.add(cartProductVo);
            }
        }
        if (CollectionUtils.isEmpty(list)) {
            return Result.error(ResponseEnum.NOT_SELECTED_ITEM.getData());
        }

        Set<Integer> productIds = list.stream().map(CartProductVo::getProductId).collect(Collectors.toSet());
        List<Integer> ids = new ArrayList<>(productIds);
        List<Product> products = productMapper.selectBatchIds(ids);
        Map<Integer, Product> map = products.stream().collect(
                Collectors.toMap(Product::getId, product -> product));


        List<OrderItem> orderItemList = new ArrayList<>();
        String orderNo = UUID.randomUUID().toString();
        for (CartProductVo cartProductVo : list) {
            Product product = map.get(cartProductVo.getProductId());
            if (product == null) {
                return Result.error(ResponseEnum.NOT_THIS_PRODUCT.getData() +
                        "商品名字为：" + cartProductVo.getProductName());
            }
            if (!ProductStatusEnum.ON_SALE.getCode().equals(product.getStatus())) {
                return Result.error(ResponseEnum.PRODUCT_OFF_SALE_OR_DELETE.getData() +
                        product.getName());
            }
            if (product.getStock() < cartProductVo.getQuantity()) {
                return Result.error(ResponseEnum.ITEM_STOCK_NOT_ADEQUATE.getData() +
                        "商品名字为：" + cartProductVo.getProductName());
            }

            OrderItem orderItem = buildOrderItem(userId, product, cartProductVo, orderNo);
            orderItemList.add(orderItem);
        }
        // 计算总价，只计算选中商品
        Order order = buildOrder(orderItemList, orderNo, userId, shippingId);
        int rowForOrder = orderMapper.insert(order);
        if (rowForOrder <= 0) {
            return Result.error(ResponseEnum.DATABASE_ERROR.getData());
        }
        orderItemList.forEach(orderItem -> {
            orderItem.setCreateTime(new Date());
            orderItem.setUpdateTime(new Date());
        });
        int rowForOrderItem = orderItemMapper.batchInsert(orderItemList);
        if (rowForOrderItem <= 0) {
            return Result.error(ResponseEnum.DATABASE_ERROR.getData());
        }
        // 生成订单，入库

        // 减库存

        // 更新购物车（选中的商品）

        // 构造orderVo
        return null;
    }

    private Order buildOrder(List<OrderItem> orderItemList, String orderNo, Integer userId, Integer shippingId) {
        BigDecimal payment = orderItemList.stream().map(OrderItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setUserId(userId);
        order.setOrderNo(orderNo);
        order.setShippingId(shippingId);
        order.setPayment(payment);
        order.setPaymentType(PaymentTypeEnum.PAY_ONLINE.getCode());
        order.setPostage(0);
        order.setStatus(OrderStatusEnum.NO_PAY.getCode());
        return order;
    }

    private OrderItem buildOrderItem(Integer userId, Product product, CartProductVo cartProductVo, String orderNo) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderNo(orderNo);
        orderItem.setQuantity(cartProductVo.getQuantity());
        orderItem.setProductId(product.getId());
        orderItem.setProductImage(product.getMainImage());
        orderItem.setUserId(userId);
        orderItem.setTotalPrice(cartProductVo.getProductTotalPrice());
        orderItem.setProductName(product.getName());
        orderItem.setCurrentUnitPrice(product.getPrice());
        return orderItem;
    }
}
