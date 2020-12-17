package com.dist.controller;

import com.dist.constants.BaseConstants;
import com.dist.entity.User;
import com.dist.param.*;
import com.dist.service.UserService;
import com.dist.util.DateUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/dist/user")
public class UserConrroller {

    @Autowired
    private UserService userService;

    private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    /**
     * 用户登录
     *
     * @param loginUser
     */
    @ApiOperation("用户登录")
    @RequestMapping(value = "/userLogin", method = RequestMethod.POST)
    public Map<String, Object> userLogin(@RequestBody LoginUser loginUser) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", BaseConstants.SUCCESS);
        returnMap.put("msg", BaseConstants.SUCCESSMSG);
        log.info("saveUser param:{}" + gson.toJson(loginUser));
        try {
            User user = userService.userLogin(loginUser.getLoginNum(), loginUser.getPassWord());
            if (user != null) {
                if (StringUtils.equals(user.getUserStatus(), "1")) {
                    returnMap.put("userLevel", user.getUserLevel());
                    returnMap.put("model", user.getModel());
                    returnMap.put("userName", user.getUserName());
                } else {
                    returnMap.put("code", BaseConstants.FAIL);
                    returnMap.put("msg", "用户状态存在问题,请联系管理员");
                    return returnMap;
                }
            } else {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "用户名密码错误");
                return returnMap;
            }
        } catch (Exception e) {
            returnMap.put("code", BaseConstants.FAIL);
            returnMap.put("msg", BaseConstants.FAILMSG);
            log.error("userLogin error:{}", e);
        }
        return returnMap;
    }

    /**
     * 保存用户
     *
     * @return
     */
    @ApiOperation("用户保存")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST)
    public Map<String, Object> saveUser(@RequestBody SaveUser saveUser) {
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", BaseConstants.SUCCESS);
        returnMap.put("msg", BaseConstants.SUCCESSMSG);
        log.info("saveUser param:{}" + gson.toJson(saveUser));
        try {
            User user = new User();
            BeanUtils.copyProperties(saveUser, user);
            User queryUser = userService.queryUserByLoginUserName(user.getLoginNum());
            if (queryUser != null) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "该登录账号已经存在");
                return returnMap;
            }
            userService.saveUser(user);
        } catch (Exception e) {
            returnMap.put("code", BaseConstants.FAIL);
            returnMap.put("msg", BaseConstants.FAILMSG);
            log.error("saveUser error:{}", e);
        }
        return returnMap;
    }

    @ApiOperation("用户查询")
    @RequestMapping(value = "/queryUserList", method = RequestMethod.POST)
    public Map<String, Object> queryUserList(@RequestBody UserParam userParam) {
        userParam.setBeginLine((userParam.getCurrentPage() - 1) * userParam.getPageSize());
        log.info("queryUserList param:{}", gson.toJson(userParam));
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", BaseConstants.SUCCESS);
        returnMap.put("msg", BaseConstants.SUCCESSMSG);
        try {
            List<User> list = userService.queryUserList(userParam);
            int count = userService.queryUserListCount(userParam);
            returnMap.put("list", list);
            returnMap.put("count", count);
        } catch (Exception e) {
            returnMap.put("code", BaseConstants.FAIL);
            returnMap.put("msg", BaseConstants.FAILMSG);
            log.error("queryUserList error:{}", e);
        }
        return returnMap;
    }


    @ApiOperation("用户信息更改")
    @RequestMapping(value = "/updateUserByLoginNum", method = RequestMethod.POST)
    public Map<String, Object> updateUserByLoginNum(@RequestBody SaveUser saveUser) {
        log.info("updateUserByLoginNum param:{}", gson.toJson(saveUser));
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", BaseConstants.SUCCESS);
        returnMap.put("msg", BaseConstants.SUCCESSMSG);
        try {
            User queryUser = userService.queryUserByLoginUserName(saveUser.getLoginNum());
            if (queryUser == null) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "该登录账号不存在");
                return returnMap;
            }
            User user = new User();
            BeanUtils.copyProperties(saveUser, user);
            user.setId(queryUser.getId());
            user.setCreateTime(queryUser.getCreateTime());
            userService.updateUserByLoginNum(user);
        } catch (Exception e) {
            returnMap.put("code", BaseConstants.FAIL);
            returnMap.put("msg", BaseConstants.FAILMSG);
            log.error("updateUserByLoginNum error:{}", e);
        }
        return returnMap;
    }

    @ApiOperation("用户移除")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public Map<String, Object> deleteUser(@RequestBody DeleteUser deleteUser) {
        log.info("deleteUser param:{}", gson.toJson(deleteUser));
        Map<String, Object> returnMap = new HashMap<>();
        returnMap.put("code", BaseConstants.SUCCESS);
        returnMap.put("msg", BaseConstants.SUCCESSMSG);
        try {
            User queryUser = userService.queryUserByLoginUserName(deleteUser.getLoginNum());
            if (queryUser == null) {
                returnMap.put("code", BaseConstants.FAIL);
                returnMap.put("msg", "该登录账号不存在");
                return returnMap;
            }
            userService.deleteUser(queryUser);
        } catch (Exception e) {
            returnMap.put("code", BaseConstants.FAIL);
            returnMap.put("msg", BaseConstants.FAILMSG);
            log.error("deleteUser error:{}", e);
        }
        return returnMap;
    }

}
