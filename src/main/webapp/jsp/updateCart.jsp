<%--
  Created by IntelliJ IDEA.
  User: 22379
  Date: 2020/10/7
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>updateCart</title>
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
<form class="form-horizontal" action="/admin1/updateCategory" method="post">
    <div class="form-group">
        <label for="inputEmail3" class="col-sm-2 control-label">分类Id</label>
        <div class="col-sm-10">
            <input type="text" name="cid" disabled class="form-control" value="${param.cid}" id="inputEmail3">
        </div>
    </div>
    <div class="form-group">
        <label for="inputPassword3" class="col-sm-2 control-label">分类名称</label>
        <div class="col-sm-10">
            <input type="text" name="cname" value="${param.cname}" class="form-control" id="inputPassword3">
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <button type="submit" class="btn btn-default">修改分类</button>
        </div>
    </div>

    <%--表单非空验证  ajax请求查询是否已经存在--%>
    <script>

    </script>
</form>
</body>
</html>
