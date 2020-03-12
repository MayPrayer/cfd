<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/3/12
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>营业额展示</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <link rel="stylesheet"href="${pageContext.request.contextPath}/static/css/income.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
</head>
<body>
<div style="padding: 15px; background-color: #F2F2F2;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg4">
            <div class="grid-demo">
                <a href="javascript:;">
                    <div class="panel_icon" style="background-color: #1e9fff;">
                        <i class="layui-icon">&#xe63c;</i>
                    </div>
                    <div class="panel_word" style="color: #1e9fff">
                        <span>30</span>
                        <cite>总订单数</cite>
                    </div>
                </a>
            </div>
        </div>
        <div class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg4">
            <div class="grid-demo">
                <a href="javascript:;">
                    <div class="panel_icon" style="background-color: #FFB800;">
                        <i class="layui-icon">&#xe770;</i>
                    </div>
                    <div class="panel_word" style="color: #FFB800;">
                        <span>30</span>
                        <cite>总用户数</cite>
                    </div>
                </a>
            </div>
        </div>
        <div class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg4">
            <div class="grid-demo">
                <a href="javascript:;">
                    <div class="panel_icon" style="background-color: #009688;">
                        <i class="layui-icon">&#xe65e;</i>
                    </div>
                    <div class="panel_word" style="color: #009688;">
                        <span>30</span>
                        <cite>营收金额</cite>
                    </div>
                </a>
            </div>
        </div>
    </div>

    <div class="layui-row layui-col-space15">
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">商品订单</div>
                <div class="layui-card-body">
                    <div style="width: 100%; height: 300px;" id="Shopping">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">营收金额</div>
                <div class="layui-card-body">
                    <div  class="layui-carousel" id="Notice_carousel" lay-filter="Notice_carousel">
                        <div carousel-item>
                            <div  id="Notice"></div>
                            <div id="NoticeAll"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">用户数量</div>
                <div class="layui-card-body">
                    <div style="width: 100%; height: 300px;" id="UserSum">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    layui.config({
        //导入echarts模块
        version: 1,
        base: '${pageContext.request.contextPath}/static/js/'
    }).use(['element', 'echarts', 'carousel'], function () {
            var element = layui.element;
            var echarts = layui.echarts;
            var carousel = layui.carousel;
            console.log(echarts);

        })

</script>


</body>


</html>
