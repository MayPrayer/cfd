layui.use(['element', 'layer', 'form'], function () {
        var form = layui.form;
        var layer = layui.layer;
        form.on('submit(logincfd)', function (data) {
            var datas = data.field;
            // var action=data.form.action;
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
            return false;
        })
    }
)
