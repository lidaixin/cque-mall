<%@page contentType="text/html;charset=utf-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">

<head>
    <jsp:include page="common.jsp"/>
    <!-- 引入首页的css 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/login.css">
</head>

<body>
<!-- 头部 -->
<jsp:include page="header.jsp"/>
<!-- 头部 end -->
<section id="login_wrap">
    <div class="fullscreen-bg"
         style="background: url(${pageContext.request.contextPath}/assets/images/login-bg.png);height: 532px;"></div>
    <div class="login-box">
        <div class="title">
            <img height="40px" src="${pageContext.request.contextPath}/assets/images/logo.png" alt="">
            <span>欢迎登录睿道商城</span>
        </div>
        <div class="login_inner">
            <!--登录错误提示消息-->
            <div id="errorMsg" class="alert alert-danger"></div>
            <form id="loginForm" action="" method="post" accept-charset="utf-8">
                <input type="hidden" name="action" value="login"/>
                <input id="username" name="username" type="text" placeholder="请输入账号" autocomplete="off">
                <input id="password" name="password" type="password" placeholder="请输入密码" autocomplete="off">
                <div class="verify">
                    <input id="verifyCode" name="check" type="text" placeholder="请输入验证码" autocomplete="off">
                    <span>
<%--                        <img src="${pageContext.request.contextPath}/assets/images/verify_code.jpg" alt="">--%>
                        <img id="verifyImg" src="/captcha" width="131px" height="40px"/>
                    </span>
                </div>
                <div class="submit_btn">
                    <button id="loginBtn" type="button">登录</button>
                    <div class="auto_login">
                        <input type="checkbox" name="" class="checkbox">
                        <span>自动登录</span>
                    </div>
                </div>
            </form>
            <div class="reg">没有账户？<a href="javascript:;">立即注册</a></div>
        </div>
    </div>
</section>
<!-- 底部 -->
<jsp:include page="footer.jsp"/>
<script>
    $(function () {
        $('#verifyImg').click(function (ev) {
            // 对于src路径来说,
            // 如果访问的是静态资源,那么必须不一致才会发送请求,如果修改的src和上次一样,则不会发送请求
            // 如果访问的是动态资源则都会发送请求
            $('#verifyImg').attr('src', `/captcha?time=\${+new Date()}`)
        })

        $('#loginBtn').click(function (ev) {
            $('#errorMsg').html('').css('display', 'none')
            // 获取表单数据
            var username = $('#username').val()
            var password = $('#password').val()
            var code = $('#verifyCode').val()
            // 前端非空校验
            if (username === '') {
                $('#errorMsg').html('用户名不能为空').css('display', 'block')
                return
            }
            if (password === '') {
                $('#errorMsg').html('密码不能为空').css('display', 'block')
                return
            }
            if (code === '') {
                $('#errorMsg').html('验证码不能为空').css('display', 'block')
                return
            }
            $.get('/user/login', {username: username, password: password, verCode: code}, function (result) {
                if (result.code === 0) {
                    // 如果不成功,刷新验证码
                    $('#verifyImg').attr('src', '/captcha')
                    // 显示错误信息
                    $('#errorMsg').html(result.info).css('display', 'block')
                } else {
                    // 跳转页面
                    location.href = '/'
                }
            })
        })
    })
</script>
</body>

</html>