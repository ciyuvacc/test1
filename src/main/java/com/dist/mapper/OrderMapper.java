package com.dist.mapper;

import com.dist.common.MyMapper;
import com.dist.entity.Order;
import com.dist.param.OrderParam;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface OrderMapper extends MyMapper<Order> {

    @SelectProvider(type = OrderSql.class, method = "queryOrderList")
    List<Map<String, Object>> queryOrderList(OrderParam orderParam);

    @SelectProvider(type = OrderSql.class, method = "queryOrderListCount")
    int queryOrderListCount(OrderParam orderParam);

}
