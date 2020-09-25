<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="common.jsp"/>
    <!-- 引入footer的css 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/detail.css">
</head>
<body>
<!-- 头部 -->
<jsp:include page="header.jsp"/>
<!-- 主体 -->
<section class="container">
    <div class="row product-content-container">
        <img class="col-md-4 col-md-offset-1 img-thumbnail" src="${product.image}" alt="">
        <div class="col-md-4 col-md-offset-2 price-container">
            <span class="product-title">${product.name}</span>
            <span class="product-price"><del>市场价：¥${product.marketPrice}</del></span>
            <span class="product-price">商城特惠：¥${product.shopPrice}</span>
            <form class="form-inline">
                <div class="form-group">
                    <label for="num">商品数量：</label>
                    <input type="number" class="form-control" id="num">
                </div>
            </form>
            <div class="product-option">
                <a href="javascript:;" class="add-to-cart" onclick="addItem()">加入购物车</a>
            </div>
        </div>
    </div>
    <div class="row product-detail-container">
        <span class="product-detail-title">商品详情</span>
        <div class="product-detail-info">${product.desc}</div>
    </div>
</section>
<!-- 底部 -->
<jsp:include page="footer.jsp"/>
<script>
    function addItem() {
        var num = parseInt($('#num').val())
        if (isNaN(num) || num <= 0) {
            alert("商品数量至少为1")
            return
        }
        $.post('/cart/add-item', {pid:${product.id}, num: num}, function (result) {
            if (result.code === 0) {
                alert('请登录以后在选购商品')
            } else {
                alert('商品添加成功')
            }
        })
    }
</script>
</body>

</html>