<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>登录</title>
    <link rel="stylesheet" href="<%=basePath%>/resources/css/login.css">
    <link rel="stylesheet" href="<%=basePath%>/resources/css/loading.css">
    <link rel="shortcut icon" href="/resources/favicon.ico">
</head>
<body>
<%
    if (request.getSession().getAttribute("USER") != null) {
        response.sendRedirect(basePath + "/Home");
    }
%>
<form class="form" id="login">
    <h1>Login</h1>
    <div class="txtb">
        <input type="text" id="login-username">
        <span data-placeholder="Username"></span>
    </div>
    <div class="txtb">
        <input type="password" id="login-password">
        <span data-placeholder="Password"></span>
    </div>
    <div class="prompt">
        <span id="login-prompt">&nbsp;</span>
    </div>
    <button type="button" class="btn" id="login-login">Login</button>
    <div class="bottom-text">
        Don't have account?
        <button type="button" id="login-registered">Registered</button>
    </div>
</form>
<form class="form" id="registered">
    <h1>Registered</h1>
    <div class="txtb">
        <input type="text" id="registered-username">
        <span data-placeholder="Username"></span>
        <div class="prompt registered-prompt" id="registered-username-prompt">
            <span>用户名格式错误</span>
        </div>
    </div>
    <div class="txtb">
        <input type="password" id="registered-password">
        <span data-placeholder="Password"></span>
        <div class="prompt registered-prompt" id="registered-password-prompt">
            <span>密码格式错误</span>
        </div>
    </div>
    <div class="codeLeft txtb">
        <input type="text" id="VerificationCode">
        <span data-placeholder="VerificationCode"></span>
    </div>
    <div class="codeRight">
        <img id="captcha_img" alt="点击更换" title="点击更换"
             onclick="changeVerifyCode(this)" src="Kaptcha"/>
    </div>
    <div class="prompt">
        <span id="registered-prompt">&nbsp;</span>
    </div>
    <button type="button" class="btn" id="registered-registered">Registered</button>
    <div class="bottom-text">
        Have account?
        <button type="button" id="registered-login">Login</button>
    </div>
</form>
<div class="loading" id="loading">
    <div class="dot"></div>
    <div class="dot"></div>
    <div class="dot"></div>
    <div class="dot"></div>
    <div class="dot"></div>
</div>
<div class="full-screen-coverage" id="full-screen-coverage"></div>
<script src="https://cdn.bootcss.com/jquery/3.4.1/jquery.min.js"></script>
<script src="<%=basePath%>/resources/js/login.js"></script>
</body>
</html>