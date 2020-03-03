layui.use('table', function () {
    var table = layui.table;
    var path = $("input[name='path']").val();
    var realpath=path+"/updateuserinfo/showalluserinfo";
    var delpath = path+"/updateuserinfo/deleteuser"
    //第一个实例
    var dataTable = table.render({
        elem: '#demo'
        , url: realpath //数据接口
        , method: 'post'
        , page: true //开启分页
        , limit: 10
        , limits: [10, 15]
        , cols: [[
            { width: 80, type: 'checkbox' },
            { field: 'id', width: 80, title: 'ID', sort: true },
            { field: 'name', title: '名字', sort: true },
            { field: 'account', title: '账户', sort: true },
            { field: 'phone', title: '手机号', sort: true },
            { field: 'birthday', title: '生日', sort: true },
            { field: 'nickname', title: '昵称', sort: true },
            //操作栏
            {fixed: 'right',title: '操作', align:'center', toolbar: '#barDemo'}
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


    //监听table容器
    table.on('tool(test)', function(obj) { //注：tool是工具条事件名，test是table原始容器的属性 lay-filter="对应的值"
        var data = obj.data ,//获得当前行数据
            layEvent = obj.event; //获得 lay-event 对应的值
        console.log("看下当前行数据的内容"+data);
        if(layEvent === 'detail') {
            layer.msg('查看操作');
        } else if(layEvent === 'del') {
            layer.confirm('真的要删除么？', function(index) {
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


    //删除记录


    function delCarouselById(id) {
        $.post(delpath,{"id":id},
            function(data, status) {
                layer.msg('删除成功');
            });
    }




});
