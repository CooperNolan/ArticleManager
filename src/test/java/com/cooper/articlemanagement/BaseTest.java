package com.cooper.articlemanagement;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 */
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
// 告诉junit spring配置文件的位置
@ContextConfiguration({ "classpath:spring-*.xml"})
public class BaseTest {
    @Test
    public void BaseTest(){
        System.out.println("测试配置类");
    }
}