<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/2/23
  Time: 7:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/login.js"></script>
</head>
<body>
<form class="layui-form" method="post">
    <div class="container">

        <!--用户登录图片-->
        <div class="layui-form-mid layui-word-aux">
            <img id="logoid" src="${pageContext.request.contextPath}/static/img/login.jpg" height="35">
        </div>
        <!--   用户名框    -->
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="account" required lay-verify="required" placeholder="请输入用户名" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <!--    密码框-->
        <div class="layui-form-item">
            <label class="layui-form-label">密 &nbsp;&nbsp;码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
            <!--验证码框-->
        </div>
        <%--        <div class="layui-form-item">--%>
        <%--            <label class="layui-form-label">验证码</label>--%>
        <%--            <div class="layui-input-inline">--%>
        <%--                <input type="text" name="title" required lay-verify="required" placeholder="请输入验证码" autocomplete="off"--%>
        <%--                       class="layui-input verity">--%>
        <%--            </div>--%>

        <%--        </div>--%>

        <!--登录按钮及提示-->
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="logincfd">登陆</button>
            </div>
        </div>
        <!--忘记密码与立即注册跳转链接-->
        <%--        <a href="" class="font-set">忘记密码?</a> <a href="" class="font-set">立即注册</a>--%>
    </div>
</form>


</body>
</html>
