package com.cooper.articlemanagement.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cooper.articlemanagement.entity.Category;

@Repository("categoryDao")
public interface CategoryDao {

    Category selectById(@Param("categoryId") Integer categoryId);

    List<Category> selectByStatus(@Param("categoryStatus") Integer categoryStatus);

    List<Category> selectByName(@Param("categoryName") String categoryName);

    int insert(Category category);

    int update(Category category);

    int delete(@Param("categoryId") Integer categoryId);

}
