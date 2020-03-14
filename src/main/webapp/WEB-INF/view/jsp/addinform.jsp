<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/3/14
  Time: 13:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
</head>
<body>
<div id="notice"
     style="margin-left: -200px;margin-top:20px; border-radius: 20px; width:900px;box-shadow: 0px 0 13px rgba(100,100,100,.7);position:relative">
    <input type="hidden" name="path" value="${pageContext.request.contextPath}">
    <div style="position:absolute; right:0; top: 20px">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;margin-left: 100px">
            <legend><h4 style="color: #009688">公告通知新增</h4></legend>
        </fieldset>
        <form class="layui-form" method="post">
            <div class="layui-form-item">
                <label class="layui-form-label">公告栏标题</label>
                <div class="layui-input-inline">
                    <input type="text" name="title" placeholder="请输入公告标题" autocomplete="off" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label">优先级</label>
                <div class="layui-input-block" style="width: 190px">
                    <select name="important" lay-filter="aihao">
                        <option value=""></option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3" selected="">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                </div>
            </div>


            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">公告内容</label>
                <div class="layui-input-block" style="width: 500px">
                <textarea id="content" name="content" placeholder="请输入内容" class="fsLayedit"
                          height="80" lay-verify="content"></textarea>
                </div>
            </div>
            <!-- 提交按钮       -->
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="addInform" id="add">提交</button>
                    <button type="reset" class="layui-btn layui-btn-primary" id="todefault">重置</button>
                </div>
            </div>


        </form>
    </div>
</div>


<script>
    layui.use(['layedit', 'jquery', 'layer','form'], function () {
        var layedit = layui.layedit, $ = layui.jquery, layer = layui.layer ,form=layui.form;
        var path = $("input[name='path']").val();
        var addpath = path+"/informs/addoneinform";

      var index =  layedit.build('content', {
            tool: ['face', 'link', 'unlink', '|', 'left', 'center', 'right']
            , height: 150
        });

        /*
        * 重置清空编辑器内容
        * */
        $("#todefault").click(
            function () {
                alert("我执行了");
                layedit.build('content', {
                    tool: ['face', 'link', 'unlink', '|', 'left', 'center', 'right']
                    , height: 150
                });
                alert("已经清空了")
            }
        )


    /*
    * 监听提交
    * */
        form.on('submit(addInform)', function (data) {
            var datas = data.field;
            console.log(datas);

            $.ajax({
                    //ajax的url请求不会在url显示
                    url: addpath,
                    data: datas,
                    type: "POST",
                    //不指定dataType 会自动获取 返回值（根据对应的类型）
                    success: function (result) {
                        alert("我被执行了！");
                        if (result.code == '0') {
                            // 提示添加用户成功
                            layer.alert(result.message, {
                                    skin: 'layui-layer-molv' //样式类名
                                    , closeBtn: 0
                                }, function (index) {
                                    //点击关闭按钮后执行的函数
                                    layer.closeAll();   //关闭所有弹窗
                                    //重新加载页面
                                    window.location.reload();
                                }
                            );
                        } else {
                            layer.msg(result.message);
                            //    提示用户信息错误
                        }
                    },
                    error: function () {
                        layer.msg("当前连接数偏多，请稍后重试！");
                    }
                }
            );
            return false;
        });

        /*
        * 文本同步到表单
        * */
        form.verify({
            content: function(value) {
                return layedit.sync(index);
            }
        });



    })


</script>
</body>
</html>