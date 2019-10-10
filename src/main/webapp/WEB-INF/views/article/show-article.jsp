<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>文章</title>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/navi.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/article/showarticle.css">
    <jsp:include page="../navi-top.jsp"></jsp:include>
    <div class="container">
        <form class="form">
            <h1 id="articleId" name="${requestScope.article.articleId}">${requestScope.article.articleTopic}</h1>
            <div class="row">
                <span id="authorId" name="${requestScope.article.authorId}">作者：</span>
                <button type="button" name="${requestScope.article.authorId}" onclick="searchByAuthorId(this)">
                    ${requestScope.article.authorNickname}
                </button>
                </button><br/>
                <span>
                    <fmt:formatDate value="${requestScope.article.articleDate}"
                                    pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                </span>
            </div>
            <div class="row summmary show-div">
                摘要：<pre>${requestScope.article.articleSummary}</pre>
            </div>
            <div class="row show-div">
                正文：<pre>${requestScope.article.articleContent}</pre>
            </div>
            <div class="row">
                <textarea id="ReplyToArticle" maxlength="200"
                          onchange="this.value=this.value.substring(0, 200)"
                          onkeydown="this.value=this.value.substring(0, 200)"
                          onkeyup="this.value=this.value.substring(0, 200)"
                          placeholder="回复内容(不超过200字)："></textarea>
            </div>
            <center>
                <button type="button" class="btn" id="reply" onclick="submit_reply_button(this)">Reply</button>
                <button type="button" class="btn" id="showBack">Back</button>
            </center>
            <c:if test="${sessionScope.USER.userId == requestScope.article.authorId}">
                <center>
                    <button type="button" class="btn modify" id="modifyArticle"
                            name="${requestScope.article.articleId}">Modify
                    </button>
                    <button type="button" class="btn delete" id="deleteArticle"
                            name="${requestScope.article.articleId}">Delete</button>
                </center>
            </c:if>
            </center></form>
        <div id="replyDiv">
            <c:forEach items="${requestScope.replyList}" var="reply">
            <div class="replyToArticle" name="${reply.replyId}">
                <div class="show-div">${reply.userNickname}:${reply.replyContent}</div>
                <div name="replyToReplyShow">
                    <c:forEach items="${reply.replyList}" var="reply2">
                        <div class="replyToReply">
                            <div class="show-div">${reply2.userNickname}:${reply2.replyContent}</div>
                        </div>
                    </c:forEach>
                </div>
                <div class="replyToReplyInput" name="${reply.forUsersId}">
                    <input type="text" name="ReplyToReply" onkeypress="inputReplyToReply(this)" placeholder="不超过100字" maxlength="100">
                    <i class="iconfont icon-huiche" onclick="submit_reply_i(this)"></i>
                </div>
            </div>
            </c:forEach>
        </div>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="<%=basePath%>/resources/js/article/showarticle.js"></script>
    <script src="<%=basePath%>/resources/js/navi.js"></script>
<jsp:include page="../navi-bottom.jsp"></jsp:include>