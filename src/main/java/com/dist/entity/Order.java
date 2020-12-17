package com.dist.entity;

import lombok.Data;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 */
@Data
@Table(name = "t_dist_order")
@NameStyle(value = Style.normal)
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //订单编号
    private String orderId;
    //客户名称
    private String customerName;
    //订单地址
    private String orderAddress;
    //订单日期(yyyyy-MM-dd)
    private String orderDate;
    //交货日期(yyyyy-MM-dd)
    private String finishDate;
    //订单状态 (SUCCESS FAIL)
    private String orderStatus;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
    //创建人
    private String createPeople;
}
