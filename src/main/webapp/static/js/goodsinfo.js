layui.use(['element', 'layer', 'form', 'jquery', 'table', 'laydate', 'util'], function () {
    //赋值引用
    var element = layui.element, layer = layui.layer, form = layui.form, $ = layui.jquery, table = layui.table,
        laydate = layui.laydate, util = layui.util;
    //获取项目根路径
    var path = $("input[name='path']").val();
    //获取json数据路径
    var realpath = path + "/curgoodsinfo/showcurgoodsinfo";
    var delpath = path + "/curgoodsinfo/delgood";
    var delmutipath = path + "/curgoodsinfo/delgoods"
    var likepath = path + "/updateuserinfo/selectlikeuser";
    var addpath = path + "/updateuserinfo/adduser";
    var vifpath = path + "/user/vifaccount";
    var updatepath = path + "/updateuserinfo/updateuser";


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
                field: 'img', title: '图片', sort: true,
            },
            {field: 'price', title: '单价', sort: true, templet: '<div>￥:&nbsp;{{d.price}}&nbsp;元</div>'},
            {field: 'discountprice', title: '折扣价', sort: true},
            {field: 'sales', title: '销售量', sort: true},
            {field: 'inventory', title: '库存', sort: true},
            {
                field: 'goodsstatus', title: '商品状态', sort: true, templet: function (d) {
                    if (d.goodsstatus == "上架") {
                        return '<span style="color:#39D8B8;">' + d.goodsstatus + '</span>'
                    }
                    if (d.goodsstatus == "下架") {
                        return '<span style="color:#FF4500;">' + d.goodsstatus + '</span>'
                    } else {
                        return '<span style="color:#888888;">' + '无状态' + '</span>'
                    }
                }
            },
            {
                field: 'goodstype', title: '商品类型', sort: true, templet: function (d) {
                    if (d.goodstype == "生鲜") {
                        return '<span style="color:#39D8B8;">' + d.goodstype + '</span>'
                    }
                    if (d.goodstype == "水果") {
                        return '<span style="color:#FF4500;">' + d.goodstype + '</span>'
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
        },
        request: {
            pageName: 'pagenum' // 页码的参数名称，默认：page
            , limitName: 'pagesize' //每页数据量的参数名，默认：limit
        },
    });

//    批量删除
    $("button[name='delchogoods']").on('click', function () {
        //获取选中状态            
        var checkStatus = table.checkStatus('goods');
        //获取选中数量
        var selectCount = checkStatus.data.length;
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
                    }
                    layer.close(index);
                    layer.msg(data.msg);
                }, error: function (code) {
                    parent.layer.msg('操作失败!', {icon: 5, time: 1000});
                }
            });
        })
    });



})
