package com.cooper.articlemanagement.service.impl;

import com.cooper.articlemanagement.dao.UserDao;
import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.myexception.UserRuntimeException;
import com.cooper.articlemanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userDao")
    UserDao userDao;

    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     * @throws UserRuntimeException
     */
    @Override
    public User selectByUserId(Integer userId) throws UserRuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        return userDao.select(map);
    }

    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     * @throws UserRuntimeException
     */
    @Override
    public User selectByUsername(String username) throws UserRuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        return userDao.select(map);
    }

    /**
     * 查询所有用户信息
     * @return
     * @throws UserRuntimeException
     */
    @Override
    public List<User> selectAll() throws UserRuntimeException {
        return userDao.select();
    }

    /**
     * 注册用户
     * @param user
     * @return
     * @throws UserRuntimeException
     */
    @Override
    public int insert(User user) throws UserRuntimeException {
        if (user.getNickname() == null) {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            user.setNickname(uuid.substring(0, 8).toUpperCase());
        }
        user.setCreateTime(new Date());
        user.setLastLogin(new Date());
        user.setUserStatus(0);
        int influences = 0;
        try {
            influences = userDao.insert(user);
        } catch (Exception e) {
            throw new UserRuntimeException(e.toString());
        }
        return influences;
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     * @throws UserRuntimeException
     */
    @Override
    public int update(User user) throws UserRuntimeException {
        user.setUpdateTime(new Date());
        int influences = 0;
        try {
            influences = userDao.update(user);
        } catch (Exception e) {
            throw new UserRuntimeException(e.toString());
        }
        return influences;
    }

    /**
     * 登录时更新用户最新登录时间
     * @param user
     * @return
     * @throws UserRuntimeException
     */
    @Override
    public int updateLoginTime(User user) throws UserRuntimeException {
        user.setLastLogin(new Date());
        int influences = 0;
        try {
            influences = userDao.update(user);
        } catch (Exception e) {
            throw new UserRuntimeException(e.toString());
        }
        return influences;
    }

    /**
     * 根据用户ID删除用户
     * @param userId
     * @return
     * @throws UserRuntimeException
     */
    @Override
    public int delete(Integer userId) throws UserRuntimeException {
        int influences = 0;
        try {
            influences = userDao.delete(userId);
        } catch (Exception e) {
            throw new UserRuntimeException(e.toString());
        }
        return influences;
    }
}
