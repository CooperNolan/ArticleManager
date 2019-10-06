package com.cooper.articlemanagement.service;

import com.cooper.articlemanagement.entity.Reply;
import com.cooper.articlemanagement.myexception.ReplyRuntimeException;
import java.util.List;

public interface ReplyService {
    Reply selectByReplyId(Integer replyId) throws ReplyRuntimeException;
    List<Reply> selectByArticleId(Integer articleId) throws ReplyRuntimeException;
    List<Reply> selectAll() throws ReplyRuntimeException;
    int insert(Reply reply) throws ReplyRuntimeException;
    int update(Reply reply) throws ReplyRuntimeException;
    int delete(Integer replyId) throws ReplyRuntimeException;
}
