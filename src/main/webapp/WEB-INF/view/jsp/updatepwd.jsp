<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/2/25
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>更新密码</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/updatepwd.js"></script>
</head>
<body>

<form class="layui-form" method="post" id="modifypwd">
<%--   获取项目根路径--%>
    <input type="hidden" name="path" value="${pageContext.request.contextPath}">
<%--    输入框1--%>
    <div class="layui-form-item" style="margin-top: 50px">
        <label class="layui-form-label">登录名</label>
        <div class="layui-input-inline">
            <input type="text" name="account" required  lay-verify="required" placeholder="请输入账号名" autocomplete="off" class="layui-input">
        </div>
    </div>
<%--    密码框1--%>
    <div class="layui-form-item">
        <label class="layui-form-label">原始密码</label>
        <div class="layui-input-inline">
            <input type="password" name="oldpassword" required lay-verify="required" placeholder="请输入原始密码" autocomplete="off" class="layui-input">
        </div>
    </div>

    <%--    密码框2--%>
    <div class="layui-form-item">
        <label class="layui-form-label">新设密码</label>
        <div class="layui-input-inline">
            <input type="password" name="newpassword" required lay-verify="required|password" placeholder="请输入修改密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">新设密码必须6到12位，且不能出现空</div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-inline">
            <input type="password" name="configpassword" required lay-verify="required|confirmPass" placeholder="请输入确认密码" autocomplete="off" class="layui-input">
        </div>
        <div class="layui-form-mid layui-word-aux">确认密码必须与输入密码一致</div>
    </div>

<%--选择按钮--%>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button class="layui-btn" lay-submit lay-filter="submitpwd">立即修改</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

</body>
</html>
