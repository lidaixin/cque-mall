<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <jsp:include page="../../jsp/common.jsp"/>
    <!-- 引入footer的css 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/order-info.css">
</head>

<body>
<!-- 头部 -->
<jsp:include page="../../jsp/header.jsp"/>

  <!-- 主体 -->
  <section class="container">
    <div class="row">
      <div style="margin:0 auto; margin-top:10px;width:950px;">
        <strong>我的订单</strong>
        <table class="table table-bordered">
            <c:forEach items="${orderList}" var="order">
                <tbody>
                <tr class="success">
                    <th colspan="6">订单编号:${order.id}</th>
                </tr>
                <tr class="warning">
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                    <th>状态</th>
                </tr>
                <c:forEach items="${order.orderItemList}" var="item">
                    <tr class="active">
                        <td width="60" width="40%">
                            <input type="hidden" name="id" value="22">
                            <img class="img-thumbnail" src="http://192.168.11.44:9090${item.product.image}" width="70" height="60">
                        </td>
                        <td width="30%">
                            <a target="_blank">${item.product.name}</a>
                        </td>
                        <td width="20%">
                            ￥${item.product.shopPrice}
                        </td>
                        <td width="10%">
                                ${item.quantity}
                        </td>
                        <td width="15%">
                            <span class="subtotal">￥${item.total}</span>
                        </td>
                        <td width="15%">
                            <span class="subtotal">已发货</span>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </c:forEach>
        </table>
      </div>
    </div>
    <div style="text-align: center;">
      <ul class="pagination">
        <li class="disabled"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
        <li class="active"><a href="#">1</a></li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">5</a></li>
        <li><a href="#">6</a></li>
        <li><a href="#">7</a></li>
        <li><a href="#">8</a></li>
        <li><a href="#">9</a></li>
        <li>
          <a href="#" aria-label="Next">
            <span aria-hidden="true">&raquo;</span>
          </a>
        </li>
      </ul>
    </div>
  </section>
<!-- 底部 -->
<jsp:include page="../../jsp/footer.jsp"/>
</body>

</html>