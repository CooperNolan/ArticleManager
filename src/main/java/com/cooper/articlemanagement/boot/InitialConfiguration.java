package com.cooper.articlemanagement.boot;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cooper.articlemanagement.service.ArticleService;
import com.cooper.articlemanagement.service.CategoryService;

@Component("initConfig")
public class InitialConfiguration implements InitializingBean {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    @Override
    public void afterPropertiesSet() throws Exception {
        categoryService.selectByZeroStatusCache();
    }
}
