<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/3/6
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/goods.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/goodsinfo.js"></script>
</head>
<body>
<div id="add-main" style="display: none;">
    <form class="layui-form" id="add-form" method="post">
        <div class="layui-form-item">
            <%--           商品--%>
            <label class="layui-form-label" style="width: 100px">商铺ID</label>
            <div class="layui-input-inline">
                <input type="text" name="modifname" required style="width: 240px" lay-verify="required"
                       placeholder="请输入商铺ID"
                       autocomplete="off" class="layui-input" readonly="readonly">
            </div>
            <label class="layui-form-label" style="width: 100px">商 品 名</label>
            <div class="llayui-input-inline">
                <input type="text" name="modifphone" required style="width: 240px" lay-verify="required"
                       placeholder="请输入商品名"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">单价</label>
            <div class="layui-input-inline">
                <input type="text" name="modifname" required style="width: 240px" lay-verify="required"
                       placeholder="请输入商品单价"
                       autocomplete="off" class="layui-input" >
            </div>
            <label class="layui-form-label" style="width: 100px">折扣价</label>
            <div class="llayui-input-inline">
                <input type="text" name="modifphone" required style="width: 240px" lay-verify="required"
                       placeholder="请输入商品折扣价"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">销量</label>
            <div class="layui-input-inline">
                <input type="text" name="modifname" required style="width: 240px" lay-verify="required"
                       placeholder="请输入销售数量"
                       autocomplete="off" class="layui-input" >
            </div>
            <label class="layui-form-label" style="width: 100px">库存</label>
            <div class="llayui-input-inline">
                <input type="text" name="modifphone" required style="width: 240px" lay-verify="required"
                       placeholder="请输入库存数量"
                       autocomplete="off" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">商品分类</label>
            <div class="layui-input-inline">
                <input type="radio" name="goodstype" value="fruit" title="水果"  checked>
                <input type="radio" name="goodstype" value="fresh" title="生鲜" >
            </div>
            <label class="layui-form-label" style="width: 100px">商品状态</label>
            <div class="layui-input-inline">
                <input type="radio" name="goodsstatus" value="fruit" title="上架"  checked>
                <input type="radio" name="goodsstatus" value="fresh" title="下架" >
            </div>
<%--            <label class="layui-form-label" style="width: 100px">商品状态</label>--%>
<%--            <div class="llayui-input-inline">--%>
<%--                <label class="layui-form-label">选择框</label>--%>
<%--                    <select name="city" lay-verify="required">--%>
<%--                        <option value=""></option>--%>
<%--                        <option value="0">北京</option>--%>
<%--                        <option value="1">上海</option>--%>
<%--                        <option value="2">广州</option>--%>
<%--                        <option value="3">深圳</option>--%>
<%--                        <option value="4">杭州</option>--%>
<%--                    </select>--%>
<%--            </div>--%>
        </div>

                <%--隐藏标签 接受返回的图片路径值提交 --%>
        <input type="hidden" name="images" class="image">
<%-- 图片上传--%>
        <div class="layui-form-item">
            <label class="layui-form-label ">商品图片</label>
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="imgupload">上传图片</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo1" width="100px" height="100px" style="margin-left: 109px">
                    <p id="demoText" style="display: inline-block; margin-left: 109px"></p>
                </div>
            </div>
        </div>





        <div class="layui-form-item layui-form-text">
            <label class="layui-form-label">描述</label>
            <div class="layui-input-block">
                <textarea name="desc" placeholder="请输入商品描述" class="layui-textarea" style="width:600px"></textarea>
            </div>
        </div>




        <%--            操作按钮--%>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="update" id="datamodif">立即修改</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>


<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card" id="design">
                <div class="layui-card-header">
                    <div class="layui-row">
                        <div class="layui-col-md4">
                            <button class="layui-btn" name="addgood"><i class="layui-icon">&#xe654;</i>新增</button>
                            <button class="layui-btn" name="delchogoods"><i class="layui-icon">&#xe640;</i>批量删除</button>
                        </div>
                        <div class="layui-col-md4 layui-col-md-offset4">
                            <di class="layui-layout-right">
                                <div class="layui-input-inline">
                                    <input type="text" id="codeLike" name="codeLike" placeholder="请输入账户名"
                                           autocomplete="off" class="layui-input">
                                </div>
                                <button class="layui-btn" name="search">搜索</button>
                            </di>
                        </div>
                    </div>
                </div>
                <div class="layui-card-body">
                    <%--table 容器--%>
                    <table id="goods" lay-filter="test"></table>
                </div>
            </div>
        </div>
    </div>
</div>


<input type="hidden" name="path" value="${pageContext.request.contextPath}">


<%--操作栏  编辑与删除--%>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit" style="background-color: #9F9F9F"><i
            class="layui-icon">&#xe642;</i></a>
    <a class="layui-btn  layui-btn-xs" lay-event="del" style="background-color:red"><i
            class="layui-icon">&#xe640;</i></a>
</script>


</body>
</html>
