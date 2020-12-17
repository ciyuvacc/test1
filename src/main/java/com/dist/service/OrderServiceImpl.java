package com.dist.service;

import com.dist.entity.Order;
import com.dist.entity.OrderArea;
import com.dist.mapper.OrderAreaMapper;
import com.dist.mapper.OrderMapper;
import com.dist.param.OrderParam;
import com.dist.param.SaveOrder;
import com.dist.util.FlowIDUtil;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service("orderServiceImpl")
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderAreaMapper orderAreaMapper;

    @Override
    public void updateStatusByOrderId(Order order) {
        if (StringUtils.equals(order.getOrderStatus(), "REMOVE")) {
            Order deleteOrder = new Order();
            deleteOrder.setOrderId(order.getOrderId());
            OrderArea orderArea = new OrderArea();
            orderArea.setOrderId(order.getOrderId());
            orderMapper.delete(deleteOrder);
            orderAreaMapper.delete(orderArea);
        } else {
            orderMapper.updateByPrimaryKeySelective(order);
        }
    }

    @Override
    public Order queryOrderByOrderId(String orderId) {
        Order queryOrder = new Order();
        queryOrder.setOrderId(orderId);
        return orderMapper.selectOne(queryOrder);
    }

    @Override
    public List<Map<String, Object>> queryOrderList(OrderParam orderParam) {
        return orderMapper.queryOrderList(orderParam);
    }

    @Override
    public int queryOrderListCount(OrderParam orderParam) {
        return orderMapper.queryOrderListCount(orderParam);
    }

    @Override
    public void saveOrder(SaveOrder saveOrder) {
        Order order = new Order();
        OrderArea orderArea = new OrderArea();

        BeanUtils.copyProperties(saveOrder, order);
        BeanUtils.copyProperties(saveOrder, orderArea);
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        order.setOrderStatus("FAIL");
        orderMapper.insert(order);

        List<Map<String, Object>> orderAreaList = saveOrder.getOrderAreaList();
        for (Map<String, Object> map : orderAreaList) {
            if (StringUtils.isNotBlank((String) map.get("name"))
                    && StringUtils.equals((String) map.get("name"), "柜体")) {
                orderArea.setOrderAreaCabinet("FAIL");
            }
            if (StringUtils.isNotBlank((String) map.get("name"))
                    && StringUtils.equals((String) map.get("name"), "玻璃层板")) {
                orderArea.setOrderAreaShelf("FAIL");
            }
            if (StringUtils.isNotBlank((String) map.get("name"))
                    && StringUtils.equals((String) map.get("name"), "玻璃背门")) {
                orderArea.setOrderAreaBackdoor("FAIL");
            }
            if (StringUtils.isNotBlank((String) map.get("name"))
                    && StringUtils.equals((String) map.get("name"), "移动门")) {
                orderArea.setOrderAreaMobile("FAIL");
            }
            if (StringUtils.isNotBlank((String) map.get("name"))
                    && StringUtils.equals((String) map.get("name"), "铝框玻璃门")) {
                orderArea.setOrderAreaAluminum("FAIL");
            }
            if (StringUtils.isNotBlank((String) map.get("name"))
                    && StringUtils.equals((String) map.get("name"), "高光门板")) {
                orderArea.setOrderAreaHigh("FAIL");
            }
            if (StringUtils.isNotBlank((String) map.get("name"))
                    && StringUtils.equals((String) map.get("name"), "吸塑门板")) {
                orderArea.setOrderAreaDoorPanel("INIT");
            }
            if (StringUtils.isNotBlank((String) map.get("name"))
                    && StringUtils.equals((String) map.get("name"), "吸塑做旧")) {
                orderArea.setOrderAreaGetOld("FAIL");
            }
            if (StringUtils.isNotBlank((String) map.get("name"))
                    && StringUtils.equals((String) map.get("name"), "五金配件")) {
                orderArea.setOrderAreaFitting("FAIL");
            }
        }
        orderArea.setOrderWorkShopStatus("FAIL");
        orderArea.setCreateTime(new Date());
        orderArea.setUpdateTime(new Date());
        orderAreaMapper.insert(orderArea);
    }

    @Override
    public void updateOrderAreaByOrderId(String orderId, String orderStatus, String flag) throws Exception {
        OrderArea orderArea = new OrderArea();
        orderArea.setUpdateTime(new Date());
        if (StringUtils.equals(flag, "1")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderAreaCabinet("SUCCESS");
            } else {
                orderArea.setOrderAreaCabinet("FAIL");
            }
        }
        if (StringUtils.equals(flag, "2")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderAreaShelf("SUCCESS");
            } else {
                orderArea.setOrderAreaShelf("FAIL");
            }
        }
        if (StringUtils.equals(flag, "3")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderAreaBackdoor("SUCCESS");
            } else {
                orderArea.setOrderAreaBackdoor("FAIL");
            }
        }
        if (StringUtils.equals(flag, "4")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderAreaMobile("SUCCESS");
            } else {
                orderArea.setOrderAreaMobile("FAIL");
            }
        }
        if (StringUtils.equals(flag, "5")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderAreaAluminum("SUCCESS");
            } else {
                orderArea.setOrderAreaAluminum("FAIL");
            }
        }
        if (StringUtils.equals(flag, "6")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderAreaHigh("SUCCESS");
            } else {
                orderArea.setOrderAreaHigh("FAIL");
            }
        }
        if (StringUtils.equals(flag, "7")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderAreaDoorPanel("SUCCESS");
            } else {
                orderArea.setOrderAreaDoorPanel("FAIL");
            }
        }
        if (StringUtils.equals(flag, "8")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderAreaGetOld("SUCCESS");
            } else {
                orderArea.setOrderAreaGetOld("FAIL");
            }
        }
        if (StringUtils.equals(flag, "9")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderAreaFitting("SUCCESS");
            } else {
                orderArea.setOrderAreaFitting("FAIL");
            }
        }
        if (StringUtils.equals(flag, "10")) {
            if (StringUtils.equals("SUCCESS", orderStatus)) {
                orderArea.setOrderWorkShopStatus("SUCCESS");
            } else {
                orderArea.setOrderWorkShopStatus("FAIL");
            }
        }
        Example example = new Example(OrderArea.class);
        example.createCriteria().andEqualTo("orderId", orderId);
        orderAreaMapper.updateByExampleSelective(orderArea, example);
    }
}
