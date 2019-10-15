package com.cooper.articlemanagement.controller;


import com.cooper.articlemanagement.entity.Article;
import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.enums.ArticleStateEnum;
import com.cooper.articlemanagement.service.ArticleService;
import com.cooper.articlemanagement.service.ReplyService;
import com.cooper.articlemanagement.util.GetPathUtil;
import com.cooper.articlemanagement.util.ResponseBodyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller("articleController")
@RequestMapping("Article")
public class ArticleController {

    @Autowired
    ArticleService articleService;
    @Autowired
    ReplyService replyService;

    /**
     * 跳转编写文章页面
     *@param map
     * @return
     */
    @RequestMapping(value = "/WriteArticle", method = RequestMethod.GET)
    public String writeArticle(Map<String, Object> map) {
        map.put("TYPE", ArticleStateEnum.WRITE_ARTICLE.getMsgOrUrl());
        return "article/write-article";
    }

    /**
     * 根据文章ID 获取文章并跳转查看页面
     * @param articleId
     * @return
     */
    @RequestMapping(value = "/ShowArticle/{articleId}", method = RequestMethod.GET)
    public String showArticle(@PathVariable(value = "articleId") Integer articleId,
                              Map<String,Object> map){
        map.put("article",articleService.selectByArticleId(articleId));
        map.put("replyList",replyService.selectByArticleId(articleId));
        System.out.println("[" + new Date() + "] 查看文章" + articleId);
        return "article/show-article";
    }

    /**
     * 跳转修改页面
     * @param map
     * @return
     */
    @RequestMapping(value = "/ModifyArticle/{articleId}", method = RequestMethod.GET)
    public String toModifyArticle(@PathVariable(value = "articleId")Integer articleId,
                                Map<String, Object> map, HttpSession session, HttpServletRequest request) {
        User user = (User)session.getAttribute("USER");
        Article article = articleService.selectByArticleId(articleId);
        if (article.getAuthorId() != user.getUserId()) {
            return "redirect:" + GetPathUtil.getBasePath(request) + "/Home";
        }
        map.put("article", article);
        map.put("TYPE", ArticleStateEnum.MODIFY_ARTICLE.getMsgOrUrl());
        return "article/write-article";
    }

    /**
     * 修改文章
     * @param article
     * @param response
     * @throws IOException
     */

    @RequestMapping(value = "/modifyArticle", method = RequestMethod.POST)
    public void modifyArticle(Article article, HttpServletResponse response) throws IOException {
        try {
            articleService.update(article);
        } catch (Exception e) {
            ResponseBodyUtil.responseBody(false,ArticleStateEnum.UNKONE_ERROR.getMsgOrUrl(),response);
        }
        ResponseBodyUtil.responseBody(true,ArticleStateEnum.ADD_SUCCES.getMsgOrUrl(),response);
    }

    /**
     * 根据作者ID查询文章
     * @param authorId
     * @param map
     * @return
     */
    @RequestMapping(value = "/{authorId}", method = RequestMethod.GET)
    public String searchByAuthorId(@PathVariable(value = "authorId") Integer authorId,
                                   Map<String,Object> map){
        map.put("pageArticle", articleService.selectByAuthorId(1,authorId));
        return "home";
    }
    @RequestMapping(value = "/{authorId}/{page}", method = RequestMethod.GET)
    public String searchByAuthorId(@PathVariable(value = "authorId") Integer authorId,
                                   @PathVariable(value = "page") Integer page,
                                   Map<String,Object> map){
        if (page <= 0) {
            return "home";
        }
        map.put("pageArticle", articleService.selectByAuthorId(page,authorId));
        return "home";
    }

    /**
     * 根据在作者ID下搜索文章
     * @param search
     * @param map
     * @return
     */
    @RequestMapping(value = "/{authorId}/Search/{search}", method = RequestMethod.GET)
    public String searchByAuthorId(@PathVariable(value = "search") String search,
                                   @PathVariable(value = "authorId") Integer authorId,
                         Map<String,Object> map){
        map.put("pageArticle", articleService.selectByAuthorId(1,authorId,search));
        return "home";
    }
    @RequestMapping(value = "/{authorId}/Search/{search}/{page}", method = RequestMethod.GET)
    public String searchByAuthorId(@PathVariable(value = "search") String search,
                                   @PathVariable(value = "authorId") Integer authorId,
                                   @PathVariable(value = "page") Integer page,
                                    Map<String,Object> map){
        map.put("pageArticle", articleService.selectByAuthorId(page,authorId,search));
        return "home";
    }

    /**
     * 根据文章名称搜索文章
     * @param search
     * @param map
     * @return
     */
    @RequestMapping(value = "/Search/{search}", method = RequestMethod.GET)
    public String search(@PathVariable(value = "search") String search,
                         Map<String,Object> map){
        map.put("pageArticle", articleService.selectBySearch(1,search));
        return "home";
    }
    @RequestMapping(value = "/Search/{search}/{page}", method = RequestMethod.GET)
    public String search(@PathVariable(value = "search") String search,
                         @PathVariable(value = "page")Integer page,
                         Map<String,Object> map){
        map.put("pageArticle", articleService.selectBySearch(page,search));
        return "home";
    }

    /**
     * 添加文章
     * @param article
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = "/AddArticle", method = RequestMethod.POST)
    public void addArticle(Article article, HttpServletResponse response, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("USER");
        article.setAuthorId(user.getUserId());
        article.setAuthorNickname(user.getNickname());
        try {
            articleService.insert(article);
        } catch (Exception e) {
            ResponseBodyUtil.responseBody(false,ArticleStateEnum.UNKONE_ERROR.getMsgOrUrl(),response);
        }
        ResponseBodyUtil.responseBody(true,ArticleStateEnum.ADD_SUCCES.getMsgOrUrl(),response);
    }

    /**
     * 删除文章
     * @param articleId
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public void delete(Integer articleId, HttpServletResponse response)throws IOException {
        try {
            articleService.delete(articleId);
        } catch (Exception e) {
            ResponseBodyUtil.responseBody(false,ArticleStateEnum.UNKONE_ERROR.getMsgOrUrl(),response);
        }
        ResponseBodyUtil.responseBody(true,ArticleStateEnum.DELETE_SUCCES.getMsgOrUrl(),response);
    }
}
