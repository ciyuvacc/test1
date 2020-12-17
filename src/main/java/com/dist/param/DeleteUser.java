package com.dist.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 */
@ApiModel(description = "用户移除实体")
@Data
public class DeleteUser {

    @ApiModelProperty(notes = "流水号",required = true)
    private String loginNum;
}
