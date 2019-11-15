package com.cooper.articlemanagement.global;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cooper.articlemanagement.entity.Article;
import com.cooper.articlemanagement.entity.User;
import com.cooper.articlemanagement.service.ArticleService;
import com.cooper.articlemanagement.util.HttpUtil;
import com.cooper.articlemanagement.util.ResponseBodyUtil;

@Aspect
public class PermissionVerificationAspect {
    private static Logger logger = LoggerFactory.getLogger(PermissionVerificationAspect.class);

    @Autowired
    ArticleService articleService;

    @Around("execution(* com.cooper.articlemanagement.controller.UserController.modifyUser(..))")
    public Object modifyUserExecution(ProceedingJoinPoint jp) throws Throwable {
        return permissionVerification(jp, new PermissionVerification() {
            @Override
            public boolean[] isPermission(Object object, User userSession) {
                if (object instanceof User) {
                    User user = (User)object;
                    if (!user.getUserId().equals(userSession.getUserId()) && userSession.getUserStatus() != 2) {
                        return new boolean[] {true, true};
                    }
                    return new boolean[] {false, true};
                }
                return new boolean[] {false, false};
            }
        });
    }

    @Around("execution(* com.cooper.articlemanagement.controller.ArticleController.modifyArticle(..))")
    public Object modifyArticleExecution(ProceedingJoinPoint jp) throws Throwable {
        return permissionVerification(jp, new PermissionVerification() {
            @Override
            public boolean[] isPermission(Object object, User userSession) {
                if (object instanceof Article) {
                    Article article = (Article)object;
                    if (!article.getAuthorId().equals(userSession.getUserId()) && userSession.getUserStatus() != 2) {
                        return new boolean[] {true, true};
                    }
                    return new boolean[] {false, true};
                }
                return new boolean[] {false, false};
            }
        });
    }

    @Around("execution(* com.cooper.articlemanagement.controller.ArticleController.delete(..))")
    public Object deleteArticleExecution(ProceedingJoinPoint jp) throws Throwable {
        return permissionVerification(jp, new PermissionVerification() {
            @Override
            public boolean[] isPermission(Object object, User userSession) {
                if (object instanceof Integer) {
                    Article article = articleService.selectByArticleId((Integer)object);
                    if (!article.getAuthorId().equals(userSession.getUserId()) && userSession.getUserStatus() != 2) {
                        return new boolean[] {true, true};
                    }
                    return new boolean[] {false, true};
                }
                return new boolean[] {false, false};
            }
        });
    }

    @Around("execution(* com.cooper.articlemanagement.controller.CategoryController.*(..))")
    public Object categoryControllerExecution(ProceedingJoinPoint jp) throws Throwable {
        return permissionVerification(jp, new PermissionVerification() {
            @Override
            public boolean[] isPermission(Object object, User userSession) {
                if (userSession.getUserStatus() != 2) {
                    return new boolean[] {true, true};
                }
                return new boolean[] {false, true};
            }
        });
    }

    public Object permissionVerification(ProceedingJoinPoint jp, PermissionVerification permissionVerification)
        throws Throwable {
        if (permissionVerification == null) {
            return null;
        }
        User userSession = null;
        HttpServletRequest request = null;
        try {
            request = HttpUtil.getHttpServletRequest();
            if (request != null && request.getSession() != null) {
                Object[] objects = jp.getArgs();
                userSession = (User)request.getSession().getAttribute("USER");
                for (Object object : objects) {
                    boolean[] booleans = permissionVerification.isPermission(object, userSession);
                    if (booleans[0]) {
                        logger.warn("{}({}) 非法访问({})！ {}", userSession.getUsername(), HttpUtil.getIpAddress(request),
                            request.getRequestURI(), object.toString());
                        return ResponseBodyUtil.responseBody(false, "No Permission");
                    }
                    if (booleans[1]) {
                        break;
                    }
                }
                return jp.proceed();
            } else {
                logger.warn("request or session is null");
            }
        } catch (Exception e) {
            logger.error(userSession.getUsername() + "(" + HttpUtil.getIpAddress(request) + ") " + e.getMessage());
        }
        return ResponseBodyUtil.responseBody(false, "No Permission");
    }

    private interface PermissionVerification {
        boolean[] isPermission(Object object, User userSession);
    }

}
