package com.dist.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 */
@ApiModel(description = "用户登录实体")
@Data
public class LoginUser {

    @ApiModelProperty(notes = "登录账号",required = true)
    private String loginNum;
    @ApiModelProperty(notes = "登录密码",required = true)
    private String passWord;
}
