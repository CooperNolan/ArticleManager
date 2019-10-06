package com.cooper.articlemanagement.test.service;

import com.cooper.articlemanagement.entity.Reply;
import com.cooper.articlemanagement.service.ReplyService;
import com.cooper.articlemanagement.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ReplyTest extends BaseTest {

    @Autowired
    ReplyService replyService;

    @Test
    public void selectTest1(){
        List<Reply> replyList = replyService.selectByArticleId(1);
        for (Reply reply : replyList) {
            System.out.println(reply);
            if(reply.getReplyList() != null) {
                for (Reply reply1 : reply.getReplyList()) {
                    System.out.println("  :" + reply1);
                }
            }
        }
    }

    @Test
    public void selectTest2(){
        List<Reply> replyList = replyService.selectAll();
        for (Reply reply : replyList) {
            System.out.println(reply);
        }
    }

    @Test
    public void insertTest(){
        Reply reply =  new Reply();
        reply.setReplyGrade(1);
        reply.setArticleId(1);
        reply.setUsersId(1);
        reply.setForUsersId(1);
        reply.setReplyContent("Test6");
        replyService.insert(reply);
        System.out.println(reply);
    }

    @Test
    public void updateTest() {
        Reply reply = replyService.selectByReplyId(4);
        System.out.println(reply);
        reply.setReplyContent("Test4");
        reply.setReplyStatus(0);
        System.out.println(reply);
        replyService.update(reply);
        System.out.println(reply);
        reply = replyService.selectByReplyId(4);
        System.out.println(reply);
    }

    @Test
    public  void delete(){
        replyService.delete(6);
    }
}
