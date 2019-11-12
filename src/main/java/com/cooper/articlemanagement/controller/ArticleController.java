package com.cooper.articlemanagement.controller;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cooper.articlemanagement.entity.Article;
import com.cooper.articlemanagement.entity.Category;
import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.enums.ArticleStateEnum;
import com.cooper.articlemanagement.service.ArticleService;
import com.cooper.articlemanagement.service.ReplyService;
import com.cooper.articlemanagement.service.UserService;
import com.cooper.articlemanagement.util.ConfigUtil;
import com.cooper.articlemanagement.util.GetPathUtil;
import com.cooper.articlemanagement.util.ResponseBodyUtil;
import com.cooper.articlemanagement.util.StringUtil;

@Controller("articleController")
@RequestMapping("Article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ReplyService replyService;
    @Autowired
    UserService userService;

    /**
     * 跳转编写文章页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/WriteArticle", method = RequestMethod.GET)
    public String writeArticle(Map<String, Object> map) {
        map.put("categoryMap", ConfigUtil.getCateporyIdAndNameMap());
        map.put("TYPE", ArticleStateEnum.WRITE_ARTICLE.getMsgOrUrl());
        return "article/write-article";
    }

    /**
     * 根据文章ID 获取文章并跳转查看页面
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/ShowArticle", method = RequestMethod.GET)
    public String showArticle(HttpServletRequest request, Map<String, Object> map) {
        Integer articleId;
        try {
            articleId = Integer.parseInt(request.getParameter("articleId"));
        } catch (Exception e) {
            return "redirect:/Home";
        }
        map.put("article", articleService.selectByArticleId(articleId));
        map.put("replyList", replyService.selectByArticleId(articleId));
        System.out.println("[" + new Date() + "] 查看文章" + articleId);
        return "article/show-article";
    }

    /**
     * 跳转修改页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/ModifyArticle/{articleId}", method = RequestMethod.GET)
    public String toModifyArticle(@PathVariable(value = "articleId") Integer articleId, Map<String, Object> map,
        HttpSession session, HttpServletRequest request) {
        User user = (User)session.getAttribute("USER");
        Article article = articleService.selectByArticleId(articleId);
        if (!article.getAuthorId().equals(user.getUserId())) {
            return "redirect:" + GetPathUtil.getBasePath(request) + "/Home";
        }
        map.put("article", article);
        map.put("categoryMap", ConfigUtil.getCateporyIdAndNameMap());
        map.put("TYPE", ArticleStateEnum.MODIFY_ARTICLE.getMsgOrUrl());
        return "article/write-article";
    }

    /**
     * 修改文章
     *
     * @param article
     * @param response
     * @throws IOException
     */

    @RequestMapping(value = "/modifyArticle", method = RequestMethod.POST)
    public void modifyArticle(Article article, Integer categoryId, HttpServletResponse response) throws IOException {
        article.setCategory(new Category(categoryId));
        try {
            articleService.update(article);
        } catch (Exception e) {
            ResponseBodyUtil.responseBody(false, ArticleStateEnum.UNKONE_ERROR.getMsgOrUrl(), response);
        }
        ResponseBodyUtil.responseBody(true,
            ArticleStateEnum.ADD_SUCCES.getMsgOrUrl() + "?articleId=" + article.getArticleId(), response);
    }

    /**
     * 根据作者ID查询文章
     *
     * @param authorId
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/{authorId}", method = RequestMethod.GET)
    public String searchByAuthorId(@PathVariable(value = "authorId") Integer authorId, HttpServletRequest request,
        Map<String, Object> map) {
        Integer page = StringUtil.getPageToInteger(request.getParameter("page"));
        Integer categoryId = StringUtil.getCategoryToInteger(request.getParameter("category"));
        String search = request.getParameter("search");
        if (search != null && !search.trim().equals("")) {
            map.put("pageArticle", articleService.selectByAuthorId(categoryId, page, authorId, search));
            map.put("search", search);
        } else {
            map.put("pageArticle", articleService.selectByAuthorId(categoryId, page, authorId));
        }
        map.put("categoryMap", ConfigUtil.getCateporyIdAndNameMap());
        map.put("categoryId", categoryId);
        map.put("authorNickname", userService.selectByUserId(authorId).getNickname());
        return "home";
    }

    /**
     * 根据文章名称搜索文章
     *
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "/Search", method = RequestMethod.GET)
    public String search(HttpServletRequest request, Map<String, Object> map) {
        Integer page = StringUtil.getPageToInteger(request.getParameter("page"));
        Integer categoryId = StringUtil.getCategoryToInteger(request.getParameter("category"));
        String search = request.getParameter("search");
        map.put("pageArticle", articleService.selectBySearch(categoryId, page, search));
        map.put("categoryMap", ConfigUtil.getCateporyIdAndNameMap());
        map.put("categoryId", categoryId);
        map.put("search", search);
        return "home";
    }

    /**
     * 添加文章
     *
     * @param article
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = "/AddArticle", method = RequestMethod.POST)
    public void addArticle(Article article, Integer categoryId, HttpServletResponse response, HttpSession session)
        throws IOException {
        User user = (User)session.getAttribute("USER");
        article.setAuthorId(user.getUserId());
        article.setAuthorNickname(user.getNickname());
        article.setCategory(new Category(categoryId));
        try {
            articleService.insert(article);
        } catch (Exception e) {
            ResponseBodyUtil.responseBody(false, ArticleStateEnum.UNKONE_ERROR.getMsgOrUrl(), response);
        }
        ResponseBodyUtil.responseBody(true,
            ArticleStateEnum.ADD_SUCCES.getMsgOrUrl() + "?articleId=" + article.getArticleId(), response);
    }

    /**
     * 删除文章
     *
     * @param articleId
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(Integer articleId, HttpServletResponse response) throws IOException {
        try {
            articleService.delete(articleId);
        } catch (Exception e) {
            ResponseBodyUtil.responseBody(false, ArticleStateEnum.UNKONE_ERROR.getMsgOrUrl(), response);
        }
        ResponseBodyUtil.responseBody(true, ArticleStateEnum.DELETE_SUCCES.getMsgOrUrl(), response);
    }
}
