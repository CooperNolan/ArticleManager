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
    <title>分类管理</title>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/navi.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/list.css">
    <jsp:include page="../navi-top.jsp"></jsp:include>
    <div class="list">
        <div class="row">
            <center>
                <h2>分类管理</h2>
            </center>
        </div>
        <div class="input-search">
            <input type="text" class="txtb" id="search" placeholder="搜索分类名称：" value="">
            <i class="iconfont icon-chaxun search" onclick="searchCategory()"></i>
        </div>
        <div class="notcomp">
            <h2>分类列表</h2>
            <div class="task" id="addCategoryDiv">
                <center><h4>添加分类</h4></center>
                <span class="article">分类名称: <input type="text" class="txtCategpry" id="categoryName"
                                                   placeholder="不超过12个字"
                                                   value="" maxlength="12"></span>
                <br>
                <span class="nickname">分类状态:
                    <input type="radio" name="categoryStatus" value="0" checked>0
                    <input type="radio" name="categoryStatus" value="1">1
                </span>
                <button type="button" id="addCategory" class="categoryButton" style="float:right;">Submit</button>
            </div>
            <c:forEach items="${requestScope.categoryList}" var="category">
                <div class="task" id="div${category.categoryId}">
                    <span class="article">分类名称: <input type="text" class="txtCategpry" name="categoryName"
                                                       placeholder="不超过12个字"
                                                       value="${category.categoryName}" maxlength="12"></span>
                    <br>
                    <span class="nickname">分类状态:
                        <c:if test="${category.categoryStatus == 0}">
                            <input type="radio" name="categoryStatus${category.categoryId}" value="0" checked>0
                            <input type="radio" name="categoryStatus${category.categoryId}" value="1">1
                        </c:if>
                        <c:if test="${category.categoryStatus == 1}">
                            <input type="radio" name="categoryStatus${category.categoryId}" value="0">0
                            <input type="radio" name="categoryStatus${category.categoryId}" value="1" checked>1
                        </c:if>
                    </span>
                    <button type="button" class="categoryButton" style="float:right;" name="${category.categoryId}"
                            onclick="deleteOnClick(this)">Delete
                    </button>
                    <button type="button" class="categoryButton" style="float:right;" name="${category.categoryId}"
                            onclick="modifyOnClick(this)">Modify
                    </button>
                </div>
            </c:forEach>
        </div>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="<%=basePath%>/resources/js/category/category.js"></script>
    <script src="<%=basePath%>/resources/js/navi.js"></script>
<jsp:include page="../navi-bottom.jsp"></jsp:include>