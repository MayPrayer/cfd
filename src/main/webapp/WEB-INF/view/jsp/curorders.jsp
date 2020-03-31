<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/3/26
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
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
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/curorders.js"></script>

</head>

<body>

<%--订单详情页--%>
<div id="more" style="display: none;">
    <form class="layui-form" id="add-form" method="post">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top:3px;margin-left:3px">
            <legend><h5 style="color: #009688">订单信息</h5></legend>
        </fieldset>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">订单编号</label>
            <div class="layui-input-inline">
                <input type="text" name="orderid" required style="width: 240px" class="layui-input" readonly>
            </div>
            <label class="layui-form-label" style="width: 100px">创建时间</label>
            <div class="layui-input-inline">
                <input type="text" name="ordertime" required style="width: 240px" class="layui-input" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">创建用户</label>
            <div class="layui-input-inline">
                <input type="text" name="createuser" required style="width: 240px" class="layui-input" readonly>
            </div>
            <label class="layui-form-label" style="width: 100px">手机号码</label>
            <div class="layui-input-inline">
                <input type="text" name="userphone" required style="width: 240px" class="layui-input" readonly>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">订单状态</label>
            <div class="layui-input-inline">
                <input type="text" name="orderstate" required style="width: 240px" class="layui-input" readonly>
            </div>

        </div>




        <fieldset class="layui-elem-field layui-field-title" style="margin-top:3px;margin-left:3px">
            <legend><h5 style="color: #009688">联系信息</h5></legend>
        </fieldset>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">接收用户</label>
            <div class="layui-input-inline">
                <input type="text" name="touser" required style="width: 240px" class="layui-input" readonly>
            </div>
            <label class="layui-form-label" style="width: 100px">联系方式</label>
            <div class="layui-input-inline">
                <input type="text" name="tophone" required style="width: 240px" class="layui-input" readonly>
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">收货地址</label>
            <div class="layui-input-inline">
                <input type="text" name="address" required style="width: 240px" class="layui-input" readonly>
            </div>
        </div>

        <fieldset class="layui-elem-field layui-field-title" style="margin-top:3px;margin-left:3px">
            <legend><h5 style="color: #009688">商品信息</h5></legend>
        </fieldset>

        <div class="layui-form-item layui-form-text">

            <label class="layui-form-label" style="width: 100px">商品详情</label>
            <div class="layui-input-inline" >
                <div name="goodsinfo" style="width: 235px;border-style: dashed;"> </div>
            </div>

            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline" >
                <textarea  class="layui-textarea" name="remark" readonly  style="width: 209px"></textarea>
            </div>


        </div>
    </form>
</div>


<%--直接显示内容--%>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card" style="margin-top: 50px">
                <div class="layui-card-header">
                    <div class="layui-row">
                        <div class="layui-col-md4">
                            <button class="layui-btn" name="exportexcel"><i class="layui-icon">&#xe655;</i>导出excel</button>
                        </div>
                        <div class="layui-col-md4 layui-col-md-offset4">
                            <di class="layui-layout-right">
                                <div class="layui-input-inline">
                                    <input type="text" name="startday" required style="width: 120px"  placeholder="请选择开始时间"
                                           autocomplete="off" class="layui-input" id="startday">
                                </div>
                                <div class="layui-input-inline">
                                    <input type="text" name="enday" required style="width: 120px"  placeholder="请选择结束时间"
                                           autocomplete="off" class="layui-input" id="enday">
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
    <a class="layui-btn layui-btn-xs" lay-event="seemore" style="background-color: #9F9F9F"><i
            class="layui-icon">&#xe615;</i></a>
    <a class="layui-btn  layui-btn-xs" lay-event="del" style="background-color:red"><i
            class="layui-icon">&#xe640;</i></a>
</script>

</html>