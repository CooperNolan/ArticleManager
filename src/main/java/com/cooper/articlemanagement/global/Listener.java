package com.cooper.articlemanagement.global;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cooper.articlemanagement.entity.User;

@WebListener()
public class Listener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private static Logger logger = LoggerFactory.getLogger(Listener.class);

    // Public constructor is required by servlet spec
    public Listener() {}

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
        */
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
        */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
        User user = (User)se.getSession().getAttribute("USER");
        if (user != null) {
            UserSessionMap.destoryHttpSession(user.getUserId());
            logger.warn(user.getUsername() + "logout...");
        }
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------
    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute 
         is added to a session.
        */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute
         is removed from a session.
        */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is invoked when an attibute
         is replaced in a session.
        */
    }
}
