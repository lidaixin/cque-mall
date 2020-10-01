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
                        <a href="/admin/category/list" class="list-group-item">商品类别管理</a>
                        <a href="/admin/product/list" class="list-group-item">商品管理</a>
                        <a href="/admin/order/list" class="list-group-item">订单管理</a>
                    </div>
                </div>
            </div>
        </div>
        <div class="cque-main">
            <div class="cque-list">
                <div class="cque-list-nav">
                    <button class="btn btn-default" type="button">添加</button>
                </div>
                <div class="cque-list-content">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>名称</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach begin="1" end="4" var="item" varStatus="status">
                            <tr>
                                <td>${status.count}</td>
                                <td>TB - Monthly</td>
                                <td class="cque-td">
                                    <button class="btn btn-default" type="button">修改</button>
                                    <button class="btn btn-default" type="button">删除</button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>