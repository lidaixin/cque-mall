<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <jsp:include page="common.jsp"/>
    <!-- 引入首页的css 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/list.css">
</head>

<body>
<!-- 头部 -->
<jsp:include page="header.jsp"/>
<!-- 列表 start -->
<section class="container">
    <div class="row">
        <span class="category-name">${category.cname}</span>
    </div>
    <div class="row">
        <div class="space-line"></div>
    </div>
    <div class="row list-container">
        <c:forEach items="${pageResult.list}" var="product">
            <a href="/product/detail?id=${product.id}">
                <div class="list-item">
                    <img src="${product.image}" alt="">
                    <div class="price">
                        <strong>
                            <em>¥</em>
                            <i>${product.shopPrice}</i>
                        </strong>
                    </div>
                    <div class="attr">
                        <em>${product.name}</em>
                    </div>
                    <a href="javascript:;" class="add-to-cart">加入购物车</a>
                </div>
            </a>
        </c:forEach>
    </div>
    <div class="row page-num-container">
        <div class="pageNum">
            <ul>
                <c:if test="${pageResult.currentPage == 1}">
                    <li style="background-color: gray;cursor: not-allowed;">
                        <a style="color: #fff; cursor: not-allowed;" href="javascript:;">首页</a>
                    </li>
                </c:if>
                <c:if test="${pageResult.currentPage != 1}">
                    <li>
                        <a href="/product-list?cid=${category.cid}&currentPage=1&pageSize=10">首页</a>
                    </li>
                </c:if>
                <c:if test="${pageResult.currentPage == 1}">
                    <li style="width: 100px;background-color: gray;cursor: not-allowed;">
                        <a style="color: #fff; cursor: not-allowed;" href="javascript:;">上一页</a>
                    </li>
                </c:if>
                <c:if test="${pageResult.currentPage > 1}">
                    <li style="width: 100px;">
                        <a href="/product-list?cid=${category.cid}&currentPage=${pageResult.currentPage - 1}&pageSize=10">上一页</a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${pageResult.totalPage}" step="1" varStatus="status">
                    <c:if test="${pageResult.currentPage == status.count}">
                        <li style="background-color: blue;cursor: not-allowed;">
                            <a style="color: #fff; cursor: not-allowed;" href="javascript:;">${status.count}</a>
                        </li>
                    </c:if>
                    <c:if test="${pageResult.currentPage != status.count}">
                        <li>
                            <a href="/product-list?cid=${category.cid}&currentPage=${status.count}&pageSize=10">${status.count}</a>
                        </li>
                    </c:if>
                </c:forEach>
                <c:if test="${pageResult.currentPage == pageResult.totalPage}">
                    <li style="width: 100px;background-color: gray;cursor: not-allowed;">
                        <a style="color: #fff; cursor: not-allowed;" href="javascript:;">下一页</a>
                    </li>
                </c:if>
                <c:if test="${pageResult.currentPage < pageResult.totalPage}">
                    <li style="width: 100px;">
                        <a href="/product-list?cid=${category.cid}&currentPage=${pageResult.currentPage + 1}&pageSize=10">下一页</a>
                    </li>
                </c:if>
                <c:if test="${pageResult.currentPage == pageResult.totalPage}">
                    <li style="background-color: gray;cursor: not-allowed;">
                        <a style="color: #fff; cursor: not-allowed;" href="javascript:;">末页</a>
                    </li>
                </c:if>
                <c:if test="${pageResult.currentPage != pageResult.totalPage}">
                    <li>
                        <a href="/product-list?cid=${category.cid}&currentPage=${pageResult.totalPage}&pageSize=10">末页</a>
                    </li>
                </c:if>
            </ul>
            <div class="page_num_inf">
                共 <span>${pageResult.totalPage}</span> 页
                共 <span>${pageResult.totalNumber}</span> 条
            </div>
        </div>
    </div>
</section>
<!-- 列表 end -->
<!-- 底部 -->
<jsp:include page="footer.jsp"/>
</body>

</html>