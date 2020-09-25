<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <jsp:include page="common.jsp"/>
    <!-- 引入首页的css 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/index.css">
</head>

<body>
<!-- 头部 -->
<jsp:include page="header.jsp"/>
<div class="container-fluid">
    <!-- 轮播图 start -->
    <div class="row">
        <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
            <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <div class="carousel-img-container" style="background-color: #801BB7;">
                        <img src="${pageContext.request.contextPath}/assets/images/banner/banner1.png">
                    </div>
                </div>
                <div class="item">
                    <div class="carousel-img-container" style="background-color: #FFCE12;">
                        <img src="${pageContext.request.contextPath}/assets/images/banner/banner2.jpg">
                    </div>
                </div>
                <div class="item">
                    <div class="carousel-img-container" style="background-color: #E80C30;">
                        <img src="${pageContext.request.contextPath}/assets/images/banner/banner3.jpg">
                    </div>
                </div>
                <div class="item">
                    <div class="carousel-img-container" style="background-color: #4282AE;">
                        <img src="${pageContext.request.contextPath}/assets/images/banner/banner4.jpg">
                    </div>
                </div>
            </div>
            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div>
    <!-- 轮播图 end -->
</div>
<!-- 主体 -->
<section class="container">
    <!-- 分类展示 start -->
    <div class="category-item">
        <div class="row product-title">
            <span class="category-name">最热</span>
            <div class="category-more-container">
                <span class="category-more">查看全部 <i class="glyphicon glyphicon-chevron-right"></i></span>
            </div>
        </div>
        <div class="row product-container">
            <div class="product-left">
                <a href="" class="card">
                    <img src="${pageContext.request.contextPath}/assets/images/product/left.webp" alt="">
                </a>
            </div>
            <div class="product-right">
                <c:forEach items="${hotList}" var="product">
                    <div class="product-item">
                        <a class="product-info card" href="/product/detail?id=${product.id}">
                            <img src="${product.image}" style="margin-top: 20px" alt="">
                            <h3 class="title"
                                style="text-align: center; width: 100px; height: 30px; overflow: hidden">${product.name}</h3>
                            <p class="desc" style="width: 100px; height: 30px; overflow: hidden">${product.desc}</p>
                            <p class="price">
                                <span class="num">${product.shopPrice}</span>元
                            </p>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- 分类展示 end -->
    <!-- 分类展示 start -->
    <div class="category-item">
        <div class="row product-title">
            <span class="category-name">最新</span>
            <div class="category-more-container">
                <span class="category-more">查看全部 <i class="glyphicon glyphicon-chevron-right"></i></span>
            </div>
        </div>
        <div class="row product-container">
            <div class="product-left">
                <a href="" class="card">
                    <img src="${pageContext.request.contextPath}/assets/images/product/left.webp" alt="">
                </a>
            </div>
            <div class="product-right">
                <c:forEach items="${newsList}" var="product">
                    <div class="product-item">
                        <a class="product-info card" href="/product/detail?id=${product.id}">
                            <img src="${product.image}" style="margin-top: 20px" alt="">
                            <h3 class="title"
                                style="text-align: center; width: 100px; height: 30px; overflow: hidden">${product.name}</h3>
                            <p class="desc" style="width: 100px; height: 30px; overflow: hidden">${product.desc}</p>
                            <p class="price">
                                <span class="num">${product.shopPrice}</span>元
                            </p>
                        </a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <!-- 分类展示 end -->
</section>
<!-- 底部 -->
<jsp:include page="footer.jsp"/>
</body>

</html>