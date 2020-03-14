<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/2/26
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <link rel="stylesheet"   href="${pageContext.request.contextPath}/static/css/personinfo.css" >
</head>

<body>

<div class="person" >
    <div style="width:200px" align="center">
        <div class="to3" align="center">
            <div class="to2" align="center">
                <div class="to1" align="center">
                    <img class="to" alt="aaa" src="${user.avatar}">
                </div>
            </div>
        </div>
    </div>

    <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
        <legend>个人资料</legend>
    </fieldset>
    <hr>


    <div class="message">
<pre>
        <i class="layui-icon">&#xe66f;</i>账户名： ${user.account}

        <i class="layui-icon">&#xe612;</i>角色: ${role.name}

        <i class="layui-icon">&#xe678;</i>姓名: ${user.name}

        <i class="layui-icon">&#xe678;</i>手机号: ${user.phone}

        <i class="layui-icon">&#xe62d;</i>出生日期: <fmt:formatDate pattern="yyyy-MM-dd" value="${user.birthday }" type="both"/>

        <i class="layui-icon">&#xe677;</i>微信号: ${user.wechat}

        <i class="layui-icon">&#xe683;</i>身份证号: ${user.idcard}

        <i class="layui-icon">&#xe659;</i>零钱: ${user.change}
    </pre>
    </div>


</div>


</body>
</html>