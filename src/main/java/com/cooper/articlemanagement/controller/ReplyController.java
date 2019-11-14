package com.cooper.articlemanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cooper.articlemanagement.entity.Reply;
import com.cooper.articlemanagement.enums.ReplyStateEnum;
import com.cooper.articlemanagement.service.ReplyService;
import com.cooper.articlemanagement.util.HttpUtil;
import com.cooper.articlemanagement.util.ResponseBodyUtil;

@Controller("replyController")
@RequestMapping(value = "Reply")
public class ReplyController extends BaseModelAttribute {

    private static Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    ReplyService replyService;

    /**
     * 新增回复
     * 
     * @param reply
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addReply(Reply reply) {
        reply.setUsersId(userSession.getUserId());
        try {
            replyService.insert(reply);
        } catch (Exception e) {
            logger.error("{}({}) AddReply errer: {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                e.getMessage());
            return ResponseBodyUtil.replyResponseBody(false, ReplyStateEnum.UNKONE_ERROR.getMsgOrUrl(), null);
        }
        return ResponseBodyUtil.replyResponseBody(true, ReplyStateEnum.ADD_REPLY_SUCCESS.getMsgOrUrl(),
            reply.getReplyId());
    }

}
