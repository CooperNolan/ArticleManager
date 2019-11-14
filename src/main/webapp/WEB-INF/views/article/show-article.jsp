<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
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
                <span>发布时间：<fmt:formatDate value='${requestScope.article.articleDate}'
                                           pattern='yyyy-MM-dd HH:mm:ss'></fmt:formatDate>
                </span><br/>
                <span>文章类型：${requestScope.article.category.categoryName}</span>
            </div>
            <div class="row show-div">
                正文：
                <pre>${requestScope.article.articleContent}</pre>
            </div>
            <div class="row">
                <textarea id="ReplyToArticle" maxlength="200"
                          onchange="this.value=this.value.substring(0, 200)"
                          onkeydown="this.value=this.value.substring(0, 200)"
                          onkeyup="this.value=this.value.substring(0, 200)"
                          placeholder="回复内容(不超过200字)："></textarea>
            </div>
            <div class="row">
                <span style="font-size:1em;float: right">最后修改时间：<fmt:formatDate
                        value='${requestScope.article.articleModifyDate}'
                        pattern='yyyy-MM-dd HH:mm:ss'></fmt:formatDate>
                </span><br/>
            </div>
            <center>
                <button type="button" class="btn" id="reply" onclick="submit_reply_button(this)">Reply</button>
                <c:if test="${sessionScope.USER.userId == requestScope.article.authorId or sessionScope.USER.userStatus == 2}">
                    <button type="button" class="btn modify" id="modifyArticle"
                            name="${requestScope.article.articleId}">Modify
                    </button>
                    <button type="button" class="btn delete" id="deleteArticle"
                            name="${requestScope.article.articleId}">Delete
                    </button>
                </c:if>
            </center>
        </form>
        <div id="replyDiv">
            <c:forEach items="${requestScope.replyList}" var="reply">
                <div class="replyToArticle" name="${reply.replyId}">
                    <div class="show-div">
                            ${reply.userNickname}&nbsp;&nbsp;&nbsp;&nbsp;<time class="time"
                                                                               datetime="<fmt:formatDate value='${reply.replyDate}' pattern='yyyy-MM-dd HH:mm:ss'></fmt:formatDate>"></time>
                        <pre>${reply.replyContent}</pre>
                    </div>
                    <div name="replyToReplyShow">
                        <c:forEach items="${reply.replyList}" var="reply2">
                            <div class="replyToReply">
                                <div class="show-div">
                                        ${reply2.userNickname}&nbsp;&nbsp;&nbsp;&nbsp;<time class="time"
                                                                                            datetime="<fmt:formatDate value='${reply2.replyDate}' pattern='yyyy-MM-dd HH:mm:ss'></fmt:formatDate>"></time>
                                    <pre>${reply2.replyContent}</pre>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                    <div class="replyToReplyInput" name="${reply.forUsersId}">
                        <input type="text" name="ReplyToReply" onkeypress="inputReplyToReply(this)"
                               placeholder="不超过100字" maxlength="100">
                        <i class="iconfont icon-huiche" onclick="submit_reply_i(this)"></i>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="<%=basePath%>/resources/js/article/showarticle.js"></script>
    <script src="<%=basePath%>/resources/js/navi.js"></script>
    <script src="https://cdn.bootcss.com/timeago.js/3.0.2/timeago.min.js"></script>
    <script type="text/javascript">
        var timeagoInstance = timeago();
        timeagoInstance.render(document.querySelectorAll('.time'), 'zh_CN');
    </script>
<jsp:include page="../navi-bottom.jsp"></jsp:include>