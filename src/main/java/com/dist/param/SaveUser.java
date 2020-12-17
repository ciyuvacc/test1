package com.dist.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 */
@ApiModel(description = "用户保存实体")
@Data
public class SaveUser {

    @ApiModelProperty(notes = "登录账号",required = true)
    private String loginNum;
    @ApiModelProperty(notes = "用户密码",required = true)
    private String passWord;
    @ApiModelProperty(notes = "用户姓名",required = true)
    private String userName;
    @ApiModelProperty(notes = "用户级别",required = true)
    private String userLevel;
    @ApiModelProperty(notes = "手机号",required = true)
    private String model;
}
