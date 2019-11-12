package com.cooper.articlemanagement.service;

import java.util.List;

import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.myexception.MyRuntimeException;

public interface UserService {
    User selectByUserId(Integer userId);

    User selectByUsername(String username);

    List<User> selectAll();

    int insert(User user) throws MyRuntimeException;

    int update(User user) throws MyRuntimeException;

    int updateLoginTime(User user) throws MyRuntimeException;

    int delete(Integer userId) throws MyRuntimeException;

}
