package com.cooper.articlemanagement.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cooper.articlemanagement.dao.ReplyDao;
import com.cooper.articlemanagement.entity.Reply;
import com.cooper.articlemanagement.myexception.MyRuntimeException;
import com.cooper.articlemanagement.service.ReplyService;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    @Qualifier("replyDao")
    ReplyDao replyDao;

    /**
     * 根据回复数据ID查询
     *
     * @param replyId
     * @return
     */
    @Override
    public Reply selectByReplyId(Integer replyId) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("replyId", replyId);
        return replyDao.select(map);
    }

    /**
     * 根据文章ID查询所有回复
     *
     * @param articleId
     * @return
     */
    @Override
    public List<Reply> selectByArticleId(Integer articleId) {
        return replyDao.selectByArticleId(articleId);
    }

    /**
     * 查询所有回复
     *
     * @return
     */
    @Override
    public List<Reply> selectAll() {
        return replyDao.select();
    }

    /**
     * 添加回复数据
     *
     * @param reply
     * @return
     * @throws MyRuntimeException
     */
    @Override
    public int insert(Reply reply) throws MyRuntimeException {
        reply.setReplyDate(new Date());
        reply.setReplyStatus(0);
        int influences = 0;
        try {
            replyDao.insert(reply);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }
        return influences;
    }

    /**
     * 更新回复数据
     *
     * @param reply
     * @return
     * @throws MyRuntimeException
     */
    @Override
    public int update(Reply reply) throws MyRuntimeException {
        int influences = 0;
        try {
            replyDao.update(reply);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }
        return influences;
    }

    /**
     * 删除回复数据
     *
     * @param replyId
     * @return
     * @throws MyRuntimeException
     */
    @Override
    public int delete(Integer replyId) throws MyRuntimeException {
        int influences = 0;
        try {
            replyDao.delete(replyId);
        } catch (Exception e) {
            throw new MyRuntimeException(e.getMessage(), e);
        }
        return influences;
    }
}
