<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>未知错误</title>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/navi.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/error.css">
    <jsp:include page="../navi-top.jsp"></jsp:include>
    <div class="container">
        <div class="row">
            <div class="error-prompt">
                <h1>未知错误！</h1>
            </div>
            <center>
                <button type="button" class="btn" id="error-to-home">Home</button>
            </center>
        </div>
    </div>
    <script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
    <script src="<%=basePath%>/resources/js/navi.js"></script>
    <script type="text/javascript">
        $('#error-to-home').on('click', function () {
            loadingShow();
            window.location.href = window.location.origin + "/Home";
            loadingHide();
        });
    </script>
<jsp:include page="../navi-bottom.jsp"></jsp:include>