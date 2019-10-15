package com.cooper.articlemanagement.service.impl;

import com.cooper.articlemanagement.dao.ArticleDao;
import com.cooper.articlemanagement.entity.Article;
import com.cooper.articlemanagement.global.PaginationObject;
import com.cooper.articlemanagement.global.StaticResources;
import com.cooper.articlemanagement.myexception.ArticleRuntimeException;
import com.cooper.articlemanagement.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    @Qualifier("articleDao")
    ArticleDao articleDao;

    /**
     * 搜索所有文章 状态默认0
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public PaginationObject<Article> selectAll(Integer page) throws ArticleRuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page-1)*StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("articleStatus",0);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page,totalPage,articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 根据文章ID查询
     * @param articleId
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public Article selectByArticleId(Integer articleId) throws ArticleRuntimeException {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("articleId",articleId);
        return articleDao.select(hashMap);
    }

    /**
     * 根据作者ID查询 状态默认0
     * @param authorId
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public PaginationObject<Article> selectByAuthorId(Integer page,Integer authorId) throws ArticleRuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page-1)*StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("authorId",authorId);
        map.put("articleStatus",0);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page,totalPage,articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 根据作者ID查询
     * @param authorId
     * @param articleStatus
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public PaginationObject<Article> selectByAuthorId(Integer page,Integer authorId, Integer articleStatus) throws ArticleRuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page-1)*StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("authorId",authorId);
        map.put("articleStatus",articleStatus);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page,totalPage,articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 在用户ID下 根据搜索内容查询 匹配文章名称 状态默认0
     * @param authorId
     * @param search
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public PaginationObject<Article> selectByAuthorId(Integer page,Integer authorId, String search) throws ArticleRuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page-1)*StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("authorId",authorId);
        map.put("search",search);
        map.put("articleStatus",0);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page,totalPage,articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 根据搜索内容查询 匹配作者名称或文章名称 状态默认0
     * @param search
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public PaginationObject<Article> selectBySearch(Integer page,String search) throws ArticleRuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page-1)*StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("search",search);
        map.put("articleStatus",0);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page,totalPage,articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 根据搜索内容查询 匹配作者名称或文章名称
     * @param search
     * @param articleStatus
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public PaginationObject<Article> selectBySearch(Integer page,String search, Integer articleStatus) throws ArticleRuntimeException {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page-1)*StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("search",search);
        map.put("articleStatus",articleStatus);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page,totalPage,articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 添加文章信息
     * @param article
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public int insert(Article article) throws ArticleRuntimeException {
        article.setArticleDate(new Date());
        article.setArticleModifyDate(new Date());
        article.setArticleStatus(0);
        int influences = 0;
        try {
            influences = articleDao.insert(article);
        } catch (Exception e) {
            throw new ArticleRuntimeException(e.toString());
        }
        return influences;
    }

    /**
     * 更新文章
     * @param article
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public int update(Article article) throws ArticleRuntimeException {
        int influences = 0;
        article.setArticleModifyDate(new Date());
        try {
            influences = articleDao.update(article);
        } catch (Exception e) {
            throw new ArticleRuntimeException(e.toString());
        }
        return influences;
    }

    /**
     * 删除文章
     * @param articleId
     * @return
     * @throws ArticleRuntimeException
     */
    @Override
    public int delete(Integer articleId) throws ArticleRuntimeException {
        int influences = 0;
        try {
            influences = articleDao.delete(articleId);
        } catch (Exception e) {
            throw new ArticleRuntimeException(e.toString());
        }
        return influences;
    }
}
