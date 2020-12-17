package com.dist.entity;

import io.swagger.annotations.ApiModelProperty;
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
@Table(name="t_dist_order_area")
@NameStyle(value= Style.normal)
public class OrderArea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //订单编号
    private String orderId;
    //车间二次确认标识
    private String orderWorkShopStatus;
    //订单制作区域
    //柜体
    private String orderAreaCabinet;
    //玻璃层板
    private String orderAreaShelf;
    //玻璃背门
    private String orderAreaBackdoor;
    //移动门
    private String orderAreaMobile;
    //铝框玻璃门
    private String orderAreaAluminum;
    //高光门板
    private String orderAreaHigh;
    //吸塑门板
    private String orderAreaDoorPanel;
    //吸塑做旧
    private String orderAreaGetOld;
    //五金配件
    private String orderAreaFitting;
    //创建时间
    private Date createTime;
    //修改时间
    private Date updateTime;
}
