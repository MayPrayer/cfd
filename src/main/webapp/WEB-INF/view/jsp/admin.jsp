<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>后台管理面板</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
</head>
<body class="layui-layout-body">

<div class="layui-layout layui-layout-admin">

    <div class="layui-header">
        <div class="layui-logo">后台管理面板</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
<%--        <ul class="layui-nav layui-layout-left">--%>
<%--            <li class="layui-nav-item"><a href="">控制台</a></li>--%>
<%--            <li class="layui-nav-item"><a href="">商品管理</a></li>--%>
<%--            <li class="layui-nav-item"><a href="">用户</a></li>--%>
<%--            <li class="layui-nav-item">--%>
<%--                <a href="javascript:;">其它系统</a>--%>
<%--                <dl class="layui-nav-child">--%>
<%--                    <dd><a href="">邮件管理</a></dd>--%>
<%--                    <dd><a href="">消息管理</a></dd>--%>
<%--                    <dd><a href="">授权管理</a></dd>--%>
<%--                </dl>--%>
<%--            </li>--%>
<%--        </ul>--%>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="${user.avatar}" class="layui-nav-img">
                    ${user.name}&nbsp; &nbsp;&nbsp;欢迎您!
                </a>
            </li>
            <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/user/delsession">注销</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">用户信息管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/updateuserinfo/showpersoninfo">基础信息显示</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/user/modifypwd">密码修改</a></dd>
                        <dd><a href="javascript:;">联系方式</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">用户管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="${pageContext.request.contextPath}/updateuserinfo/showuser">用户管理</a></dd>
                        <dd><a href="">超链接</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">订单管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">订单管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">商品管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">商品管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">营业额统计</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">营业额统计</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="">营业额统计</a></li>
                <li class="layui-nav-item"><a href="">用户投诉</a></li>
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <iframe id="iframeMain" src="" style="width: 100%"; height="100%" scrolling="no" ;>


        </iframe>

    </div>

    <div class="layui-footer" style="text-align: center">
        <!-- 底部固定区域 -->
        Copyright 2020 MayPrayer All rights Reserved.v
    </div>
</div>

<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });


//    jq拿a的href值放到iframe的src属性上
    $(document).ready(function(){
        $("dd>a").click(function (e) {
            e.preventDefault();
            $("#iframeMain").attr("src",$(this).attr("href"));
        });
    });
</script>
</body>
</html>