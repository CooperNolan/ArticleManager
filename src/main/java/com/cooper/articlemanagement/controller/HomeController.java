package com.cooper.articlemanagement.controller;

import com.cooper.articlemanagement.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller("homeController")
public class HomeController {

    @Autowired
    ArticleService articleService;
    /**
     * 跳转首页
     * @return
     */
    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String Home(Map<String, Object> map) {
        map.put("pageArticle", articleService.selectAll(1));
        return "home";
    }
    @RequestMapping(value = "/Home/{page}", method = RequestMethod.GET)
    public String pageHome(@PathVariable(value = "page") Integer page,Map<String, Object> map) {
        if (page <= 0) {
            return "home";
        }
        map.put("pageArticle", articleService.selectAll(page));
        return "home";
    }
}
