<%--
  Created by IntelliJ IDEA.
  User: 22379
  Date: 2020/10/7
  Time: 20:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addCart</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon"/>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath}/assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/admin-base.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/assets/css/admin.css" rel="stylesheet">
    <!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
    <script src="${pageContext.request.contextPath}/assets/js/jquery-3.4.1.min.js"></script>
    <!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
    <script src="${pageContext.request.contextPath}/assets/js/bootstrap.min.js"></script>
</head>

<body>
<br><br><br><br>
<form class="form-horizontal" action="/admin1/addOrder" method="post">
    <div class="form-group">
        <label for="inputEmail3" class="col-sm-2 control-label">状态</label>
        <div class="col-sm-10">
            <input type="text" name="state" class="form-control" id="inputEmail3" placeholder="状态">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">收货地址</label>
        <div class="col-sm-10">
            <input type="text" name="address" class="form-control" id="inputPassword3" placeholder="收货地址">
        </div>
    </div>
    <div class="form-group">
        <label for="inputEmail4" class="col-sm-2 control-label">收货人姓名</label>
        <div class="col-sm-10">
            <input type="text" name="name" class="form-control" id="inputEmail4" placeholder="收货人姓名">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword4" class="col-sm-2 control-label">电话</label>
        <div class="col-sm-10">
            <input type="text" name="tel" class="form-control" id="inputPassword4" placeholder="电话">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">添加收货地址</button>
        </div>
    </div>

    <%--表单非空验证  ajax请求查询是否已经存在--%>
    <script>

    </script>
</form>
</body>
</html>
