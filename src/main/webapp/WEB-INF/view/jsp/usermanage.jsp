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

<div class="layui-fluid" >
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card" id="design">
                <div class="layui-card-header">
                    <div class="layui-row">
                        <div class="layui-col-md4">
                            <button class="layui-btn"><i class="layui-icon">&#xe654;</i>新增</button>
                        </div>
                        <div class="layui-col-md4 layui-col-md-offset4">
                            <di class="layui-layout-right">
                                <div class="layui-input-inline">
                                    <input type="text" id="codeLike" name="codeLike" placeholder="请输入账户名" autocomplete="off" class="layui-input">
                                </div>
                                <button class="layui-btn" οnclick="search()">搜索</button>
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
    <a class="layui-btn layui-btn-xs" lay-event="edit" style="background-color: #9F9F9F"><i class="layui-icon" >&#xe642;</i></a>
    <a class="layui-btn  layui-btn-xs" lay-event="del"  style="background-color:red"><i class="layui-icon" >&#xe640;</i></a>
</script>





</body>
</html>