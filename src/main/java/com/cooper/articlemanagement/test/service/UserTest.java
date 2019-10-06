package com.cooper.articlemanagement.test.service;

import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.service.UserService;
import com.cooper.articlemanagement.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserTest extends BaseTest {
    @Autowired
    UserService userService;

    @Test
    public void selectTest1(){
        User user = userService.selectByUsername("admin");
        System.out.println(user);
    }
    @Test
    public void selectTest2(){
        User user = userService.selectByUserId(1);
        System.out.println(user);
    }
    @Test
    public void selectTest3(){
        List<User> users = userService.selectAll();
        System.out.println(users.size());
    }
}
