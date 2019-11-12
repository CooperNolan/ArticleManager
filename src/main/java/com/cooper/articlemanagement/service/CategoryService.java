package com.cooper.articlemanagement.service;

import java.util.List;

import com.cooper.articlemanagement.entity.Category;
import com.cooper.articlemanagement.myexception.MyRuntimeException;

public interface CategoryService {
    Category selectByCategoryId(Integer categoryId);

    List<Category> selectByCategoryStatus(Integer categoryStatus);

    void selectByZeroStatusCache();

    int insert(Category category) throws MyRuntimeException;

    int update(Category category) throws MyRuntimeException;

    int delete(Integer categoryId) throws MyRuntimeException;
}
