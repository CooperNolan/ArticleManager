package com.cooper.articlemanagement.test.dao;

import com.cooper.articlemanagement.dao.UserDao;
import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.service.UserService;
import com.cooper.articlemanagement.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserTest extends BaseTest {

    @Autowired
    UserDao userDao;
    @Test
    public void selectAll() {
        List<User> list = new ArrayList<User>();
        list = userDao.select();
        for (User user : list) {
            System.out.println(user);
        }
    }
    @Test
    public void insert() {
        User user = new User("123455", "123456");
        user.setNickname("123");
        user.setCreateTime(new Date());
        user.setUserStatus(0);
        user.setGender("男");
        user.setPhone("12345678912");
        user.setEmail("12345");
        user.setAddress("sadsa");
        user.setUpdateTime(new Date());
        user.setLastLogin(new Date());
        user.setRemark("dsada");
        System.out.println(userDao.insert(user));
        System.out.println(user);
    }
    @Test
    public void update1() {
        User user = new User();
        user.setUserId(2);
        user.setUserStatus(1);
        user.setPhone("123");
        userDao.update(user);
        System.out.println(user);
    }
    @Test
    public void update2() {
        User user = new User();
        user.setUserId(2);
        user.setUserStatus(0);
        user.setPhone("");
        userDao.update(user);
        System.out.println(user);
    }
    @Test
    public void delete() {
        System.out.println(userDao.delete(6));
    }
}
