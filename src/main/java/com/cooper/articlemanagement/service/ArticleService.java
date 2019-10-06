package com.cooper.articlemanagement.service;


import com.cooper.articlemanagement.entity.Article;
import com.cooper.articlemanagement.global.PaginationObject;
import com.cooper.articlemanagement.myexception.ArticleRuntimeException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("articleService")
public interface ArticleService {
    Article selectByArticleId(Integer articleId) throws ArticleRuntimeException;
    PaginationObject<Article> selectByAuthorId(Integer page,Integer authorId) throws ArticleRuntimeException;
    PaginationObject<Article> selectByAuthorId(Integer page,Integer authorId,String search) throws ArticleRuntimeException;
    PaginationObject<Article> selectByAuthorId(Integer page,Integer authorId,Integer articleStatus) throws ArticleRuntimeException;
    PaginationObject<Article> selectBySearch(Integer page,String search) throws ArticleRuntimeException;
    PaginationObject<Article> selectBySearch(Integer page,String search,Integer articleStatus) throws ArticleRuntimeException;
    PaginationObject<Article> selectAll(Integer page) throws ArticleRuntimeException;
    int insert(Article article) throws ArticleRuntimeException;
    int update(Article article) throws ArticleRuntimeException;
    int delete(Integer articleId) throws ArticleRuntimeException;
}
