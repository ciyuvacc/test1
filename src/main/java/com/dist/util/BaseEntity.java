package com.dist.util;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Transient;

/**
 *
 */
@Data
public class BaseEntity {

    @Transient
    @ApiModelProperty(notes = "当前页默认1",required = true)
    private Integer currentPage = 1;       // 当前页
    @Transient
    @ApiModelProperty(notes = "每页条数默认10",required = true)
    private Integer pageSize = 10; // 每页大小
    @Transient
    private Integer beginLine = (currentPage - 1) * pageSize;           // 起始行

}
