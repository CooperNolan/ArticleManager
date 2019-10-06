package com.cooper.articlemanagement.service.impl;

import com.cooper.articlemanagement.dao.ReplyDao;
import com.cooper.articlemanagement.entity.Reply;
import com.cooper.articlemanagement.myexception.ReplyRuntimeException;
import com.cooper.articlemanagement.service.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    @Qualifier("replyDao")
    ReplyDao replyDao;

    /**
     * 根据回复数据ID查询
     * @param replyId
     * @return
     * @throws ReplyRuntimeException
     */
    @Override
    public Reply selectByReplyId(Integer replyId) throws ReplyRuntimeException {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("replyId", replyId);
        return replyDao.select(map);
    }

    /**
     * 根据文章ID查询所有回复
     * @param articleId
     * @return
     * @throws ReplyRuntimeException
     */
    @Override
    public List<Reply> selectByArticleId(Integer articleId) throws ReplyRuntimeException {
        return replyDao.selectByArticleId(articleId);
    }

    /**
     * 查询所有回复
     * @return
     * @throws ReplyRuntimeException
     */
    @Override
    public List<Reply> selectAll() throws ReplyRuntimeException {
        return replyDao.select();
    }

    /**
     * 添加回复数据
     * @param reply
     * @return
     * @throws ReplyRuntimeException
     */
    @Override
    public int insert(Reply reply) throws ReplyRuntimeException {
        reply.setReplyDate(new Date());
        reply.setReplyStatus(0);
        int influences = 0;
        try {
            replyDao.insert(reply);
        } catch (Exception e) {
            throw new ReplyRuntimeException(e.toString());
        }
        return influences;
    }

    /**
     * 更新回复数据
     * @param reply
     * @return
     * @throws ReplyRuntimeException
     */
    @Override
    public int update(Reply reply) throws ReplyRuntimeException {
        int influences = 0;
        try {
            replyDao.update(reply);
        } catch (Exception e) {
            throw new ReplyRuntimeException(e.toString());
        }
        return influences;
    }

    /**
     * 删除回复数据
     * @param replyId
     * @return
     * @throws ReplyRuntimeException
     */
    @Override
    public int delete(Integer replyId) throws ReplyRuntimeException {
        int influences = 0;
        try {
            replyDao.delete(replyId);
        } catch (Exception e) {
            throw new ReplyRuntimeException(e.toString());
        }
        return influences;
    }
}
