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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/income.css">
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
                <div class="layui-card-header">订单详情</div>
                <div class="layui-card-body">
                    <div style="width: 100%; height: 300px;" id="order">
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md6">
            <div class="layui-card">
                <div class="layui-card-header">营收详情</div>
                <div class="layui-card-body">
                    <div class="layui-carousel" id="income" lay-filter="income_carousel">
                        <div carousel-item>
                            <div id="income1"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">用户详情</div>
                <div class="layui-card-body">
                    <div style="width: 100%; height: 300px;" id="user">
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

        //建造轮播实例
        carousel.render({
            elem: '#income'
            , width: '100%' //设置容器宽度
            , height: '300px'
            , arrow: 'none' //不显示箭头
            , indicator: 'outside'//lay-indicator:outside
            , autoplay: false//自动切换
            , trigger: 'hover'//悬浮切换
        });
        //监听轮播切换事件
        carousel.on('change(income_carousel)', function (obj) { //Notice_carousel 来源于对应HTML容器的 lay-filter="Notice_carousel" 属性值
            obj.index === 0 ? income2.resize() : income1.resize();
        });


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
                        useRender(data);
                        incomeRender1(data);
                        incomeRender2(data);
                    },
                    error: function () {
                        layer.msg("当前连接数偏多，请稍后重试！");
                    }
                }
            );


        });


        //订单柱形图渲染
        function ordeRender(data) {
            console.log("执行了语句");
            //只能用原生
            var myorderChart = echarts.init(document.getElementById('order'));
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
                    data: data.data.orderMap.xAxis
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
                        data: data.data.orderMap.value,//处理小数点数据
                        itemStyle: {
                            normal: {//柱子颜色
                                color: '#4ad2ff'
                            }
                        }
                    }
                ]
            };
            console.log("打印下填充数据" + orderOption);
            //填充数据
            myorderChart.setOption(orderOption);
        }


        // 用户线性图渲染
        function useRender(data) {
            var myuserChart = echarts.init(document.getElementById('user'));
            var userOption = {
                title: {
                    text: '用户详情线性图'
                },
                tooltip: {
                    trigger: 'axis'//悬浮显示对比
                },
                legend: {//顶部显示 与series中的数据类型的name一致
                    data: ['用户数']
                },
                toolbox: {
                    feature: {
                        saveAsImage: {}//保存图片下载
                    }
                },
                xAxis: {
                    type: 'category',
                    boundaryGap: false,//起点开始
                    data: data.data.userMap.xAxis
                },
                yAxis: {
                    type: 'value'
                },
                series: [{
                    name: '用户数',
                    smooth: true,//曲线
                    type: 'line',//线性
                    areaStyle: {
                        color: ['rgba(70,220,230,.8)']
                    },//区域颜色
                    lineStyle: {//线条颜色
                        color: '#00FF00'
                    }, itemStyle: {
                        normal: {//折点颜色
                            color: '#000'
                        }
                    },
                    data: data.data.userMap.value,
                }]
            };
            myuserChart.setOption(userOption);
        }


        //营业详情饼状图渲染
        function incomeRender1(data) {
            var myincomeChart1 = echarts.init(document.getElementById('income1'));
            var incomeOption1 = {
                title: {
                    text: '营业收入汇总',
                    x: 'center'//标题居中
                }, tooltip: {
                    trigger: 'item'//悬浮显示对比
                },
                legend: {
                    orient: 'vertical',//类型垂直,默认水平
                    left: 'left',//类型区分在左 默认居中
                    data: data.data.incomeMap.xAxis
                },
                series: [
                    {
                        type: 'pie',//饼状
                        radius: '60%',//圆的大小
                        center: ['50%', '50%'],//居中
                        data: [
                            {value: data.data.incomeMap.value[0], name: data.data.incomeMap.xAxis[0]},
                            {value: data.data.incomeMap.value[1], name: data.data.incomeMap.xAxis[1]},
                            {value: data.data.incomeMap.value[2], name: data.data.incomeMap.xAxis[2]},
                            {value: data.data.incomeMap.value[3], name: data.data.incomeMap.xAxis[3]},
                            {value: data.data.incomeMap.value[4], name: data.data.incomeMap.xAxis[4]},
                            {value: data.data.incomeMap.value[5], name: data.data.incomeMap.xAxis[5]},
                            {value: data.data.incomeMap.value[6], name: data.data.incomeMap.xAxis[6]},
                        ]
                    }
                ]
            }
            myincomeChart1.setOption(incomeOption1);
        }


        function incomeRender2(data) {
            var myincomeChart2 = echarts.init(document.getElementById('income2'));
            var incomeOption2 ={
                title: {
                    text: '营业收入汇总',
                    x: 'center'//标题居中
                }, tooltip: {
                    trigger: 'item'//悬浮显示对比
                },
                legend: {
                    orient: 'vertical',//类型垂直,默认水平
                    left: 'left',//类型区分在左 默认居中
                    data: data.data.incomeMap.xAxis
                },
                series: [
                    {
                        type: 'pie',//饼状
                        radius: '60%',//圆的大小
                        center: ['50%', '50%'],//居中
                        data: [
                            {value: data.data.incomeMap.value[0], name: data.data.incomeMap.xAxis[0]},
                            {value: data.data.incomeMap.value[1], name: data.data.incomeMap.xAxis[1]},
                            {value: data.data.incomeMap.value[2], name: data.data.incomeMap.xAxis[2]},
                            {value: data.data.incomeMap.value[3], name: data.data.incomeMap.xAxis[3]},
                            {value: data.data.incomeMap.value[4], name: data.data.incomeMap.xAxis[4]},
                            {value: data.data.incomeMap.value[5], name: data.data.incomeMap.xAxis[5]},
                            {value: data.data.incomeMap.value[6], name: data.data.incomeMap.xAxis[6]},
                        ]
                    }
                ]
            }
            myincomeChart2.setOption(incomeOption2);

        }
    })


</script>


</body>


</html>
