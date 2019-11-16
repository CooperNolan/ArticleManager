<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://at.alicdn.com/t/font_1298741_gw7g40ueihl.css">
<link rel="shortcut icon" href="/resources/image/favicon.ico">
</head>
<body>
<div class="loading" id="loading">
    <span class="loading-span" style="color: black">Loading...</span>
</div>
<div class="full-screen-coverage" id="full-screen-coverage"></div>
<div class="header">
    <h2 class="logo" id="user-name-label" name="${sessionScope.USER.userId}">${sessionScope.USER.nickname}</h2>
    <script type="text/javascript">
    </script>
    <input type="checkbox" id="chk">
    <label for="chk" class="show-menu-btn">
        <i class="iconfont icon-zhankaixiangqing"></i>
    </label>
    <ul class="menu">
        <button id="toHome">首页</button>
        <button id="userMsg">个人信息</button>
        <button id="myArticle">我的文章</button>
        <button id="writeArticle">文章编写</button>
        <c:if test="${sessionScope.USER.userStatus == 2}">
            <button id="categoryManager">分类管理</button>
        </c:if>
        <button id="logout">注销</button>
        <label for="chk" class="hide-menu-btn">
            <i class="iconfont icon-shouqizhankai"></i>
        </label>
    </ul>
</div>
<div>
