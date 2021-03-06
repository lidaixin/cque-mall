<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <!-- 引入favicon.ico网页图标 -->
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon"/>
    <title>睿道商城后台管理</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/admin-base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/admin.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
    <style type="text/css">
        .cque-list {
            margin: 10px;
            display: flex;
            flex-direction: column;
        }

        .cque-list-nav {
            margin: 10px;
        }

        .cque-list-content {
            margin: 10px;
        }

        .cque-td {
            display: flex;
            justify-content: space-around;
            align-items: center;
        }
    </style>
</head>

<body>

<div class="cque-window">
    <div class="cque-nav">
        <div class="navbar-header" style="margin-left: 20px">
            <img alt="Brand" height="60px" src="${pageContext.request.contextPath}/assets/images/logo.png">
        </div>
        <h2 style="margin-left: 20px">商城后台管理系统</h2>
    </div>
    <div class="cque-content">
        <div class="cque-side">
            <div class="panel panel-info" style="margin: 0;height: 100%">
                <div class="panel-heading">系统菜单</div>
                <div class="panel-body" style="padding: 0">
                    <div class="list-group">
                        <a href="${pageContext.request.contextPath}/admin1/cart/list" class="list-group-item">商品类别管理</a>
                        <a href="#" class="list-group-item">商品管理</a>
                        <a href="${pageContext.request.contextPath}/admin1/order/list" class="list-group-item">订单管理</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="cque-main">
            <div class="cque-list">
                <div class="cque-list-nav">
                    <a href="${pageContext.request.contextPath}/jsp/addProduct.jsp">
                        <button class="btn btn-default" type="button">添加</button>
                    </a>
                </div>
                <div class="cque-list-content">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>marketPrice</th>
                            <th>shopPrice</th>
                            <th>pImage</th>
                            <th>isHot</th>
                            <th>pdesc</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach begin="1" end="9" items="${pageResult.list}" var="product" varStatus="productStatu">
                            <tr>
                                <td>${productStatu.count}</td>
                                <td>${product.name}</td>
                                <td>${product.marketPrice}</td>
                                <td>${product.shopPrice}</td>
                                <td>${product.image}</td>
                                <td>${product.isHot}</td>
                                <td>${product.desc}</td>
                                <td class="cque-td">
                                    <a href="${pageContext.request.contextPath}/jsp/updateProduct.jsp?pid=${product.id}&name=${product.name}">
                                        <button class="btn btn-default btn-primary" type="button">修改</button>
                                    </a>
                                    <button onclick="deleteItem(${product.id})" type="button" class="btn btn-primary">
                                        删除
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <nav aria-label="Page navigation">
                        <ul class="pagination">
                        <c:if test="${pageResult.currentPage != 1}">
                            <li>
                                <a href="/admin1/product/list?cid=1&currentPage=${pageResult.currentPage-1}&pageSize=10" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                        </c:if>
                            <c:forEach begin="1" end="${pageResult.totalPage}" step="1" varStatus="status">
                                <li>
                                    <a href="/admin1/product/list?cid=1&currentPage=${status.count}&pageSize=10">${status.count}</a>
                                </li>
                            </c:forEach>
                        <c:if test="${pageResult.currentPage != pageResult.totalPage}">
                            <li>
                                <a href="/admin1/product/list?cid=1&currentPage=${pageResult.currentPage+1}&pageSize=10" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </c:if>
                        </ul>
                    </nav>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    // 删除商品
    function deleteItem(id) {
        var isDel = confirm('您确定要删除该商品吗?')
        if (!isDel)
            return
        $.post('/admin1/deleteProduct', {pid: id}, function (result) {
            // 跳转页面
            alert("删除成功")
            location.href = '/admin1/product/list'
        })
    }
</script>
</body>
</html>
