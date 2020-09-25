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
    <c:if test="${empty order}">
        <h2 style="color: #4cae4c; text-align: center">由于您的商品比较热销,我们将为您提供VIP服务,请联系客服人员...</h2>
    </c:if>
    <c:if test="${not empty order}">
        <div class="row">
            <div style="margin: 10px auto 0;width:950px;">
                <strong>订单详情</strong>
                <table class="table table-bordered">
                    <tbody>
                    <tr class="warning">
                        <th colspan="5">订单编号:${order.id} </th>
                    </tr>
                    <tr class="warning">
                        <th>图片</th>
                        <th>商品</th>
                        <th>价格</th>
                        <th>数量</th>
                        <th>小计</th>
                    </tr>
                    <c:forEach items="${order.orderItemList}" var="item">
                        <tr class="active">
                            <td width="60" width="40%">
                                <input type="hidden" name="id" value="22">
                                <img src="http://192.168.11.44:9090${item.product.image}" width="70" height="60">
                            </td>
                            <td width="30%">${item.product.desc}</td>
                            <td width="20%">￥${item.product.shopPrice}</td>
                            <td width="10%">${item.quantity}</td>
                            <td width="15%"><span class="subtotal">￥${item.total}</span></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <div style="text-align:right;margin-right:120px;">
                商品金额: <strong style="color:#ff6600;">￥${order.total}元</strong>
            </div>
        </div>
        <div>
            <hr/>
            <form action="/order/update-order" method="post" class="form-horizontal"
                  style="margin-top:5px;margin-left:150px;">
                <input type="hidden" name="id" value="${order.id}"/>
                <div class="form-group">
                    <label for="address" class="col-sm-1 control-label">地址</label>
                    <div class="col-sm-5">
                        <input id="address" name="address" type="text" class="form-control" placeholder="请输入收货地址">
                    </div>
                </div>
                <div class="form-group">
                    <label for="name" class="col-sm-1 control-label">收货人</label>
                    <div class="col-sm-5">
                        <input type="text" name="name" class="form-control" id="name" placeholder="请输收货人">
                    </div>
                </div>
                <div class="form-group">
                    <label for="telephone" class="col-sm-1 control-label">电话</label>
                    <div class="col-sm-5">
                        <input type="text" name="telephone" class="form-control" id="telephone" placeholder="请输入联系方式">
                    </div>
                </div>
                <hr/>
                <div style="margin-top:5px;margin-left:150px;">
                    <strong>在线支付：</strong>
                    <p>
                        <br/>
                        <input type="radio" name="pd_FrpId" value="ICBC-NET-B2C" checked="checked"/>支付宝
                        <img height="30px" src="${pageContext.request.contextPath}/assets/images/zhifubao.png"
                             align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="BOC-NET-B2C"/>微信支付
                        <img height="30px" src="${pageContext.request.contextPath}/assets/images/weixin.png"
                             align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <br/>
                        <br/>
                        <input type="radio" name="pd_FrpId" value="BOCO-NET-B2C"/>中国银行
                        <img src="${pageContext.request.contextPath}/assets/images/bc.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="PINGANBANK-NET"/>建设银行
                        <img src="${pageContext.request.contextPath}/assets/images/ccb.bmp" align="middle"/>&nbsp;&nbsp;&nbsp;&nbsp;
                        <input type="radio" name="pd_FrpId" value="CCB-NET-B2C"/>工商银行
                        <img src="${pageContext.request.contextPath}/assets/images/icbc.bmp" align="middle"/>
                    </p>
                    <hr/>
                    <p style="text-align:right;margin-right:100px;">
                        <button type="submit">
                            <img src="${pageContext.request.contextPath}/assets/images/finalbutton.gif" width="204"
                                 height="51"
                                 border="0"/>
                        </button>
                    </p>
                    <hr/>
                </div>
            </form>

        </div>
    </c:if>

</section>
<!-- 底部 -->
<jsp:include page="../../jsp/footer.jsp"/>
</body>

</html>