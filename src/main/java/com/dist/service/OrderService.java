package com.dist.service;

import com.dist.entity.Order;
import com.dist.param.OrderParam;
import com.dist.param.SaveOrder;

import java.util.List;
import java.util.Map;

/**
 * */
public interface OrderService {


    void updateStatusByOrderId(Order order);

    Order queryOrderByOrderId(String orderId);

    List<Map<String, Object>> queryOrderList(OrderParam orderParam);

    int queryOrderListCount(OrderParam orderParam);

    void saveOrder(SaveOrder saveOrder);

    void updateOrderAreaByOrderId(String orderId, String orderStatus, String flag) throws Exception;
}
