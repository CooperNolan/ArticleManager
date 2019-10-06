package com.cooper.articlemanagement.controller;


import com.cooper.articlemanagement.entity.Reply;
import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.enums.ReplyStateEnum;
import com.cooper.articlemanagement.service.ReplyService;
import com.cooper.articlemanagement.util.ResponseBodyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller("replyController")
@RequestMapping(value = "Reply")
public class ReplyController {

    @Autowired
    ReplyService replyService;

    /**
     * 新增回复
     * @param reply
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/AddReply", method = RequestMethod.POST)
    public void addReply(Reply reply, HttpSession session, HttpServletResponse response) throws IOException {
        User user = (User) session.getAttribute("USER");
        reply.setUsersId(user.getUserId());
        try {
            replyService.insert(reply);
        } catch (Exception e) {
            ResponseBodyUtil.replyResponseBody(false, ReplyStateEnum.UNKONE_ERROR.getMsgOrUrl(),null,response);
        }
        ResponseBodyUtil.replyResponseBody(true,ReplyStateEnum.ADD_REPLY_SUCCESS.getMsgOrUrl(),reply.getReplyId(),response);
    }


}
