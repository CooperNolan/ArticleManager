package com.cooper.articlemanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.enums.UserStateEnum;
import com.cooper.articlemanagement.global.UserSessionMap;
import com.cooper.articlemanagement.service.UserService;
import com.cooper.articlemanagement.util.ResponseBodyUtil;

@Controller("userController")
public class UserController extends BaseModelAttribute {

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    /**
     * 跳转登录界面
     * 
     * @return
     */
    @RequestMapping(value = "/Login", method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    /**
     * 用户登录验证 验证正确跳转首页
     * 
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(String username, String password) {
        User user = userService.selectByUsername(username);
        // 判断用户是否存在
        if (user != null) {
            // 判断用户密码是否正确
            if (user.getUserpass().equals(password)) {
                // 判断账号是否被锁定
                if (user.getUserStatus() == 1) {
                    return ResponseBodyUtil.responseBody(false, UserStateEnum.STATUS_ERROR.getMsgOrUrl());
                }
                // 更新用户登录时间
                userService.updateLoginTime(user);
                user.setUserpass(null);
                session.setAttribute("USER", user);
                UserSessionMap.putUserSessionMap(user.getUserId(), session);
                logger.info(user.getUsername() + " login...");
                return ResponseBodyUtil.responseBody(true, UserStateEnum.LOGIN_SUCCESS.getMsgOrUrl());
            }
        }
        return ResponseBodyUtil.responseBody(false, UserStateEnum.LOGIN_ERROR.getMsgOrUrl());
    }

    /**
     * 用户注册 注册成功跳转首页
     * 
     * @param username
     * @param password
     * @param code
     * @return
     */
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    @ResponseBody
    public String registered(String username, String password, String code) {
        String kaptchaCode = (String)session.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        // 判断验证码是否正确
        if (!kaptchaCode.equals(code.toUpperCase())) {
            return ResponseBodyUtil.responseBody(false, UserStateEnum.CODE_ERROR.getMsgOrUrl());
        }
        // 判断用户名是否存在
        if (userService.selectByUsername(username) != null) {
            return ResponseBodyUtil.responseBody(false, UserStateEnum.REGISTERED_ERROR.getMsgOrUrl());
        }
        User user = new User(username, password);
        try {
            userService.insert(user);
        } catch (Exception e) {
            logger.error("registered errer: " + e.getMessage());
            return ResponseBodyUtil.responseBody(false, UserStateEnum.UNKONE_ERROR.getMsgOrUrl());
        }
        user.setUserpass(null);
        session.setAttribute("USER", user);
        UserSessionMap.putUserSessionMap(user.getUserId(), session);
        logger.info(user.getUsername() + " registered success...");
        return ResponseBodyUtil.responseBody(true, UserStateEnum.REGISTERED_SUCCESS.getMsgOrUrl());
    }

    /**
     * 用户注销 销毁session 跳转至登录页面
     * 
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    @ResponseBody
    public String logout() {
        try {
            // 销毁session
            session.invalidate();
        } catch (Exception e) {
            logger.error("session.invalidate() errer: " + e.getMessage());
            return ResponseBodyUtil.responseBody(false, UserStateEnum.LOGOUT_ERROR.getMsgOrUrl());
        }
        return ResponseBodyUtil.responseBody(true, UserStateEnum.LOGOUT_SUCCESS.getMsgOrUrl());
    }

    /**
     * 跳转至修改界面
     * 
     * @return
     */
    @RequestMapping(value = "User/ModifyUser", method = RequestMethod.GET)
    public String toModifyUser() {
        return "user/modify-user";
    }

    /**
     * 修改用户信息
     * 
     * @param user
     * @return
     */
    @RequestMapping(value = "User/modify", method = RequestMethod.POST)
    @ResponseBody
    public String modifyUser(User user) {
        if (user.getNickname().length() > 15) {
            user.setNickname(user.getNickname().substring(0, 16));
        }
        try {
            userService.update(user);
            session.setAttribute("USER", userService.selectByUserId(user.getUserId()));
        } catch (Exception e) {
            logger.error(user.getUsername() + " modifyUser errer: " + e.getMessage());
            return ResponseBodyUtil.responseBody(false, UserStateEnum.UNKONE_ERROR.getMsgOrUrl());
        }
        return ResponseBodyUtil.responseBody(true, UserStateEnum.UPDATE_SUCCESS.getMsgOrUrl());
    }
}
