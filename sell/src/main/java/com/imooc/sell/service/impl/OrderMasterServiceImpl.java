package com.imooc.sell.service.impl;

import com.imooc.sell.dataobject.OrderDetail;
import com.imooc.sell.dataobject.OrderMaster;
import com.imooc.sell.dataobject.ProductInfo;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.dto.OrderMasterDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.repository.OrderDetailRepository;
import com.imooc.sell.repository.OrderMasterRepository;
import com.imooc.sell.service.OrderMasterService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.util.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Override
    public OrderMasterDto create(OrderMasterDto orderMasterDto) {

        String orderId = KeyUtil.genUniqueKey();
        BigDecimal orderAmound = new BigDecimal(BigInteger.ZERO);

        //1.查询商品(数量，价格)
        for (OrderDetail orderDetail :
                orderMasterDto.getOrderDetailList()) {
            ProductInfo productInfo = productInfoService.findById(orderDetail.getDetailId());
            if (productInfo == null) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            orderAmound = orderDetail.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmound);
            //订单详情入库
            orderDetail.setDetailId(KeyUtil.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo, orderDetail);
            orderDetailRepository.save(orderDetail);
        }

        //3.写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmound);
        BeanUtils.copyProperties(orderMasterDto, orderMaster);
        orderMasterRepository.save(orderMaster);

        //4.扣库存
        List<CartDto> cartDtoList = orderMasterDto.getOrderDetailList().stream().map(e ->
            new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productInfoService.decreaseStock(cartDtoList);
        return orderMasterDto;
    }

    @Override
    public OrderMasterDto findById(String orderId) {
        return null;
    }

    @Override
    public Page<OrderMasterDto> findList(String buyerOpenid, Pageable pageable) {
        return null;
    }

    @Override
    public OrderMasterDto cancell(OrderMasterDto orderMasterDto) {
        return null;
    }

    @Override
    public OrderMasterDto finish(OrderMasterDto orderMasterDto) {
        return null;
    }

    @Override
    public OrderMasterDto paid(OrderMasterDto orderMasterDto) {
        return null;
    }
}
