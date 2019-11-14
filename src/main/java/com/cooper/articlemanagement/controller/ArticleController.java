package com.cooper.articlemanagement.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cooper.articlemanagement.entity.Article;
import com.cooper.articlemanagement.entity.Category;
import com.cooper.articlemanagement.enums.ArticleStateEnum;
import com.cooper.articlemanagement.service.ArticleService;
import com.cooper.articlemanagement.service.ReplyService;
import com.cooper.articlemanagement.service.UserService;
import com.cooper.articlemanagement.util.ConfigUtil;
import com.cooper.articlemanagement.util.HttpUtil;
import com.cooper.articlemanagement.util.ResponseBodyUtil;
import com.cooper.articlemanagement.util.StringUtil;

@Controller("articleController")
@RequestMapping(value = "Article")
public class ArticleController extends BaseModelAttribute {

    private static Logger logger = LoggerFactory.getLogger(ArticleController.class);

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
        map.put("categoryMap", ConfigUtil.getCategoryIdAndNameMap());
        map.put("TYPE", ArticleStateEnum.WRITE_ARTICLE.getMsgOrUrl());
        return "article/write-article";
    }

    /**
     * 根据文章ID 获取文章并跳转查看页面
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/ShowArticle", method = RequestMethod.GET)
    public String showArticle(Map<String, Object> map) {
        Integer articleId;
        try {
            articleId = Integer.parseInt(request.getParameter("articleId"));
        } catch (Exception e) {
            logger.error("{}({}) ShowArticle errer: {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                e.getMessage());
            return "redirect:/Home";
        }
        map.put("article", articleService.selectByArticleId(articleId));
        map.put("replyList", replyService.selectByArticleId(articleId));
        return "article/show-article";
    }

    /**
     * 跳转修改页面
     * 
     * @param map
     * @return
     */
    @RequestMapping(value = "/ModifyArticle", method = RequestMethod.GET)
    public String toModifyArticle(Map<String, Object> map) {
        Integer articleId = null;
        try {
            articleId = Integer.parseInt(request.getParameter("articleId"));
        } catch (Exception e) {
            logger.error("{}({}) ShowArticle errer: {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                e.getMessage());
            return "redirect:" + HttpUtil.getBasePath(request) + "/Home";
        }
        Article article = articleService.selectByArticleId(articleId);
        if (!article.getAuthorId().equals(userSession.getUserId()) && userSession.getUserStatus() != 2) {
            return "redirect:" + HttpUtil.getBasePath(request) + "/Home";
        }
        map.put("article", article);
        map.put("categoryMap", ConfigUtil.getCategoryIdAndNameMap());
        map.put("TYPE", ArticleStateEnum.MODIFY_ARTICLE.getMsgOrUrl());
        return "article/write-article";
    }

    /**
     * 修改文章
     * 
     * @param article
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modifyArticle(Article article, Integer categoryId) {
        article.setCategory(new Category(categoryId));
        try {
            articleService.update(article);
        } catch (Exception e) {
            logger.error("{}({}) modifyArticle errer: {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                e.getMessage());
            return ResponseBodyUtil.responseBody(false, ArticleStateEnum.UNKONE_ERROR.getMsgOrUrl());
        }
        return ResponseBodyUtil.responseBody(true,
            ArticleStateEnum.ADD_SUCCES.getMsgOrUrl() + "?articleId=" + article.getArticleId());
    }

    /**
     * 根据作者ID查询文章
     *
     * @param authorId
     * @param map
     * @return
     */
    @RequestMapping(value = "/{authorId}", method = RequestMethod.GET)
    public String searchByAuthorId(@PathVariable(value = "authorId") Integer authorId, Map<String, Object> map) {
        Integer page = StringUtil.getPageToInteger(request.getParameter("page"));
        Integer categoryId = StringUtil.getCategoryToInteger(request.getParameter("category"));
        String search = request.getParameter("search");
        if (search != null && !search.trim().equals("")) {
            map.put("pageArticle", articleService.selectByAuthorId(categoryId, page, authorId, search));
            map.put("search", search);
        } else {
            map.put("pageArticle", articleService.selectByAuthorId(categoryId, page, authorId));
        }
        map.put("categoryMap", ConfigUtil.getCategoryIdAndNameMap());
        map.put("categoryId", categoryId);
        map.put("authorNickname", userService.selectByUserId(authorId).getNickname());
        return "home";
    }

    /**
     * 根据文章名称搜索文章
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/Search", method = RequestMethod.GET)
    public String search(Map<String, Object> map) {
        Integer page = StringUtil.getPageToInteger(request.getParameter("page"));
        Integer categoryId = StringUtil.getCategoryToInteger(request.getParameter("category"));
        String search = request.getParameter("search");
        map.put("pageArticle", articleService.selectBySearch(categoryId, page, search));
        map.put("categoryMap", ConfigUtil.getCategoryIdAndNameMap());
        map.put("categoryId", categoryId);
        map.put("search", search);
        return "home";
    }

    /**
     * 添加文章
     * 
     * @param article
     * @param categoryId
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addArticle(Article article, Integer categoryId) {
        article.setAuthorId(userSession.getUserId());
        article.setAuthorNickname(userSession.getNickname());
        article.setCategory(new Category(categoryId));
        try {
            articleService.insert(article);
        } catch (Exception e) {
            logger.error("{}({}) AddArticle errer: {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                e.getMessage());
            return ResponseBodyUtil.responseBody(false, ArticleStateEnum.UNKONE_ERROR.getMsgOrUrl());
        }
        return ResponseBodyUtil.responseBody(true,
            ArticleStateEnum.ADD_SUCCES.getMsgOrUrl() + "?articleId=" + article.getArticleId());
    }

    /**
     * 删除文章
     * 
     * @param articleId
     * @return
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(Integer articleId) {
        try {
            articleService.delete(articleId);
        } catch (Exception e) {
            logger.error("{}({}) deleteArticle errer: {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                e.getMessage());
            return ResponseBodyUtil.responseBody(false, ArticleStateEnum.UNKONE_ERROR.getMsgOrUrl());
        }
        return ResponseBodyUtil.responseBody(true, ArticleStateEnum.DELETE_SUCCES.getMsgOrUrl());
    }
}
