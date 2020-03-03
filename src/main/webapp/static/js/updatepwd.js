layui.use(['element', 'layer', 'form', 'jquery'], function () {
        var form = layui.form;
        var layer = layui.layer;
        //获取项目根目录
        var path = $("input[name='path']").val();
        // console.log("我获取到的路径为："+path);
        // var newPassword = $("input[name='newpassword']").val();
        // var configPassword = $("input[name='configpassword']").val();
        // console.log("新密码为：" + newPassword);
        // console.log("验证密码为：" + configPassword);
        // $("input[name='configpassword']").blur(function () {
        //         if (newPassword != configPassword) {
        //             layer.tips("新设密码与确认密码不一致", 'input[name=\'configpassword\']', {
        //                 tips: [2, '#f41616'], //设置tips方向和颜色 类型：Number/Array，默认：2 tips层的私有参数。支持上右下左四个方向，通过1-4进行方向设定。如tips: 3则表示在元素的下面出现。有时你还可能会定义一些颜色，可以设定tips: [1, '#c00']
        //                 tipsMore: false, //是否允许多个tips 类型：Boolean，默认：false 允许多个意味着不会销毁之前的tips层。通过tipsMore: true开启
        //                 time: 2000  //2秒后销毁，还有其他的基础参数可以设置。。。。这里就不添加了
        //             })
        //         } else {
        //             layer.tips("新设密码与确认密码一致", 'input[name=\'configpassword\']', {
        //                 tips: [2, '#11ae11'],
        //                 tipsMore: false,
        //                 time: 2000
        //             })
        //         }
        //
        //     }
        // )
        form.on('submit(submitpwd)', function (data) {
            var datas = data.field;
            var relpath = path + "/updateuserinfo/modifypwd";
            console.log(relpath);
            // var action=data.form.action;
            $.ajax({
                    //ajax的url请求不会在url显示
                    url: relpath,
                    data: datas,
                    type: "POST",
                    //不指定dataType 会自动获取 返回值（根据对应的类型）
                    success: function (result) {
                        alert("我被执行了！");
                        if (result.code == '0') {
                            //    提示修改密码成功
                            layer.alert(result.message, {
                                skin: 'layui-layer-molv' //样式类名
                                , closeBtn: 0
                            });
                        //清空表单
                            $("#modifypwd")[0].reset();

                        } else {
                            layer.msg(result.message);
                            //    提示用户名输入错误
                        }
                    },
                    error: function () {
                        layer.msg("当前连接数偏多，请稍后重试！");
                    }
                }
            );
            return false;
        });


        //密码表单校验
        form.verify(
            {
                password: function (value) {
                    if (!new RegExp('(?=.*[0-9])(?=.*[a-zA-Z]).{8,30}').test(value)) {
                        return '密码中必须包含字母、数字，至少8个字符，最多30个字符';
                    }
                },
                confirmPass: function (value) {
                    if ($('input[name=newpassword]').val() !== value)
                        return '两次密码输入不一致！';
                }


            }
        );


    }
)
