package com.dist.service;

import com.dist.entity.User;
import com.dist.param.UserParam;

import java.util.List;

/**
 * */
public interface UserService {


    User userLogin(String loginNum, String passWord);

    void saveUser(User user);

    User queryUserByLoginUserName(String loginNum);

    List<User> queryUserList(UserParam userParam);

    int queryUserListCount(UserParam userParam);

    void updateUserByLoginNum(User user);

    void deleteUser(User queryUser);
}
