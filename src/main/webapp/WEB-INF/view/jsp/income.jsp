<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/3/12
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>营业额展示</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
<%--       <script src="${pageContext.request.contextPath}/static/js/echarts.js"></script>--%>
</head>
<body>
<div id="main" style="width: 600px;height:400px;"></div>
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

            //开始实现渲染
            var myChart = echarts.init(document.getElementById('main'));

            var option = {

                title: {

                    text: 'ECharts 入门示例'

                },

                tooltip: {},

                legend: {

                    data: ['销量']

                },

                xAxis: {

                    data: ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]

                },

                yAxis: {},

                series: [{

                    name: '销量',

                    type: 'bar',
                    data: [5, 20, 36, 10, 10, 20]

                }]

            };

            myChart.setOption(option);


        })

</script>


</body>


</html>
