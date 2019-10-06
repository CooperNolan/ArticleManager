<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>个人信息</title>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/navi.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/user/modifyuser.css">
    <jsp:include page="../navi-top.jsp"></jsp:include>
    <div class="container">
        <form class="form">
            <h1>${sessionScope.USER.username}</h1>
            <div class="row">
                <label>昵称：</label>
                <input type="text" id="nickname" value="${sessionScope.USER.nickname}" placeholder="不超过15字" maxlength="15">
            </div>
            <div class="row">
                <label>密码：</label><span class="lable-prompt" id="userpass-prompt"></span>
                <input type="password" id="userpass" value="" placeholder="8-18个数字、字母或下划线">
            </div>
            <div class="row" >
                <label>生日：</label>
                <input type="date" id="birthday" value="${sessionScope.USER.birthday}">
            </div>
            <div class="row">
                <label>性别：</label>
                <select id="gender">
                    <c:if test='${sessionScope.USER.gender == null or sessionScope.USER.gender == "男"}'>
                        <option value="男" selected>男</option>
                        <option value="女">女</option>
                    </c:if>
                    <c:if test='${sessionScope.USER.gender == "女"}'>
                        <option value="男" >男</option>
                        <option value="女" selected>女</option>
                    </c:if>
                </select>
            </div>
            <div class="row">
                <label>电话：</label><span class="lable-prompt" id="phone-prompt" maxlength="13"></span>
                <input type="rel" id="phone" value="${sessionScope.USER.phone}">
            </div>
            <div class="row">
                <label>邮箱：</label><span class="lable-prompt" id="email-prompt" maxlength="30"></span>
                <input type="email" id="email" value="${sessionScope.USER.email}">
            </div>
            <div class="row">
                <label>地址：</label>
                <input type="text" id="address" value="${sessionScope.USER.address}" maxlength="30">
            </div>
            <div class="row">
                <label>创建时间：</label>
                <input type="text" id="createTime"
                       value="<fmt:formatDate value="${sessionScope.USER.createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>"
                       readonly>
            </div>
            <div class="row">
                <label>最后修改时间：</label>
                <input type="text" id="updateTime"
                       value="<fmt:formatDate value="${sessionScope.USER.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>"
                       placeholder="暂无修改时间" readonly>
            </div>
            <div class="row">
                <label>最后登录时间：</label>
                <input type="text" id="lastLogin"
                       value="<fmt:formatDate value="${sessionScope.USER.lastLogin}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>"
                       placeholder="暂无登录时间" readonly>
            </div>
            <div class="row">
                <label>个人简介：</label>
                <textarea id="remark" maxlength="200"
                          onchange="this.value=this.value.substring(0, 200)"
                          onkeydown="this.value=this.value.substring(0, 200)"
                          onkeyup="this.value=this.value.substring(0, 200)"
                          placeholder="不超过200字">${sessionScope.USER.remark}</textarea>
            </div>
            <div class="prompt">
                <span id="prompt">&nbsp;</span>
            </div>
            <center>
                <button type="button" class="btn" id="modify">mofidy</button>
                <button type="button" class="btn" id="writeBack">Home</button>
            </center>
        </form>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="<%=basePath%>/resources/js/user/modifyuser.js"></script>
    <script src="<%=basePath%>/resources/js/navi.js"></script>
<jsp:include page="../navi-bottom.jsp"></jsp:include>