package com.cooper.articlemanagement.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cooper.articlemanagement.service.ArticleService;
import com.cooper.articlemanagement.service.CategoryService;
import com.cooper.articlemanagement.util.ConfigUtil;

@Component("initConfig")
public class InitialConfiguration implements InitializingBean {

    private static Logger logger = LoggerFactory.getLogger(InitialConfiguration.class);

    @Autowired
    CategoryService categoryService;

    @Autowired
    ArticleService articleService;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info(">>>>>>>initialization configuration start...");
        Long start = System.currentTimeMillis();
        try {
            /************* initialization configuration *************/

            ConfigUtil.init();
            categoryService.selectByZeroStatusCache();

            /************* initialization configuration *************/
        } catch (Exception e) {
            logger.info(">>>>>>>initialization configuration error(耗时:{}ms)...", System.currentTimeMillis() - start);
            throw e;
        }
        logger.info(">>>>>>>initialization configuration end(耗时:{}ms)...", System.currentTimeMillis() - start);
    }
}
