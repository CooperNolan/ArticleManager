package com.cooper.articlemanagement.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cooper.articlemanagement.service.ArticleService;
import com.cooper.articlemanagement.util.ConfigUtil;
import com.cooper.articlemanagement.util.StringUtil;

@Controller("homeController")
public class HomeController extends BaseModelAttribute {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    ArticleService articleService;

    /**
     * 跳转首页
     * 
     * @param map
     * @return
     */
    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String Home(Map<String, Object> map) {
        Integer page = StringUtil.getPageToInteger(request.getParameter("page"));
        Integer categoryId = StringUtil.getCategoryToInteger(request.getParameter("category"));
        map.put("categoryMap", ConfigUtil.getCategoryIdAndNameMap());
        map.put("categoryId", categoryId);
        map.put("pageArticle", articleService.selectAll(categoryId, page));
        return "home";
    }

    /**
     * 跳转错误页面
     *
     * @return
     */
    @RequestMapping(value = "/Error", method = RequestMethod.GET)
    public String Error() {
        return "error/error";
    }
}
