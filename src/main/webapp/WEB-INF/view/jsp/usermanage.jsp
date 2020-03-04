<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/2/26
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息展示</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/usermanage.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/usermanage.js"></script>
</head>

<body>
<%--弹出表单--%>
<div id="add-main" style="display: none;">
    <form class="layui-form" id="add-form" method="post">
        <%--     账户       --%>
        <div class="layui-form-item center">
            <label class="layui-form-label" style="width: 100px">账 户</label>
            <div class="layui-input-block">
                <input type="text" name="account" required style="width: 240px" lay-verify="required"
                       placeholder="请输入登录账户名" autocomplete="off" class="layui-input">
            </div>
        </div>
        <%--        昵称--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">昵 称</label>
            <div class="layui-input-block">
                <input type="text" name="nickname" required style="width: 240px" lay-verify="required" placeholder="请输入昵称"
                       autocomplete="off" class="layui-input">
                <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->
            </div>
        </div>

        <%--            密码--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">密 码</label>
            <div class="layui-input-block">
                <input type="text" name="pwd" required style="width: 240px" lay-verify="required|password" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
                <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->
            </div>
        </div>
        <%--            头像链接--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">头 像</label>
            <div class="layui-input-block">
                <input type="text" name="avatar" required style="width: 240px" lay-verify="required|avatar"
                       placeholder="请输入头像链接"
                       autocomplete="off" class="layui-input">
                <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->
            </div>
        </div>

        <%--姓名--%>
        <div class="layui-form-item ">
            <label class="layui-form-label" style="width: 100px">名 字</label>
            <div class="layui-input-block">
                <input type="text" name="name" required value="" style="width: 240px" lay-verify="required"
                       placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>
        </div>

        <%--           手机号--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">手 机 号</label>
            <div class="layui-input-block">
                <input type="text" name="phone" required style="width: 240px" lay-verify="required|phone" placeholder="请输入手机号"
                       autocomplete="off" class="layui-input">
                <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->
            </div>
        </div>


        <%--         生日--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">生 日</label>
            <div class="layui-input-block">
                <input type="text" name="birthday" required style="width: 240px" lay-verify="required" placeholder="请选择生日"
                       autocomplete="off" class="layui-input" id="birthday">
                <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->
            </div>
        </div>
        <%--           身份证号--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">身份证号</label>
            <div class="layui-input-block">
                <input type="text" name="idcard" required style="width: 240px" lay-verify="required|idcard"
                       placeholder="请输入身份证号"
                       autocomplete="off" class="layui-input">
                <!-- <input type="hidden" name="id" style="width: 240px" autocomplete="off" class="layui-input"> -->
            </div>
        </div>

        <%--            操作按钮--%>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="save" id="datatran">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" id="closebtn">重置</button>
            </div>
        </div>
    </form>
</div>

<%--直接显示内容--%>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card" id="design">
                <div class="layui-card-header">
                    <div class="layui-row">
                        <div class="layui-col-md4">
                            <button class="layui-btn" name="adduser"><i class="layui-icon">&#xe654;</i>新增</button>
                        </div>
                        <div class="layui-col-md4 layui-col-md-offset4">
                            <di class="layui-layout-right">
                                <div class="layui-input-inline">
                                    <input type="text" id="codeLike" name="codeLike" placeholder="请输入账户名"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <button class="layui-btn" name="search">搜索</button>
                            </di>
                        </div>
                    </div>
                </div>
                <div class="layui-card-body">
                    <%--table 容器--%>
                    <table id="demo" lay-filter="test"></table>
                </div>
            </div>
        </div>
    </div>
</div>

<input type="hidden" name="path" value="${pageContext.request.contextPath}">


<%--操作栏  编辑与删除--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit" style="background-color: #9F9F9F"><i
            class="layui-icon">&#xe642;</i></a>
    <a class="layui-btn  layui-btn-xs" lay-event="del" style="background-color:red"><i
            class="layui-icon">&#xe640;</i></a>
</script>


</body>
</html>