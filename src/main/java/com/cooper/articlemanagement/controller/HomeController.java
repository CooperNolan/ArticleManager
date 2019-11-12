package com.cooper.articlemanagement.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cooper.articlemanagement.service.ArticleService;
import com.cooper.articlemanagement.util.ConfigUtil;
import com.cooper.articlemanagement.util.StringUtil;

@Controller("homeController")
public class HomeController {

    @Autowired
    ArticleService articleService;

    /**
     * 跳转首页
     *
     * @return
     */
    @RequestMapping(value = "/Home", method = RequestMethod.GET)
    public String Home(HttpServletRequest request, Map<String, Object> map) {
        Integer page = StringUtil.getPageToInteger(request.getParameter("page"));
        Integer categoryId = StringUtil.getCategoryToInteger(request.getParameter("category"));
        map.put("categoryMap", ConfigUtil.getCateporyIdAndNameMap());
        map.put("categoryId", categoryId);
        map.put("pageArticle", articleService.selectAll(categoryId, page));
        return "home";
    }
}
