package com.zjh.service;

import com.zjh.domain.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * Created by Jiaheng on 2017/8/20.
 */
@ContextConfiguration("classpath*:/smart-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService){
        userService = this.userService;
    }

    @Test
    public void hasMatchUser(){
        boolean b1 = userService.hasMatchUser("admin","123456");
        boolean b2 = userService.hasMatchUser("admin","1111");
        System.out.println(b1);
        System.out.println(b2);
    }

    @Test
    public void findUserByUserName(){
        User user = userService.findUserByUserName("admin");
        System.out.println(user.getUserName().equals("admin"));
    }


}
