package com.cooper.articlemanagement.controller;

import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.enums.UserStateEnum;
import com.cooper.articlemanagement.global.UserSessionMap;
import com.cooper.articlemanagement.service.UserService;
import com.cooper.articlemanagement.util.ResponseBodyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller("userController")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 跳转登录界面
     * @return
     */
    @RequestMapping(value = "/Login",method = RequestMethod.GET)
    public String toLogin() {
        return "login";
    }

    /**
     * 用户登录验证 验证正确跳转首页
     * @param username
     * @param password
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(String username, String password,
                      HttpServletResponse response,
                      HttpSession session) throws IOException {
        User user = userService.selectByUsername(username);
        //判断用户是否存在
        if (user != null) {
            //判断用户密码是否正确
            if (user.getUserpass().equals(password)) {
                //判断账号是否被锁定
                if (user.getUserStatus() == 1) {
                    ResponseBodyUtil.responseBody(false,
                            UserStateEnum.STATUS_ERROR.getMsgOrUrl(),response);
                    return;
                }
                //更新用户登录时间
                userService.updateLoginTime(user);
                user.setUserpass(null);
                session.setAttribute("USER",user);
                UserSessionMap.putUserSessionMap(user.getUserId(),session);
                System.out.println("["+new Date()+"] "+user.getUsername()+"已登录");
                ResponseBodyUtil.responseBody(true,
                        UserStateEnum.LOGIN_SUCCESS.getMsgOrUrl(),response);
                return;
            }
        }
        ResponseBodyUtil.responseBody(false,
                UserStateEnum.LOGIN_ERROR.getMsgOrUrl(),response);
    }

    /**
     * 用户注册 注册成功跳转首页
     * @param username
     * @param password
     * @param code
     * @param response
     * @param session
     * @throws IOException
     */
    @RequestMapping(value = "/registered", method = RequestMethod.POST)
    public void registered(String username, String password,
                           String code,HttpServletResponse response,
                           HttpSession session)throws IOException {
        String kaptchaCode = (String) session.getAttribute
                (com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
        //判断验证码是否正确
        if (!kaptchaCode.equals(code.toUpperCase())) {
            ResponseBodyUtil.responseBody(false,
                    UserStateEnum.CODE_ERROR.getMsgOrUrl(),response);
            return;
        }
        //判断用户名是否存在
        if (userService.selectByUsername(username) != null) {
            ResponseBodyUtil.responseBody(false,
                    UserStateEnum.REGISTERED_ERROR.getMsgOrUrl(),response);
            return;
        }
        User user = new User(username, password);
        try {
            userService.insert(user);
        } catch (Exception e) {
            ResponseBodyUtil.responseBody(false,
                    UserStateEnum.UNKONE_ERROR.getMsgOrUrl(), response);
        }
        user.setUserpass(null);
        session.setAttribute("USER", user);
        UserSessionMap.putUserSessionMap(user.getUserId(),session);
        System.out.println("["+new Date()+"] "+user.getUsername()+" 成功注册");
        ResponseBodyUtil.responseBody(true,
                UserStateEnum.REGISTERED_SUCCESS.getMsgOrUrl(),response);
    }

    /**
     * 用户注销 销毁session 跳转至登录页面
     * @param session
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public void logout(HttpSession session,
                       HttpServletResponse response) throws IOException{
        try {
            //销毁session
            session.invalidate();
        } catch (Exception e) {
            ResponseBodyUtil.responseBody(false,
                    UserStateEnum.LOGOUT_ERROR.getMsgOrUrl(),response);
            return;
        }
        ResponseBodyUtil.responseBody(true,
                UserStateEnum.LOGOUT_SUCCESS.getMsgOrUrl(),response);
    }

    /**
     * 跳转至修改界面
     * @return
     */
    @RequestMapping(value = "User/ModifyUser", method = RequestMethod.GET)
    public String toModifyUser() {
        return "user/modify-user";
    }

    @RequestMapping(value = "User/modifyUser", method = RequestMethod.POST)
    public void modifyUser(User user,HttpServletResponse response,HttpSession session) throws IOException{
        User sessionUser = (User)session.getAttribute("USER");
        user.setUserId(sessionUser.getUserId());
        try {
            userService.update(user);
            session.setAttribute("USER", userService.selectByUserId(sessionUser.getUserId()));
        } catch (Exception e) {
            ResponseBodyUtil.responseBody(false, UserStateEnum.UNKONE_ERROR.getMsgOrUrl(), response);
        }
        ResponseBodyUtil.responseBody(true, UserStateEnum.UPDATE_SUCCESS.getMsgOrUrl(), response);
    }
}
