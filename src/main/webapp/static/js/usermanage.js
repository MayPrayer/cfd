layui.use(['element', 'layer', 'form', 'jquery', 'table', 'laydate', 'util', 'upload'], function () {
    //赋值引用
    var element = layui.element, layer = layui.layer, form = layui.form, $ = layui.jquery, table = layui.table,
        laydate = layui.laydate, util = layui.util, upload = layui.upload;
    //获取项目根路径
    var path = $("input[name='path']").val();
    //获取json数据路径
    var realpath = path + "/updateuserinfo/showalluserinfo";
    var delpath = path + "/updateuserinfo/deleteuser";
    var likepath = path + "/updateuserinfo/selectlikeuser";
    var addpath = path + "/updateuserinfo/adduser";
    var vifpath = path + "/user/vifaccount";
    var updatepath = path + "/updateuserinfo/updateuser";
    var uploadpath = path + "/curgoodsinfo/upload";
    var extrainfopath=path+"/updateuserinfo/extrainfo"


    //没进行任何操作时的表格
    var dataTable = table.render({
        elem: '#demo'
        , url: realpath //数据接口
        , method: 'post'
        , page: true //开启分页
        , limit: 10
        , limits: [10, 15]
        , cols: [[
            {width: 80, type: 'checkbox'},
            {field: 'id', width: 80, title: 'ID', sort: true},
            {field: 'name', title: '名字', sort: true},
            {field: 'account', title: '账户', sort: true},
            {field: 'phone', title: '手机号', sort: true},
            {
                field: 'birthday', title: '生日', sort: true, templet: function (d) {
                    return util.toDateString(d.birthday, "yyyy-MM-dd")
                }
            },
            {field: 'nickname', title: '昵称', sort: true},
            //操作栏
            {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
        ]],
        parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
            return {
                "code": res.code, //解析接口状态
                "msg": res.message, //解析提示文本
                "count": res.count, //解析数据长度
                "data": res.data.list //解析数据列表
            };
        },
        request: {
            pageName: 'pagenum' // 页码的参数名称，默认：page
            , limitName: 'pagesize' //每页数据量的参数名，默认：limit
        },
    });

    //模糊查询   绑定搜索事件
    $("button[name='search']").on("click", search);

    //用户名模糊查询
    function search() {
        //获取搜索的值
        var inputVal = $('input[name="codeLike"]').val();
        alert("执行了")
        //重新加载表格
        table.render({
                elem: '#demo'
                , url: likepath //数据接口
                , method: 'post'
                , page: true //开启分页
                , limit: 10
                , limits: [10, 15]
                , cols: [[
                    {width: 80, type: 'checkbox'},
                    {field: 'id', width: 80, title: 'ID', sort: true},
                    {field: 'name', title: '名字', sort: true},
                    {field: 'account', title: '账户', sort: true},
                    {field: 'phone', title: '手机号', sort: true},
                    {
                        field: 'birthday',
                        title: '生日',
                        sort: true,
                        //将json中不同格式的数据转换成正常格式
                        templet: function (d) {
                            return util.toDateString(d.birthday, "yyyy-MM-dd")
                        }
                    },
                    {field: 'nickname', title: '昵称', sort: true},
                    //操作栏
                    {fixed: 'right', title: '操作', align: 'center', toolbar: '#barDemo'}
                ]],
                parseData: function (res) { //将原始数据解析成 table 组件所规定的数据
                    return {
                        "code": res.code, //解析接口状态
                        "msg": res.message, //解析提示文本
                        "count": res.count, //解析数据长度
                        "data": res.data.list //解析数据列表
                    };
                },
                request: {
                    pageName: 'pagenum' // 页码的参数名称，默认：page
                    , limitName: 'pagesize' //每页数据量的参数名，默认：limit
                },
                where: {
                    "account": inputVal
                }
            }
        )

    }


    //监听table容器
    table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data,//获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        console.log("看下当前行数据的内容" + data);
        if (layEvent === 'edit') {
            //实现编辑功能
            //填充表格数据
            $("input[name='modifaccount']").val(data.account);
            $("input[name='modifname']").val(data.name);
            $("input[name='modifphone']").val(data.phone);
            let d = new Date(data.birthday);
            let birth = (d.getFullYear() + "-" + (d.getMonth() + 1) + "-" + d.getDate());
            $("input[name='modifbirthday']").val(birth);
            $("input[name='modifnickname']").val(data.nickname);
            gainUserData(data.id);
            edituser();

        } else if (layEvent === 'del') {
            layer.confirm('真的要删除么？', function (index) {
                //obj.del(); //删除对应行（tr）的DOM结构
                delCarouselById(data.id);
                layer.close(index);
                layer.msg("删除成功", {icon: 6});

                //判断代码没写


                //刷新表格
                dataTable.reload({
                    elem: '#demo'
                })

                //向服务端发送删除指令
            });
        }
        /*else if(layEvent === 'edit'){
          layer.msg('编辑操作');
         }*/
    });

    //编辑用户
    function edituser() {
        alert("我被执行了");
        //发送ajax请求获取当前所有
        layer.open({
            type: 1,
            title: "编辑用户",
            closebtn: false,
            shift: 2,
            area: ['800px', '700px'],
            shadeclose: true,
            // btn: ['新增', '取消'],
            // btnalign: 'c',
            content: $("#edit-main"),
            success: function (layero, index) {
            },
            yes: function () {
            }
        });

    }


    //删除记录
    function delCarouselById(id) {
        $.post(delpath, {"id": id},
            function (data, status) {
                layer.msg('删除成功');
            });
    }


    laydate.render({
        elem: '#birthday', //指定元素
    });
    laydate.render({
        elem: '#modifbirthday', //指定元素
    });

    $("button[name='adduser']").on("click", onaddbtn);

    //添加用户页面
    function onaddbtn() {
        //页面层-自定义
        alert("我被执行了");
        layer.open({
            type: 1,
            title: "新增用户",
            closebtn: false,
            shift: 2,
            area: ['800px', '700px'],
            shadeclose: true,
            // btn: ['新增', '取消'],
            // btnalign: 'c',
            content: $("#add-main"),
            success: function (layero, index) {
            },
            yes: function () {
            }
        });
    }


