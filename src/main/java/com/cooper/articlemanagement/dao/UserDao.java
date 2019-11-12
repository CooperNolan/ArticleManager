package com.cooper.articlemanagement.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cooper.articlemanagement.entity.User;

@Repository("userDao")
public interface UserDao {

    User select(Map<String, Object> map);

    List<User> select();

    int insert(User user);

    int update(User user);

    int delete(@Param("userId") Integer userId);

}
