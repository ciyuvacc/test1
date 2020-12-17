package com.dist.mapper;

import com.dist.param.OrderParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

/**
 */
@Slf4j
public class OrderSql {


    public String queryOrderList(OrderParam orderParam) {
        StringBuffer sql = new StringBuffer("");
        sql.append("select t1.*,t2.* from t_dist_order t1 LEFT JOIN t_dist_order_area t2 ON  t1.orderId = t2.orderId  where 1=1 ");
        if (StringUtils.isNotBlank(orderParam.getOrderId())) {
            sql.append("and t1.orderId like '%");
            sql.append(orderParam.getOrderId());
            sql.append("%' ");
        }
        if (StringUtils.isNotBlank(orderParam.getOrderAddress())) {
            sql.append("and t1.orderAddress like '%");
            sql.append(orderParam.getOrderAddress());
            sql.append("%' ");
        }
        if (StringUtils.isNotBlank(orderParam.getCustomerName())) {
            sql.append("and t1.customerName like '%");
            sql.append(orderParam.getCustomerName());
            sql.append("%' ");
        }

        if (StringUtils.isNotBlank(orderParam.getCreateTimeStart())) {
            sql.append("and t1.orderDate >=  #{createTimeStart} ");
        }
        if (StringUtils.isNotBlank(orderParam.getCreateTimeEnd())) {
            sql.append("and t1.orderDate <=  #{createTimeEnd} ");
        }
        if (StringUtils.isNotBlank(orderParam.getOrderStatus())) {
            sql.append("and t1.orderStatus =  #{orderStatus} ");
        }
        sql.append(" order by t1.id desc");
        sql.append(" limit ");
        sql.append(orderParam.getBeginLine() == null ? 0 : orderParam.getBeginLine());
        sql.append(",");
        sql.append(orderParam.getPageSize() == null ? 10 : orderParam.getPageSize());
        log.info("queryOrderList sql :" + sql.toString());
        return sql.toString();
    }


    public String queryOrderListCount(OrderParam orderParam) {
        StringBuffer sql = new StringBuffer("");
        sql.append("select count(*) from t_dist_order t1 LEFT JOIN t_dist_order_area t2 ON  t1.orderId = t2.orderId  where 1=1 ");
        if (StringUtils.isNotBlank(orderParam.getOrderId())) {
            sql.append("and t1.orderId like '%");
            sql.append(orderParam.getOrderId());
            sql.append("%' ");
        }
        if (StringUtils.isNotBlank(orderParam.getOrderAddress())) {
            sql.append("and t1.orderAddress like '%");
            sql.append(orderParam.getOrderAddress());
            sql.append("%' ");
        }
        if (StringUtils.isNotBlank(orderParam.getCustomerName())) {
            sql.append("and t1.customerName like '%");
            sql.append(orderParam.getCustomerName());
            sql.append("%' ");
        }

        if (StringUtils.isNotBlank(orderParam.getCreateTimeStart())) {
            sql.append("and t1.orderDate >=  #{createTimeStart} ");
        }
        if (StringUtils.isNotBlank(orderParam.getCreateTimeEnd())) {
            sql.append("and t1.orderDate <=  #{createTimeEnd} ");
        }
        if (StringUtils.isNotBlank(orderParam.getOrderStatus())) {
            sql.append("and t1.orderStatus =  #{orderStatus} ");
        }
        log.info("queryOrderListCount sql :" + sql.toString());
        return sql.toString();
    }


}
