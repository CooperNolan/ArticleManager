package com.cooper.articlemanagement.service;

import java.util.List;

import com.cooper.articlemanagement.entity.Reply;
import com.cooper.articlemanagement.myexception.MyRuntimeException;

public interface ReplyService {
    Reply selectByReplyId(Integer replyId);

    List<Reply> selectByArticleId(Integer articleId);

    List<Reply> selectAll();

    int insert(Reply reply) throws MyRuntimeException;

    int update(Reply reply) throws MyRuntimeException;

    int delete(Integer replyId) throws MyRuntimeException;

}
