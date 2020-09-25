<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <!-- 引入header的css 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/header.css">
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
</head>
<body>
<!-- 头部 -->
<header class="container-fluid">
    <!-- 顶部 start -->
    <div class="row site-topbar">
        <ul class="container site-topbar-container">
            <c:if test="${not empty loginUser}">
                <li class="site-topbar-info" style="color: gold">${loginUser.name},欢迎您</li>
                <li class="site-topbar-info"><a href="/order/list">我的订单</a></li>
                <li class="site-topbar-info"><a href="javascript:;" onclick="logout()">退出</a></li>
            </c:if>
            <c:if test="${empty loginUser}">
                <li class="site-topbar-info"><a href="/jsp/login.jsp">登录</a></li>
                <li class="site-topbar-info"><a href="/jsp/register.html">注册</a></li>
            </c:if>
            <li class="site-topbar-info"><a href="/cart/list"><i class="glyphicon glyphicon-shopping-cart"></i> 购物车</a>
            </li>
        </ul>
    </div>
    <!-- 顶部 end -->
    <!-- 导航栏 start -->
    <div class="row">
        <div class="container site-nav-container">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/assets/images/logo.png" title="睿道商城">
            </div>
            <ul id="categoryUL" class="nav">
                <li><a href="/product-list?cid=1">手机</a></li>
            </ul>
            <div class="search-container">
                <input type="text">
                <a class="search-button"><i class="glyphicon glyphicon-search"></i></a>
            </div>
        </div>
    </div>
    <!-- 导航栏 end -->
</header>
<script>
    $(function () {
        // 发送请求获取分类列表
        $.get('/category/list', function (categoryList) {
            var str = ''
            $.each(categoryList, function (index, category) {
                str += `<li><a href="/product/list?cid=\${category.cid}&currentPage=1&pageSize=10">\${category.cname}</a></li>`
            })
            $('#categoryUL').html(str)
        }, 'json')
    })

    function logout() {
        $.get('/user/logout', function (result) {
            if (result.code === 1) {
                location.href = '/'
            }
        })
    }
</script>
</body>
</html>
