<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="../../jsp/common.jsp"/>
    <!-- 引入footer的css 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/cart.css">
    <%--    <script src="${pageContext.request.contextPath}/assets/js/car.js"></script>--%>
</head>

<body>
<!-- 头部 -->
<jsp:include page="../../jsp/header.jsp"/>
<!-- 主体 -->
<section class="container">
    <div class="row">
        <span class="col-md-1 cart-title">购物车</span>
    </div>
    <c:if test="${not empty cart}">
        <div class="row">
            <div class="cart-thead">
                <div class="col-md-1 t-checkbox">
                    <input type="checkbox" name="" id="" class="checkall"> 全选
                </div>
                <div class="col-md-4 t-goods">商品</div>
                <div class="col-md-2 t-price">单价</div>
                <div class="col-md-2 t-num">数量</div>
                <div class="col-md-2 t-sum">小计</div>
                <div class="col-md-1 t-action">操作</div>
            </div>
        </div>
        <c:forEach items="${cart.list}" var="cartItem">
            <div class="row cart-item">
                <div class="col-md-1 p-checkbox">
                    <input type="checkbox" name="" id="" class="j-checkbox">
                </div>
                <div class="col-md-4 p-goods">
                    <div class="row">
                        <div class="col-md-4 p-img img-thumbnail">
                            <img width="40px" height="40px" src="http://192.168.11.44:9090${cartItem.product.image}" alt="">
                        </div>
                        <div class="col-md-8 p-msg">${cartItem.product.desc}</div>
                    </div>
                </div>
                <div class="col-md-2 p-price">￥${cartItem.product.shopPrice}</div>
                <div class="col-md-2 p-num">
                    <div class="row quantity-form">
                        <a href="javascript:;" onclick="updateItem(${cartItem.product.id},-1)"
                           class="col-md-3 decrement">-</a>
                        <input id="productNum" type="text" class="col-md-6 itxt" value="${cartItem.num}">
                        <a href="javascript:;" onclick="updateItem(${cartItem.product.id},1)"
                           class="col-md-3 increment">+</a>
                    </div>
                </div>
                <div class="col-md-2 p-sum">￥${cartItem.sumPrice}</div>
                <div class="col-md-1 p-action">
                    <a href="javascript:;"
                       onclick="deleteItem(${cartItem.product.id})">删除</a>
                </div>
            </div>
        </c:forEach>
        <!-- 结算模块 -->
        <div class="row cart-floatbar">
            <div class="col-md-1 select-all">
                <input type="checkbox" name="" id="" class="checkall">全选
            </div>
            <div class="col-md-2 operation">
                <a href="javascript:;" class="remove-batch"> 删除选中的商品</a>
                <a href="javascript:;" class="clear-all">清理购物车</a>
            </div>
            <div class="col-md-6 col-md-offset-5 toolbar-right">
                <div class="row">
                    <div class="col-md-3 amount-sum">已经选<em>${not empty cart ? cart.totalNum : 0}</em>件商品</div>
                    <div class="col-md-5 price-sum">总价： <em>￥${not empty cart ? cart.totalPrice : 0.00}</em></div>
                    <a href="/order/create-order">
                        <div class="col-md-4 btn-area">提交订单</div>
                    </a>
                </div>
            </div>
        </div>
    </c:if>
    <c:if test="${empty cart}">
        <h2 style="color: #4cae4c; text-align: center">您还没有选购商品</h2>
    </c:if>
</section>
<!-- 底部 -->
<jsp:include page="../../jsp/footer.jsp"/>
<script>
    // 修改购物项数量
    function updateItem(pid, num) {
        // 判断数量是否<=0
        var pNum = parseInt($('#productNum').val())
        var updateNum = parseInt(num)
        if ((pNum + updateNum) <= 0) {
            alert('商品数量不能为0')
            return
        }
        $.post('/cart/add-item', {pid: pid, num: num}, function (result) {
            // 跳转页面
            location.href = '/cart/list'
        })
    }

    // 删除购物项
    function deleteItem(pid) {
        var isDel = confirm('您确定要删除该商品吗?')
        if (!isDel)
            return
        $.post('/cart/delete-item', {pid: pid}, function (result) {
            // 跳转页面
            location.href = '/cart/list'
        })
    }
</script>
</body>

</html>