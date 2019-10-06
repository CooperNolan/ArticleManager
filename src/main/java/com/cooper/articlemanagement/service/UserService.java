package com.cooper.articlemanagement.service;


import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.myexception.UserRuntimeException;

import java.util.List;

public interface UserService {
    User selectByUserId(Integer userId) throws UserRuntimeException;
    User selectByUsername(String username) throws UserRuntimeException;
    List<User> selectAll() throws UserRuntimeException;
    int insert(User user) throws UserRuntimeException;
    int update(User user) throws UserRuntimeException;
    int updateLoginTime(User user) throws UserRuntimeException;
    int delete(Integer userId) throws UserRuntimeException;
}
