package com.dist.service;

import com.dist.entity.User;
import com.dist.mapper.UserMapper;
import com.dist.param.UserParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User userLogin(String loginNum, String passWord) {
        User queryUser = new User();
        queryUser.setLoginNum(loginNum);
        queryUser.setPassWord(passWord);
        return userMapper.selectOne(queryUser);
    }

    @Override
    public void saveUser(User user) {
        user.setCreateTime(new Date());
        user.setUserStatus("1");
        userMapper.insert(user);
    }

    @Override
    public User queryUserByLoginUserName(String loginNum) {
        User queryUser = new User();
        queryUser.setLoginNum(loginNum);
        return userMapper.selectOne(queryUser);
    }

    @Override
    public List<User> queryUserList(UserParam userParam) {
        return userMapper.queryUserList(userParam);
    }

    @Override
    public int queryUserListCount(UserParam userParam) {
        return userMapper.queryUserListCount(userParam);
    }

    @Override
    public void updateUserByLoginNum(User user) {
        user.setUserStatus("1");
        user.setUpdateTime(new Date());
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUser(User queryUser) {
        userMapper.deleteByPrimaryKey(queryUser.getId());
    }
}
