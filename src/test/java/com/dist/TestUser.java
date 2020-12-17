package com.dist;

import com.dist.entity.User;
import com.dist.service.UserService;
import com.dist.util.DateUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestUser {

    @Autowired
    private UserService userService;


    @Test
    public void addUserTest() {
        User user = new User();
        user.setUserName("1111111111111");
        userService.saveUser(user);
    }

    @Test
    public void testDate() {
        Date orderDate1 = DateUtil.getDateByStr("2020-07-08");
        Date finishDate1 = DateUtil.getDateByStr("2020-07-15");

        Long dateDiff = DateUtil.getDateDiff(orderDate1, new Date());
        Long dateDiff1 = DateUtil.getDateDiff(new Date(), finishDate1);
    }

}