// 添加用户表单验证
    form.verify(
        {
            password: function (value) {
                if (!new RegExp('(?=.*[0-9])(?=.*[a-zA-Z]).{8,30}').test(value)) {
                    return '密码中必须包含字母、数字，至少8个字符，最多30个字符';
                }
            },
            avatar: function (value) {
                if (!new RegExp('(https?:[^:<>"]*\\/)([^:<>"]*)(\\.((png!thumbnail)|(png)|(jpg)|(webp)))').test(value)) {
                    return '头像图片链接格式不正确';
                }
            },
            phone: function (value) {
                if (!new RegExp('^((0\\d{2,3}-\\d{7,8})|(1[3456789]\\d{9}))$').test(value)) {
                    return '手机号码格式不正确';
                }
            },
            idcard: function (value) {
                if (!new RegExp('^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$').test(value)) {
                    return '身份证号格式不正确';
                }
            },


        }
    );


//验证用户名是否存在
    $("input[name='account']").blur(function () {
            let account = $("input[name='account']").val();
            $.ajax({
                    //ajax的url请求不会在url显示
                    url: vifpath,
                    data: {account: account},
                    type: "POST",
                    //不指定dataType 会自动获取 返回值（根据对应的类型）
                    success: function (result) {
                        alert("我被执行了！");
                        if (result.code == '404') {
                            //    提示用户名输入错误

                            layer.tips("账户名已存在", 'input[name=\'account\']', {
                                tips: [2, '#f41616'], //设置tips方向和颜色 类型：Number/Array，默认：2 tips层的私有参数。支持上右下左四个方向，通过1-4进行方向设定。如tips: 3则表示在元素的下面出现。有时你还可能会定义一些颜色，可以设定tips: [1, '#c00']
                                tipsMore: false, //是否允许多个tips 类型：Boolean，默认：false 允许多个意味着不会销毁之前的tips层。通过tipsMore: true开启
                                time: 2000  //2秒后销毁，还有其他的基础参数可以设置。。。。这里就不添加了
                            });
                            //    将提交按钮变黑
                            $("#datatran").attr('disabled', true);
                            $("#datatran").css({'background-color': '#808080'});


                        } else {
                            //    提示用户名合法成功
                            layer.tips("账户名合法", 'input[name=\'account\']', {
                                tips: [2, '#11ae11'],
                                tipsMore: false,
                                time: 2000
                            });
                            $("#datatran").attr('disabled', false);
                            $("#datatran").css({'background-color': '#009688'});

                        }
                    },
                    error: function () {
                        layer.msg("当前连接数偏多，请稍后重试！");
                    }
                }
            );
        }
    )

    //监听新增用户提交动作
    form.on('submit(save)', function (data) {
        var datas = data.field;
        console.log(datas);
        // var action=data.form.action;
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

    //监听更新按钮
    form.on('submit(update)', function (data) {
        var datas = data.field;
        console.log(datas);
        // var action=data.form.action;
        $.ajax({
                //ajax的url请求不会在url显示
                url: updatepath,
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
    * 监控新增用户单选框按钮 ,
    * */
    form.on('select(grant)', function (data) {
        alert("我执行了")
        if (data.value=="shop"){
            $("#shopinfo").show();
            $(".custom").attr("lay-verify", "required");
            $("input[name='shopphone']").attr("lay-verify", "required|phone");
            $(".custom").removeAttr("disabled");
            form.render();
        }else{
            $("#shopinfo").hide();
            //设置禁用,去除隐藏提交，防止提交
            $(".custom").attr("disabled", "disabled");
            $(".custom").attr("lay-verify", "");
            form.render();
        }
    });





    // 图片上传 并将路径(/upload/2020-03-07/b8682757-b5ce-453f-942d-798af03d3ae4.jpg)存入隐藏域
    var uploadInst1 = upload.render({
        elem: '#imgupload1'
        , url: uploadpath
        , accept: 'images'
        , size: 50000
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#demo1').attr('src', result);
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功 提示信息
            var demoText1 = $('#demoText1');
            demoText1.html('<span style="color: #4cae4c;">上传成功</span>');
            //获取隐藏域 ，赋值操作 ，控制台显示
            var fileupload1 = $("input[name='userimage']");
            fileupload1.attr("value", res.data.src);
            console.log(fileupload1.attr("value"));
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText1 = $('#demoText1');
            demoText1.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText1.find('.demo-reload').on('click', function () {
                uploadInst1.upload();
            });
        }
    });


    var uploadInst2 = upload.render({
        elem: '#imgupload2'
        , url: uploadpath
        , accept: 'images'
        , size: 50000
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#demo2').attr('src', result);
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功 提示信息
            var demoText2 = $('#demoText2');
            demoText2.html('<span style="color: #4cae4c;">上传成功</span>');
            //获取隐藏域 ，赋值操作 ，控制台显示
            var fileupload2 = $("input[name='shopimage']");
            fileupload2.attr("value", res.data.src);
            console.log(fileupload2.attr("value"));
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText2 = $('#demoText2');
            demoText2.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText2.find('.demo-reload').on('click', function () {
                uploadInst2.upload();
            });
        }
    });


    /*
    * ajax 获取编辑显示信息
    * */
    function gainUserData(userid) {
        let datas={userid};
        $("input[name='userid']").val(userid);
        $.ajax({
         //ajax的url请求不会在url显示
         url: extrainfopath,
         data: datas,
         type: "POST",
         //不指定dataType 会自动获取 返回值（根据对应的类型）
         success: function (result) {
             alert("我被执行了！");
             if (result.code == '0') {
                 $("input[name='modifpwd']").val(result.data.userinfo.pwd);
                 $("input[name='modifidcard']").val(result.data.userinfo.idcard);
                 $("input[name='modifuserimage']").val(result.data.userinfo.avatar)
                 $('#demo3').attr('src', result.data.userinfo.avatar);
                 console.log("result结果为"+result.data.shopinfo)
                 if ((typeof result.data.shopinfo)!="undefined"){
                     //选中用户商铺权限
                     $("option[value='modifshop']").prop("selected",true);
                     form.render();
                     $("input[name='modifshopname']").val(result.data.shopinfo.name);
                     $("input[name='modifshopaddress']").val(result.data.shopinfo.position);
                     $("input[name='modifmanagername']").val(result.data.shopinfo.managername);
                     $("input[name='modifshopphone']").val(result.data.shopinfo.managerphone);

                     $("input[name='modifshopimage']").val(result.data.shopinfo.logo);
                     $('#demo4').attr('src', result.data.shopinfo.logo);

                 }else{
                     if ((typeof result.data.admin)!="undefined"){
                         //选中管理员权限
                         $("option[value='modifadmin']").prop("selected",true);
                         $("#shopinfo2").hide();
                         //设置禁用,去除隐藏提交，防止提交
                         $(".custom").attr("disabled", "disabled");
                         $(".custom").attr("lay-verify", "");

                         form.render();
                     }else{
                         $("option[value='modifuser']").prop("selected",true);
                         $("#shopinfo2").hide();
                         //设置禁用,去除隐藏提交，防止提交
                         $(".custom").attr("disabled", "disabled");
                         $(".custom").attr("lay-verify", "");
                         form.render();
                     }

                 }

             } else {
                 layer.msg(result.message);
                 //    获取用户信息错误
             }
         },
         error: function () {
             layer.msg("当前连接数偏多，请稍后重试！");
         }
     }
 );
    }



    /*
    * 监听权限
    * */
    form.on('select(modifgrant)', function (data) {
        alert("我执行了")
        if (data.value=="modifshop"){
            $("#shopinfo2").show();
            $(".custom").attr("lay-verify", "required");
            $("input[name='modifshopphone']").attr("lay-verify", "required|phone");
            $(".custom").removeAttr("disabled");
            //清空数据
            $(".custom").val("");
            form.render();
        }else{
            $("#shopinfo2").hide();
            //设置禁用,去除隐藏提交，防止提交
            $(".custom").attr("disabled", "disabled");
            $(".custom").attr("lay-verify", "");
            //清空数据
            $(".custom").val("");
            form.render();
        }
    });



    /*
    *图片上传
    * */
    var uploadInst3 = upload.render({
        elem: '#modifimgupload3'
        , url: uploadpath
        , accept: 'images'
        , size: 50000
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#demo3').attr('src', result);
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功 提示信息
            var demoText3 = $('#demoText3');
            demoText3.html('<span style="color: #4cae4c;">上传成功</span>');
            //获取隐藏域 ，赋值操作 ，控制台显示
            var fileupload3 = $("input[name='modifuserimage']");
            fileupload3.attr("value", res.data.src);
            console.log(fileupload3.attr("value"));
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText3 = $('#demoText3');
            demoText3.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText3.find('.demo-reload').on('click', function () {
                uploadInst3.upload();
            });
        }
    });

    var uploadInst4 = upload.render({
        elem: '#modifimgupload4'
        , url: uploadpath
        , accept: 'images'
        , size: 50000
        , before: function (obj) {
            obj.preview(function (index, file, result) {
                $('#demo4').attr('src', result);
            });
        }
        , done: function (res) {
            //如果上传失败
            if (res.code > 0) {
                return layer.msg('上传失败');
            }
            //上传成功 提示信息
            var demoText4 = $('#demoText4');
            demoText4.html('<span style="color: #4cae4c;">上传成功</span>');
            //获取隐藏域 ，赋值操作 ，控制台显示
            var fileupload4 = $("input[name='modifshopimage']");
            fileupload4.attr("value", res.data.src);
            console.log(fileupload4.attr("value"));
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText4 = $('#demoText4');
            demoText4.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText4.find('.demo-reload').on('click', function () {
                uploadInst4.upload();
            });
        }
    });



    //验证用户名是否存在
    $("input[name='modifaccount']").blur(function () {
            let account = $("input[name='modifaccount']").val();
            $.ajax({
                    //ajax的url请求不会在url显示
                    url: vifpath,
                    data: {account: account},
                    type: "POST",
                    //不指定dataType 会自动获取 返回值（根据对应的类型）
                    success: function (result) {
                        alert("我被执行了！");
                        if (result.code == '404') {
                            //    提示用户名输入错误

                            layer.tips("账户名已存在", 'input[name=\'account\']', {
                                tips: [2, '#f41616'], //设置tips方向和颜色 类型：Number/Array，默认：2 tips层的私有参数。支持上右下左四个方向，通过1-4进行方向设定。如tips: 3则表示在元素的下面出现。有时你还可能会定义一些颜色，可以设定tips: [1, '#c00']
                                tipsMore: false, //是否允许多个tips 类型：Boolean，默认：false 允许多个意味着不会销毁之前的tips层。通过tipsMore: true开启
                                time: 2000  //2秒后销毁，还有其他的基础参数可以设置。。。。这里就不添加了
                            });
                            //    将提交按钮变黑
                            $("#update").attr('disabled', true);
                            $("#update").css({'background-color': '#808080'});


                        } else {
                            //    提示用户名合法成功
                            layer.tips("账户名合法", 'input[name=\'account\']', {
                                tips: [2, '#11ae11'],
                                tipsMore: false,
                                time: 2000
                            });
                            $("#update").attr('disabled', false);
                            $("#update").css({'background-color': '#009688'});

                        }
                    },
                    error: function () {
                        layer.msg("当前连接数偏多，请稍后重试！");
                    }
                }
            );
        }
    )




});
