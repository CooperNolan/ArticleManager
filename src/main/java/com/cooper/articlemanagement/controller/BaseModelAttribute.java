package com.cooper.articlemanagement.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.cooper.articlemanagement.entity.User;

public class BaseModelAttribute {

    @Autowired
    protected HttpServletRequest request;

    protected HttpSession session;

    protected User userSession;

    @ModelAttribute
    public void setModelAttribute() {
        this.session = request.getSession();
        this.userSession = (User)session.getAttribute("USER");
    }

}
