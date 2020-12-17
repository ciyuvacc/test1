package com.dist.param;

import com.dist.util.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 */
@Data
public class OrderParam extends BaseEntity {

    @ApiModelProperty(notes = "流水号")
    private String orderId;
    @ApiModelProperty(notes = "订单状态")
    private String orderStatus;
    @ApiModelProperty(notes = "创建时间开始")
    private String createTimeStart;
    @ApiModelProperty(notes = "创建时间结束")
    private String createTimeEnd;
    @ApiModelProperty(notes = "客户名称")
    private String customerName;
    @ApiModelProperty(notes = "订单地址")
    private String orderAddress;

}
