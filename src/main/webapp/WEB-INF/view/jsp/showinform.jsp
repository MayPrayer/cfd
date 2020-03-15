<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/3/14
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
</head>
<body>
<div style="padding: 20px; background-color: #F2F2F2;">
    <input type="hidden" name="path" value="${pageContext.request.contextPath}">
    <!--    自适应-->
    <div class="layui-row layui-col-space15">

        <c:forEach items="${informs}" var="inform">
            <div class="layui-col-md6">
                <div class="layui-card">
                    <input type="hidden" name="informid" value="${inform.id}">
                    <div class="layui-card-header"><span style="color:#FF4500">公告标题</span>：${inform.title} <span><i
                            class="layui-icon"
                            style="float: right;color:#FF4500" >&#xe640;</i> </span></div>
                    <div class="layui-card-body">
                            ${inform.content}
                    </div>
                </div>
            </div>
        </c:forEach>

    </div>

</div>
<script>
    layui.use(['jquery', 'layer'], function () {
        var $ = layui.$, layer = layui.layer;
        var path = $("input[name='path']").val();
        var delpath = path + '/informs/deloneinform';
        //删除按钮删除
        // function del(obj){
        //
        //
        // }
        $("i.layui-icon").on('click', function () {
            //获取id值
            var inputs = $(this).parent().parent().parent().find("input");
            var id = inputs[0].value;

            //    发送ajax请求
            $.ajax({
                type: 'post',
                data: {"id": id},
                url: delpath,
                success: function (data) {
                    if (data.code == 0) {
                        // 操作成功刷新页面
                        window.history.go(0);
                    } else {
                        layer.msg('操作失败!', {icon: 5, time: 1000});
                    }
                }, error: function (code) {
                    parent.layer.msg('操作失败!', {icon: 5, time: 1000});
                }
            });

        });


    })

</script>
</body>
</html>
