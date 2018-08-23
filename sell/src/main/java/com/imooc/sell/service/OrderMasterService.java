package com.imooc.sell.service;

import com.imooc.sell.dto.OrderMasterDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderMasterService {

    /** 创建订单 */
    OrderMasterDto create(OrderMasterDto orderMasterDto);

    /** 查询单个订单 */
    OrderMasterDto findById(String orderId);

    /** 查询订单列表 */
    Page<OrderMasterDto> findList(String buyerOpenid, Pageable pageable);

    /** 取消订单 */
    OrderMasterDto cancell(OrderMasterDto orderMasterDto);

    /** 完结订单 */
    OrderMasterDto finish(OrderMasterDto orderMasterDto);

    /** 支付订单 */
    OrderMasterDto paid(OrderMasterDto orderMasterDto);

}
