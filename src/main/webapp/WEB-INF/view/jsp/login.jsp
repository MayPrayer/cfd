<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/2/23
  Time: 7:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
</head>
<body>
<form class="layui-form" method="post">
    <div class="container" >

        <!--用户登录图片-->
        <div class="layui-form-mid layui-word-aux">
            <img id="logoid" src="${pageContext.request.contextPath}/static/img/login.jpg" height="35">
        </div>
        <!--   用户名框    -->
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <input type="text" name="account" required lay-verify="required" placeholder="请输入用户名" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <!--    密码框-->
        <div class="layui-form-item">
            <label class="layui-form-label">密 &nbsp;&nbsp;码</label>
            <div class="layui-input-inline">
                <input type="password" name="password" required lay-verify="required" placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
            <!--验证码框-->
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">滑动验证</label>
            <div class="layui-input-block">
                <div id="slider"></div>
            </div>
        </div>


        <!--登录按钮及提示-->
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="logincfd">登陆</button>
            </div>
        </div>
    </div>
</form>
<script >

    layui.config({
        version: 1,
        base: '${pageContext.request.contextPath}/static/layui/sliderVerify/'
    }).use(['element', 'layer', 'form','sliderVerify'], function () {
            var form = layui.form;
            var layer = layui.layer;
            var sliderVerify = layui.sliderVerify;

//渲染滑动验证
            var slider = sliderVerify.render({
                elem: '#slider',
                max:'70',

            });

            form.on('submit(logincfd)', function (data) {
                var datas = data.field;
                if(slider.isOk()){//用于表单验证是否已经滑动成功
                    alert("滑动执行了")
                    $.ajax({
                            //ajax的url请求不会在url显示
                            url: "verify",
                            data: datas,
                            type: "POST",
                            //不指定dataType 会自动获取 返回值（根据对应的类型）
                            success: function (result) {
                                alert("我被执行了");
                                if (result.code=='0') {
                                    //   跳转页面
                                    window.location.href = "index"
                                } else {
                                    layer.msg(result.message);
                                    //    提示密码或用户名错误
                                }
                                // window.location.href="main";
                            },
                            error: function () {
                                layer.msg("当前连接数偏多，请稍后重试！");
                            }
                        }
                    );
                }else{
                    layer.msg("请先通过滑块验证");
                }

                var datas = data.field;

                return false;
            })
        }
    )


</script>

</body>
</html>
