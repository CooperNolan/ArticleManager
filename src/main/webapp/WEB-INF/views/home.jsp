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
    <title>文章管理系统</title>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/navi.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/list.css">
    <jsp:include page="navi-top.jsp"></jsp:include>
    <div class="list">
        <c:if test="${requestScope.authorNickname != null}">
            <div class="row">
                <center>
                    <h2>${requestScope.authorNickname}</h2>
                </center>
            </div>
        </c:if>
        <div class="row">
            <select id="category">
                <option value="0" selected>全部</option>
                <c:forEach items="${requestScope.categoryMap}" var="category">
                    <c:if test="${category.key == requestScope.categoryId}">
                        <option value=${category.key} selected>${category.value}</option>
                    </c:if>
                    <c:if test="${category.key != requestScope.categoryId}">
                        <option value=${category.key}>${category.value}</option>
                    </c:if>
                </c:forEach>
            </select>
        </div>
        <div class="input-search">
            <input type="text" class="txtb" id="search" placeholder="搜索文章名：" value="">
            <input type="hidden" class="txtb" id="searchCache" placeholder="搜索文章名：" value="${requestScope.search}">
            <i class="iconfont icon-chaxun search" onclick="searchArticle()"></i>
        </div>
        <div class="notcomp">
            <h2>文章列表</h2>
            <c:forEach items="${requestScope.pageArticle.pageList}" var="article">
                <div class="task" id="div${article.articleId}">
                    <span class="article">文章名称: ${article.articleTopic}</span><br/>
                    <span class="nickname">作者: ${article.authorNickname}</span><br/>
                    <span class="time">发布时间: <fmt:formatDate value="${article.articleDate}"
                                                             pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate> </span>
                    <i class="iconfont icon-chakan" name="${article.articleId}" onclick="iconOnClick(this)"></i>
                    <c:if test="${sessionScope.USER.userId == article.authorId || sessionScope.USER.userStatus == 2 }">
                        <i class="iconfont icon-shanchu1 deleteIcon" name="${article.articleId}"
                           onclick="deleteIconOnClick(this)"></i>
                    </c:if>
                </div>
            </c:forEach>
        </div>
        <c:if test="${requestScope.pageArticle.totalPage > 1}">
            <div class="Pagination" id="Pagination">
                <c:if test="${requestScope.pageArticle.page > 1}">
                    <i class="iconfont icon-jiantou Pagination-left" name="${requestScope.pageArticle.page - 1}"
                       onclick="paginationLeft(this)"></i>
                </c:if>
                <input type="text" class="Pagination-input" name="${requestScope.pageArticle.totalPage}"
                       placeholder="${requestScope.pageArticle.page}/${requestScope.pageArticle.totalPage}"
                       maxlength="10">
                <c:if test="${requestScope.pageArticle.page < requestScope.pageArticle.totalPage}">
                    <i class="iconfont icon-jiantou Pagination-right" name="${requestScope.pageArticle.page + 1}"
                       onclick="paginationRight(this)"></i>
                </c:if>
            </div>
        </c:if>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="<%=basePath%>/resources/js/list.js"></script>
    <script src="<%=basePath%>/resources/js/navi.js"></script>
<jsp:include page="navi-bottom.jsp"></jsp:include>