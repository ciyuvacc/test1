package com.dist.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 *
 */
@ApiModel(description = "订单增加实体")
@Data
public class SaveOrder {

    //订单号
    @ApiModelProperty(notes = "订单号", required = true)
    private String orderId;
    //客户名称
    @ApiModelProperty(notes = "客户名称", required = true)
    private String customerName;
    //订单地址
    @ApiModelProperty(notes = "订单地址", required = true)
    private String orderAddress;
    //订单日期(yyyyy-MM-dd)
    @ApiModelProperty(notes = "订单日期", required = true)
    private String orderDate;
    //交货日期(yyyyy-MM-dd)
    @ApiModelProperty(notes = "交货日期", required = true)
    private String finishDate;
    //订单制作区域
    @ApiModelProperty(notes = "订单制作区域")
    private List<Map<String, Object>> orderAreaList;
    //创建人
    @ApiModelProperty(notes = "创建人", required = true)
    private String createPeople;

}
