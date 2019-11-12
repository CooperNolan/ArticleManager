package com.cooper.articlemanagement.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cooper.articlemanagement.dao.ArticleDao;
import com.cooper.articlemanagement.entity.Article;
import com.cooper.articlemanagement.global.PaginationObject;
import com.cooper.articlemanagement.global.StaticResources;
import com.cooper.articlemanagement.myexception.MyRuntimeException;
import com.cooper.articlemanagement.service.ArticleService;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    @Qualifier("articleDao")
    ArticleDao articleDao;

    /**
     * 搜索所有文章 状态默认0
     *
     * @return
     */
    @Override
    public PaginationObject<Article> selectAll(Integer categoryId, Integer page) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page - 1) * StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("articleStatus", 0);
        map.put("categoryId", categoryId);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page, totalPage, articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 根据文章ID查询
     *
     * @param articleId
     * @return
     */
    @Override
    public Article selectByArticleId(Integer articleId) {
        HashMap<String, Object> hashMap = new HashMap<String, Object>();
        hashMap.put("articleId", articleId);
        return articleDao.select(hashMap);
    }

    /**
     * 根据作者ID查询 状态默认0
     *
     * @param authorId
     * @return
     */
    @Override
    public PaginationObject<Article> selectByAuthorId(Integer categoryId, Integer page, Integer authorId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page - 1) * StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("authorId", authorId);
        map.put("articleStatus", 0);
        map.put("categoryId", categoryId);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page, totalPage, articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 根据作者ID查询
     *
     * @param authorId
     * @param articleStatus
     * @return
     */
    @Override
    public PaginationObject<Article> selectByAuthorId(Integer categoryId, Integer page, Integer authorId,
        Integer articleStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page - 1) * StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("authorId", authorId);
        map.put("articleStatus", articleStatus);
        map.put("categoryId", categoryId);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page, totalPage, articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 在用户ID下 根据搜索内容查询 匹配文章名称 状态默认0
     *
     * @param authorId
     * @param search
     * @return
     */
    @Override
    public PaginationObject<Article> selectByAuthorId(Integer categoryId, Integer page, Integer authorId,
        String search) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page - 1) * StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("authorId", authorId);
        map.put("search", search);
        map.put("articleStatus", 0);
        map.put("categoryId", categoryId);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page, totalPage, articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 根据搜索内容查询 匹配作者名称或文章名称 状态默认0
     *
     * @param search
     * @return
     */
    @Override
    public PaginationObject<Article> selectBySearch(Integer categoryId, Integer page, String search) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page - 1) * StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("search", search);
        map.put("articleStatus", 0);
        map.put("categoryId", categoryId);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page, totalPage, articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 根据搜索内容查询 匹配作者名称或文章名称
     *
     * @param search
     * @param articleStatus
     * @return
     */
    @Override
    public PaginationObject<Article> selectBySearch(Integer categoryId, Integer page, String search,
        Integer articleStatus) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("page", (page - 1) * StaticResources.PAGE_NUM);
        map.put("pageNum", StaticResources.PAGE_NUM);
        map.put("search", search);
        map.put("articleStatus", articleStatus);
        map.put("categoryId", categoryId);
        Integer totalNum = articleDao.selectHomeTotalNum(map);
        int totalPage = PaginationObject.getTotalPage(totalNum);
        if (page < 0 || page > totalPage) {
            return null;
        }
        PaginationObject<Article> pageArticle = new PaginationObject(page, totalPage, articleDao.selectHomeList(map));
        return pageArticle;
    }

    /**
     * 添加文章信息
     *
     * @param article
     * @return
     * @throws MyRuntimeException
     */
    @Override
    public int insert(Article article) throws MyRuntimeException {
        article.setArticleDate(new Date());
        article.setArticleModifyDate(new Date());
        article.setArticleStatus(0);
        int influences = 0;
        try {
            influences = articleDao.insert(article);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }
        return influences;
    }

    /**
     * 更新文章
     *
     * @param article
     * @return
     * @throws MyRuntimeException
     */
    @Override
    public int update(Article article) throws MyRuntimeException {
        int influences = 0;
        article.setArticleModifyDate(new Date());
        try {
            influences = articleDao.update(article);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }
        return influences;
    }

    /**
     * 删除文章
     *
     * @param articleId
     * @return
     * @throws MyRuntimeException
     */
    @Override
    public int delete(Integer articleId) throws MyRuntimeException {
        int influences = 0;
        try {
            influences = articleDao.delete(articleId);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }
        return influences;
    }
}
