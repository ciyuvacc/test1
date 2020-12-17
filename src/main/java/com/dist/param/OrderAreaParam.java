package com.dist.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 */
@ApiModel(description = "修改订单制作区域实体")
@Data
public class OrderAreaParam {

    @ApiModelProperty(notes = "流水号",required = true)
    private String orderId;
    @ApiModelProperty(notes = "状态 SUCCESS FAIL ",required = true)
    private String orderStatus;
    @ApiModelProperty(notes = "标识")
    private String flag;
}
