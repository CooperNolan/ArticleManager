package com.cooper.articlemanagement.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.cooper.articlemanagement.entity.Reply;

@Repository("replyDao")
public interface ReplyDao {

    Reply select(HashMap<String, Object> hashMap);

    List<Reply> select(Map<String, Object> map);

    List<Reply> select();

    List<Reply> selectByArticleId(@Param("articleId") Integer articleId);

    int insert(Reply reply);

    int update(Reply reply);

    int delete(@Param("replyId") Integer replyId);

}
