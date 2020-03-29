layui.use(['element', 'layer', 'form', 'jquery', 'table', 'laydate', 'util', 'layedit', 'upload'], function () {
    //赋值引用
    var element = layui.element, layer = layui.layer, form = layui.form, $ = layui.jquery, table = layui.table,
        laydate = layui.laydate, util = layui.util, layedit = layui.layedit, upload = layui.upload;
    //获取项目根路径
    var path = $("input[name='path']").val();
    //获取json数据路径
    var realpath = path + "/ordermanager/getcurorders";
    var delpath = path + "/ordermanager/deloneorder";
    var seemorepath = path + "/ordermanager/seemore";
    var selectlikepath = path + "/ordermanager/selctlike"


    //没进行任何操作时的表格
    var dataTable = table.render({
        elem: '#demo'
        , url: realpath //数据接口
        , method: 'post'
        , page: true //开启分页
        , limit: 10
        , limits: [10, 15]
        , cols: [[
            // {width: 80, type: 'checkbox'},
            {field: 'id', title: 'ID', sort: true},
            {field: 'userid', title: '用户编号', sort: true},
            {field: 'shipadrid', title: '联系编号', sort: true},
            {field: 'amount', title: '总价', width: 200, sort: true},
            {
                field: 'ordertime', title: '创建订单时间', sort: true,
            },
            {field: 'orderstate', title: '订单状态', sort: true},
            {field: 'message', title: '备注', sort: true},
            {field: 'deliverytime', title: '预计送达时间', sort: true},
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
    });


//    删除订单
    table.on('tool(test)', function (obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data,//获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        console.log("看下当前行数据的内容" + data);
        if (layEvent === 'seemore') {
            alert("执行了");
            //渲染表单

            //    获取信息
            let orderid = data.id
            let userid = data.userid;
            let shipadrid = data.shipadrid;
            let amount = data.amount;
            let message = data.message;
            let ordertime = data.ordertime;
            let orderstate = data.orderstate;
            console.log("订单创建时间" + ordertime);

            //    发送ajax 获取所需信息
            gainData(userid, shipadrid, orderid, amount, ordertime, orderstate, amount, message);

            seemore();
            //    填充数据
        } else if (layEvent === 'del') {
            layer.confirm('真的要删除么？', function (index) {
                //obj.del(); //删除对应行（tr）的DOM结构
                delCarouselById(data.id);
                layer.close(index);
                layer.msg("删除成功", {icon: 6});
                dataTable.reload({
                    elem: '#demo'
                })

                //向服务端发送删除指令
            });
        }
    });


    function seemore() {
        layer.open({
            type: 1,
            title: "查看详情",
            closebtn: false,
            shift: 2,
            area: ['800px', '700px'],
            shadeclose: true,
            // btn: ['新增', '取消'],
            // btnalign: 'c',
            content: $("#more"),
            success: function (layero, index) {
            },
            yes: function () {
            }
        });

    }


    function gainData(userid, shipadrid, orderid, amount, ordertime, orderstate, amount, message) {
        let data = {userid, shipadrid, orderid};
        $.ajax({
            type: 'post',
            data: data,
            url: seemorepath,
            success: function (data) {
                if (data.code == 0) {
                    //   订单信息
                    $("input[name='createuser']").val(data.data.userinfo[0].name);
                    $("input[name='orderid']").val(orderid);
                    $("input[name='ordertime']").val(ordertime);
                    $("input[name='userphone']").val(data.data.userinfo[0].phone);
                    $("input[name='orderstate']").val(orderstate);
                    //联系信息

                    $("input[name='touser']").val(data.data.addrinfo[0].name);
                    $("input[name='tophone']").val(data.data.addrinfo[0].phone);
                    $("input[name='address']").val(data.data.addrinfo[0].address + data.data.addrinfo[0].housenumber);
                    //遍历商品信息
                    let mesg = '';

                    for (good of data.data.goodsinfo) {
                        let mesginfo = "</p>" + good.goods.name + "------" + good.taste + "------" + good.goods.discountprice + "元------*" + good.quantity + "</p>";
                        mesg = mesg + mesginfo;
                        console.log("订单信息为" + mesginfo);
                    }
                    let totalmeg = "<p>" + "总价为---------------<span style='color: #FF5722;'>￥:" + amount + "元</span>" + "</p>";
                    //首先先清空元素 避免重复
                    $("div[name='goodsinfo']").empty();
                    let firstr = "<pre>名称    口味    价格    数量</pre>"
                    $(firstr + mesg + "</br>" + totalmeg).appendTo("div[name='goodsinfo']");

                    //备注信息
                    $("textarea[name='remark']").val(message);

                }

                // layer.close(index);
            }, error: function (code) {
                parent.layer.msg('操作失败!', {icon: 5, time: 1000});
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


// 渲染输入框 日历
    laydate.render({
        elem: '#enday', //指定元素
        type: 'datetime'
    });
    laydate.render({
        elem: '#startday', //指定元素
        type: 'datetime'
    });


//监听按钮
    $("button[name='search']").on("click", search);

    function search() {
        alert("执行了！")
        let startday = $("#startday").val();
        let enday = $("#enday").val();
        console.log("开始时间" + startday + "\n结束时间" + enday);
        if ((startday != '') && (startday != '')) {
            table.render({
                elem: '#demo'
                , url: selectlikepath //数据接口
                , method: 'post'
                , page: true //开启分页
                , limit: 10
                , limits: [10, 15]
                , cols: [[
                    // {width: 80, type: 'checkbox'},
                    {field: 'id', title: 'ID', sort: true},
                    {field: 'userid', title: '用户编号', sort: true},
                    {field: 'shipadrid', title: '联系编号', sort: true},
                    {field: 'amount', title: '总价', width: 200, sort: true},
                    {
                        field: 'ordertime', title: '创建订单时间', sort: true,
                    },
                    {field: 'orderstate', title: '订单状态', sort: true},
                    {field: 'message', title: '备注', sort: true},
                    {field: 'deliverytime', title: '预计送达时间', sort: true},
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
                }, where: {
                    "startday": startday,
                    "enday":enday
                }
            });
        } else {
            layer.msg('开始日期与结束日期不能为空!', {icon: 5, time: 1000});
        }

    }

});

