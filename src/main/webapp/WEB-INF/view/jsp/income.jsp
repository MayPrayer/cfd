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
    <script src="${pageContext.request.contextPath}/static/js/income.js"></script>

</head>
<body>
<div style="padding: 15px; background-color: #F2F2F2;">
    <input type="hidden" name="path" value="${pageContext.request.contextPath}">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-xs12 layui-col-sm6 layui-col-md4 layui-col-lg4">
            <div class="grid-demo">
                <a href="javascript:;">
                    <div class="panel_icon" style="background-color: #1e9fff;">
                        <i class="layui-icon">&#xe63c;</i>
                    </div>
                    <div class="panel_word" style="color: #1e9fff">
                        <span>${message.totalOrders}</span>
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
                        <span>${message.totalUsers}</span>
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
                        <span>${message.totalIncome}</span>
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
                    <div style="width: 100%; height: 300px;" id="order">
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
    }).use(['element', 'echarts', 'carousel', 'jquery'], function () {
        var element = layui.element;
        var echarts = layui.echarts;
        var carousel = layui.carousel;
        var $ = layui.$;
        var path = $("input[name='path']").val();
        console.log(path);
        var incomepath = path + "/ordermanager/getincome";
        console.log(incomepath);
        //    页面加载就发送ajax 获取数据
        $(function () {

            $.ajax({
                    url: incomepath,
                    type: "POST",
                    success: function (result) {
                        alert("我被执行了");
                        var data = result;
                        console.log(data.data[0]);

                        ordeRender(data);
                    },
                    error: function () {
                        layer.msg("当前连接数偏多，请稍后重试！");
                    }
                }
            );


        });


        /*
        * 渲染订单柱形图
        * */

        //初始化
        function ordeRender(data) {

            console.log("执行了语句");
            //只能用原生
            var myorderChart = echarts.init(document.getElementById('order'));
           /* var myorderChart = echarts.init($("#order"));*/


            var orderOption = {
                title: {
                    text: '商品订单柱形图'
                },
                //提示框组件
                tooltip: {
                    //坐标轴触发，主要用于柱状图，折线图等
                    trigger: 'axis'
                },
                //数据全部显示
                axisLabel: {
                    interval: 0
                },
                //图例
                legend: {
                    data: ['订单数']
                },
                //横轴 日期
                xAxis: {
                    data: data.data[0]
                },
                //纵轴 自动分配
                yAxis: {type: 'value'},
                //系列列表。每个系列通过type决定自己的图表类型
                series: [
                    {
                        name: '订单数',
                        //type 为图形的选择，治理为柱状图
                        type: 'bar',
                        //订单数
                        data: data.data[1],//处理小数点数据
                        itemStyle: {
                            normal: {//柱子颜色
                                color: '#4ad2ff'
                            }
                        }
                    }
                ]
            };
            console.log("打印下填充数据"+orderOption);
            //填充数据
            myorderChart.setOption(orderOption);

        }


    })






</script>



</body>


</html>
