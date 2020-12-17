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
@Table(name = "t_dist_user")
@NameStyle(value = Style.normal)
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userName;
    private String loginNum;
    private String passWord;
    private String userLevel;
    private String model;
    //用户状态(1正常  2 移除)
    private String userStatus;
    private Date createTime;
    private Date updateTime;
}
