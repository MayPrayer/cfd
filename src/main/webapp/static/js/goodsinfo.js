layui.use(['element', 'layer', 'form', 'jquery', 'table', 'laydate', 'util', 'layedit', 'upload'], function () {
    //赋值引用
    var element = layui.element, layer = layui.layer, form = layui.form, $ = layui.jquery, table = layui.table,
        laydate = layui.laydate, util = layui.util, layedit = layui.layedit, upload = layui.upload;
    //获取项目根路径
    var path = $("input[name='path']").val();
    //获取json数据路径
    var realpath = path + "/curgoodsinfo/showcurgoodsinfo";
    var delpath = path + "/curgoodsinfo/delgood";
    var delmutipath = path + "/curgoodsinfo/delgoods"
    var likepath = path + "/curgoodsinfo/selectlikegoods";
    var addpath = path + "/curgoodsinfo/addgood";
    var editpath = path + "/curgoodsinfo/editgood";
    var vifpath = path + "/curgoodsinfo/vifgood";
    var updatepath = path + "/curgoodsinfo/updategood";
    var uploadpath = path + "/curgoodsinfo/upload";


    //没进行任何操作时的表格
    var dataTable = table.render({
            elem: '#goods'
            , url: realpath //数据接口
            , method: 'post'
            , page: true //开启分页
            , limit: 10
            , limits: [10, 15]
            , cols: [[
                {width: 80, type: 'checkbox'},
                {field: 'id', width: 80, title: 'ID', sort: true},
                {field: 'shopid', title: '商铺ID', sort: true},
                {field: 'name', title: '商品名', sort: true},
                {field: 'describes', title: '描述', sort: true},
                {
                    field: 'img', title: '图片', width: 150, sort: true, templet: function (d) {
                        return '<div><img   src=' + d.img + '></div>'
                        // return '<div><img   src=' + '/file'+d.img + '></div>'
                    }
                },
                {field: 'price', title: '单价', sort: true, templet: '<div>￥&nbsp;{{d.price}}&nbsp;元</div>'},
                {
                    field: 'discountprice',
                    title: '折扣价',
                    sort: true,
                    // templet: '<div style="color:#FF4500;font-weight: bold>￥&nbsp;{{d.price}}&nbsp;元</div>'
                    templet: '<div>￥&nbsp;{{d.discountprice}}&nbsp;元</div>'

                },
                {field: 'sales', title: '销售量', sort: true},
                {field: 'inventory', title: '库存', sort: true},
                {
                    field: 'goodsstatus', title: '商品状态', sort: true, templet: function (d) {
                        if (d.goodsstatus == "上架") {
                            return '<span style="color:#39D8B8;font-weight: bold">' + d.goodsstatus + '</span>'
                        }
                        if (d.goodsstatus == "下架") {
                            return '<span style="color:#FF4500;font-weight: bold">' + d.goodsstatus + '</span>'
                        } else {
                            return '<span style="color:#888888;font-weight: bold">' + '无状态' + '</span>'
                        }
                    }
                },
                {
                    field: 'goodstype', title: '商品类型', sort: true, templet: function (d) {
                        if (d.goodstype == "生鲜") {
                            return '<span style="color:#FFFF00;">' + d.goodstype + '</span>'
                        }
                        if (d.goodstype == "水果") {
                            return '<span style="color:#5CBB44;">' + d.goodstype + '</span>'
                        } else {
                            return '<span style="color:#888888;">' + '无状态' + '</span>'
                        }
                    }
                },
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
            }
            ,
            request: {
                pageName: 'pagenum' // 页码的参数名称，默认：page
                , limitName: 'pagesize' //每页数据量的参数名，默认：limit
            }
            ,
        })
    ;


//    批量删除
    $("button[name='delchogoods']").on('click', function () {
        //获取选中状态            
        var checkStatus = table.checkStatus('goods');
        //获取选中数量
        var selectCount = checkStatus.data.length;
        console.log("选中数量为" + selectCount)
        if (selectCount == 0) {
            layer.msg('批量删除至少选中一项数据', function () {
            });
            return false;
        }
        layer.confirm('真的要删除选中的项吗？', function (index) {
            layer.close(index);
            index = layer.load(1, {shade: [0.1, '#fff']});

            var isStr = "";
            for (var i = 0; i < selectCount; i++) {
                isStr = isStr + "," + checkStatus.data[i].id;
            }
            $.ajax({
                type: 'post',
                data: {"isStr": isStr},
                url: delmutipath,
                success: function (data) {
                    if (data.code == 0) {
                        //
                        alert("删除成功")
                        //  提示信息删除成功刷新页面
                        layer.msg("删除成功", {icon: 6});
                        dataTable.reload({
                            elem: '#goods'
                        })
                    }
                    layer.close(index);
                }, error: function (code) {
                    parent.layer.msg('操作失败!', {icon: 5, time: 1000});
                }
            });
        })
    });


//单个删除，以及编辑
    table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data,//获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        console.log("看下当前行数据的内容" + data);
        if (layEvent === 'edit') {
            //实现编辑功能
            //填充表格数据
            $("input[name='modifname']").val(data.name);
            $("input[name='modifprice']").val(data.price);
            $("input[name='modifdiscountprice']").val(data.discountprice);
            $("input[name='modifsales']").val(data.sales);
            $("input[name='modifinventory']").val(data.inventory);

            //选中状态
            checkChang(data);
            editgoods();
            //图片预览，以及描述
            $("input[name='modifimage']").val(data.img);
            $("#demo2").attr("src", data.img)
            console.log(data.describes);
            $("textarea[name='modifdescribes']").val(data.describes);
            $("input[name='id']").val(data.id);

        } else if (layEvent === 'del') {
            layer.confirm('真的要删除么？', function (index) {
                $.post(delpath, {"id": data.id});
                layer.close(index);
                layer.msg("删除成功", {icon: 6});
                //判断代码没写
                //刷新表格
                dataTable.reload({
                    elem: '#goods'
                })
                //向服务端发送删除指令
            });
        }
    });

//编辑商品
    function editgoods() {
        alert("我被执行了");
        //发送ajax请求获取当前所有
        layer.open({
            type: 1,
            title: "编辑商品",
            closebtn: false,
            shift: 2,
            area: ['800px', '600px'],
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

//    选中框change
    function checkChang(data) {
        var goodstype = data.goodstype;
        var goodsstatus = data.goodsstatus;
        $("input[name='modifgoodstype']").each(function () {
            if (this.value == goodstype) {
                this.checked = true;
            }
            form.render();
        })
        $("input[name='modifgoodsstatus']").each(function () {
            if (this.value == goodsstatus) {
                this.checked = true;
            }
            form.render();
        })
    }


//    新增商品 (绑定事件，弹出页面)
    $("button[name='addgood']").on("click", onaddbtn);

//添加商品页面
    function onaddbtn() {
        //页面层-自定义
        alert("我被执行了");
        layer.open({
            type: 1,
            title: "新增商品",
            closebtn: false,
            shift: 2,
            area: ['800px', '600px'],
            shadeclose: true,
            // btn: ['新增', '取消'],
            // btnalign: 'c',
            content: $("#add-main"),
            success: function () {
                form.render();
            },
            yes: function () {
            }
        });
    }


// 图片上传 并将路径(/upload/2020-03-07/b8682757-b5ce-453f-942d-798af03d3ae4.jpg)存入隐藏域
    var uploadInst = upload.render({
        elem: '#imgupload'
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
            var demoText = $('#demoText');
            demoText.html('<span style="color: #4cae4c;">上传成功</span>');
            //获取隐藏域 ，赋值操作 ，控制台显示
            var fileupload = $(".image");
            fileupload.attr("value", res.data.src);
            console.log(fileupload.attr("value"));
        }
        , error: function () {
            //演示失败状态，并实现重传
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function () {
                uploadInst.upload();
            });
        }
    });

    // 编辑商品，图片修改
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
            var fileupload = $(".image");
            fileupload.attr("value", res.data.src);
            console.log(fileupload.attr("value"));
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
    * 监听新增商品表单
    * */
    form.on('submit(add)', function (data) {
        var datas = data.field;
        alert("提交成功");
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
    //监听编辑表单
    form.on('submit(edit)', function (data) {
        var datas = data.field;
        alert("提交成功");
        console.log(datas);
        // var action=data.form.action;
        $.ajax({
                //ajax的url请求不会在url显示
                url: editpath,
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


    /**
     * 表单验证规则
     *
     * */
    form.verify(
        {


            /*0-999999.99区间*/
            price: function (value) {
                if (!new RegExp('^\\d{1,6}(\\.\\d{1,2})?$').test(value)) {
                    return '价格格式不正确，请输入正确的价格';
                }

            },
            /* 0-200个*/
            nums: [
                /^[1-9]\d*$/
                , '只能是正整数哦'
            ]
        }
    );


    /*
    * 模糊查询
    * */


    $("button[name='search']").on("click", search);

    //用户名模糊查询
    function search() {
        //获取搜索的值
        var inputVal = $('input[name="codeLike"]').val();
        alert("执行了")
        //重新加载表格
        table.render({
                elem: '#goods'
                , url: likepath //数据接口
                , method: 'post'
                , page: true //开启分页
                , limit: 10
                , limits: [10, 15]
                , cols: [[
                    {width: 80, type: 'checkbox'},
                    {field: 'id', width: 80, title: 'ID', sort: true},
                    {field: 'shopid', title: '商铺ID', sort: true},
                    {field: 'name', title: '商品名', sort: true},
                    {field: 'describes', title: '描述', sort: true},
                    {
                        field: 'img', title: '图片', width: 150, sort: true, templet: function (d) {
                            return '<div><img   src=' + d.img + '></div>'
                            // return '<div><img   src=' + '/file'+d.img + '></div>'
                        }
                    },
                    {field: 'price', title: '单价', sort: true, templet: '<div>￥&nbsp;{{d.price}}&nbsp;元</div>'},
                    {
                        field: 'discountprice',
                        title: '折扣价',
                        sort: true,
                        // templet: '<div style="color:#FF4500;font-weight: bold>￥&nbsp;{{d.price}}&nbsp;元</div>'
                        templet: '<div>￥&nbsp;{{d.discountprice}}&nbsp;元</div>'

                    },
                    {field: 'sales', title: '销售量', sort: true},
                    {field: 'inventory', title: '库存', sort: true},
                    {
                        field: 'goodsstatus', title: '商品状态', sort: true, templet: function (d) {
                            if (d.goodsstatus == "上架") {
                                return '<span style="color:#39D8B8;font-weight: bold">' + d.goodsstatus + '</span>'
                            }
                            if (d.goodsstatus == "下架") {
                                return '<span style="color:#FF4500;font-weight: bold">' + d.goodsstatus + '</span>'
                            } else {
                                return '<span style="color:#888888;font-weight: bold">' + '无状态' + '</span>'
                            }
                        }
                    },
                    {
                        field: 'goodstype', title: '商品类型', sort: true, templet: function (d) {
                            if (d.goodstype == "生鲜") {
                                return '<span style="color:#FFFF00;">' + d.goodstype + '</span>'
                            }
                            if (d.goodstype == "水果") {
                                return '<span style="color:#5CBB44;">' + d.goodstype + '</span>'
                            } else {
                                return '<span style="color:#888888;">' + '无状态' + '</span>'
                            }
                        }
                    },
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
                }
                ,
                request: {
                    pageName: 'pagenum' // 页码的参数名称，默认：page
                    , limitName: 'pagesize' //每页数据量的参数名，默认：limit
                },
                where: {
                    "name": inputVal
                }
            }
        )

    }

})
