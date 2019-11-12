package com.cooper.articlemanagement.service;

import org.springframework.stereotype.Service;

import com.cooper.articlemanagement.entity.Article;
import com.cooper.articlemanagement.global.PaginationObject;
import com.cooper.articlemanagement.myexception.MyRuntimeException;

@Service("articleService")
public interface ArticleService {
    Article selectByArticleId(Integer articleId);

    PaginationObject<Article> selectByAuthorId(Integer categoryId, Integer page, Integer authorId);

    PaginationObject<Article> selectByAuthorId(Integer categoryId, Integer page, Integer authorId, String search);

    PaginationObject<Article> selectByAuthorId(Integer categoryId, Integer page, Integer authorId,
        Integer articleStatus);

    PaginationObject<Article> selectBySearch(Integer categoryId, Integer page, String search);

    PaginationObject<Article> selectBySearch(Integer categoryId, Integer page, String search, Integer articleStatus);

    PaginationObject<Article> selectAll(Integer categoryId, Integer page);

    int insert(Article article) throws MyRuntimeException;

    int update(Article article) throws MyRuntimeException;

    int delete(Integer articleId) throws MyRuntimeException;

}
