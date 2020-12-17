package com.dist.mapper;

import com.dist.common.MyMapper;
import com.dist.entity.User;
import com.dist.param.UserParam;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * */
public interface UserMapper extends MyMapper<User> {

    @SelectProvider(type = UserSql.class, method = "queryUserListCount")
    int queryUserListCount(UserParam userParam);

    @SelectProvider(type = UserSql.class, method = "queryUserList")
    List<User> queryUserList(UserParam userParam);
}
