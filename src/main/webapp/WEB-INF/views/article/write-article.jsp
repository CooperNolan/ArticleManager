<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>文章编写</title>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/navi.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/article/writearticle.css">
    <jsp:include page="../navi-top.jsp"></jsp:include>
    <div class="container">
        <form class="form">
            <h1>文章编写</h1>
            <div class="row">
                <label>文章题目：</label>
                <textarea id="topic" maxlength="15"
                          onchange="this.value=this.value.substring(0, 15)"
                          onkeydown="this.value=this.value.substring(0, 15)"
                          onkeyup="this.value=this.value.substring(0, 15)"
                          placeholder="不超过15字">${requestScope.article.articleTopic}</textarea>
            </div>
            <div class="row">
                <label>文章分类：</label>
                <select id="category">
                    <c:forEach items="${requestScope.categoryMap}" var="category">
                        <c:if test="${category.key == requestScope.article.category.categoryId}">
                            <option value=${category.key} selected>${category.value}</option>
                        </c:if>
                        <c:if test="${category.key != requestScope.article.category.categoryId}">
                            <option value=${category.key}>${category.value}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div class="row">
                <label>文章内容：</label>
                <textarea id="content" placeholder="正文：">${requestScope.article.articleContent}</textarea>
            </div>
            <div class="prompt">
                <span id="prompt">&nbsp;</span>
            </div>
            <center>
                <button type="button" class="btn" id="${requestScope.TYPE}">${requestScope.TYPE}</button>
            </center>
        </form>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="<%=basePath%>/resources/js/article/writearticle.js"></script>
    <script src="<%=basePath%>/resources/js/navi.js"></script>
<jsp:include page="../navi-bottom.jsp"></jsp:include>