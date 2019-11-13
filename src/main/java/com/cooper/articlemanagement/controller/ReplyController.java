package com.cooper.articlemanagement.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cooper.articlemanagement.entity.Reply;
import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.enums.ReplyStateEnum;
import com.cooper.articlemanagement.service.ReplyService;
import com.cooper.articlemanagement.util.HttpUtil;
import com.cooper.articlemanagement.util.ResponseBodyUtil;

@Controller("replyController")
@RequestMapping(value = "Reply")
public class ReplyController {

    private static Logger logger = LoggerFactory.getLogger(ReplyController.class);

    @Autowired
    ReplyService replyService;

    /**
     * 新增回复
     * 
     * @param reply
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/AddReply", method = RequestMethod.POST)
    public void addReply(Reply reply, HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User)request.getSession().getAttribute("USER");
        reply.setUsersId(user.getUserId());
        try {
            replyService.insert(reply);
        } catch (Exception e) {
            logger.error("{}({}) AddReply errer: {}", ((User)request.getSession().getAttribute("USER")).getUsername(),
                HttpUtil.getIpAddress(request), e.getMessage());
            ResponseBodyUtil.replyResponseBody(false, ReplyStateEnum.UNKONE_ERROR.getMsgOrUrl(), null, response);
        }
        ResponseBodyUtil.replyResponseBody(true, ReplyStateEnum.ADD_REPLY_SUCCESS.getMsgOrUrl(), reply.getReplyId(),
            response);
    }

}
