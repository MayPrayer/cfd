<%--
  Created by IntelliJ IDEA.
  User: MayPrayer
  Date: 2020/2/26
  Time: 11:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>个人信息展示</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/layui/css/layui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/usermanage.css">
    <script src="${pageContext.request.contextPath}/static/js/jquery-1.10.2.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/static/js/usermanage.js"></script>
</head>

<body>
<%--弹出表单 新增用户--%>
<div id="add-main" style="display: none;">
    <form class="layui-form" id="add-form" method="post">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top:3px;margin-left:3px">
            <legend><h5 style="color: #009688">用户信息</h5></legend>
        </fieldset>
        <%--     账户       --%>
        <div class="layui-form-item center">
            <label class="layui-form-label" style="width: 100px">账 户</label>
            <div class="layui-input-inline">
                <input type="text" name="account" required style="width: 240px" lay-verify="required"
                       placeholder="请输入登录账户名" autocomplete="off" class="layui-input">
            </div>

            <%--        昵称--%>
            <label class="layui-form-label" style="width: 100px">昵 称</label>
            <div class="layui-input-inline">
                <input type="text" name="nickname" required style="width: 240px" lay-verify="required"
                       placeholder="请输入昵称"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <%--            密码--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">密 码</label>
            <div class="layui-input-inline">
                <input type="text" name="pwd" required style="width: 240px" lay-verify="required|password"
                       placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
            <%--姓名--%>
            <label class="layui-form-label" style="width: 100px">名 字</label>
            <div class="layui-input-inline">
                <input type="text" name="name" required value="" style="width: 240px" lay-verify="required"
                       placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>

        </div>


        <%--           手机号--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">手 机 号</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" required style="width: 240px" lay-verify="required|phone"
                       placeholder="请输入手机号"
                       autocomplete="off" class="layui-input">
            </div>
            <%--         生日--%>
            <label class="layui-form-label" style="width: 100px">生 日</label>
            <div class="layui-input-inline">
                <input type="text" name="birthday" required style="width: 240px" lay-verify="required"
                       placeholder="请选择生日"
                       autocomplete="off" class="layui-input" id="birthday">
            </div>
        </div>


        <%--           身份证号--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">身份证号</label>
            <div class="layui-input-inline">
                <input type="text" name="idcard" required style="width: 240px" lay-verify="required|idcard"
                       placeholder="请输入身份证号"
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label" style="width: 100px">用户权限</label>
            <div class="layui-input-inline">
                <select name="grant" lay-verify="required" lay-filter="grant">
                    <option value="">请选择身份</option>
                    <option value="user">普通用户</option>
                    <option value="shop"selected="">商铺用户</option>
                    <option value="admin">管理员</option>
                </select>
            </div>
        </div>


        <%--隐藏标签 接受返回的图片路径值提交 --%>
        <input type="hidden" name="userimage" class="image">
        <%-- 图片上传--%>
        <div class="layui-form-item">
            <label class="layui-form-label " style="width: 100px">用户头像</label>
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="imgupload1">上传图片</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo1" width="100px" height="100px" style="margin-left: 126px">
                    <p id="demoText1" style="display: inline-block; margin-left: 109px"></p>
                </div>
            </div>
        </div>


        <%--商铺信息--%>


        <div id="shopinfo">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top:3px;margin-left:3px">
                <legend><h5 style="color: #009688">商铺信息</h5></legend>
            </fieldset>

            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px">商铺名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="shopname" required style="width: 240px" lay-verify="required"
                           placeholder="请输入商铺名称"
                           autocomplete="off" class="layui-input custom">
                </div>

                <label class="layui-form-label" style="width: 100px">商铺地址</label>
                <div class="layui-input-inline">
                    <input type="text" name="shopaddress" required style="width: 240px" lay-verify="required"
                           placeholder="请输入商铺地址"
                           autocomplete="off" class="layui-input custom">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px">经营者名字</label>
                <div class="layui-input-inline">
                    <input type="text" name="managername" required style="width: 240px" lay-verify="required"
                           placeholder="请输入经营者名字"
                           autocomplete="off" class="layui-input custom">
                </div>
                <label class="layui-form-label" style="width: 100px">联系电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="shopphone" required style="width: 240px" lay-verify="required|phone"
                           placeholder="请输入联系电话"
                           autocomplete="off" class="layui-input custom">
                </div>
            </div>

            <%--商铺图片--%>
            <input type="hidden" name="shopimage" class="image">
            <%-- 图片上传--%>
            <div class="layui-form-item">
                <label class="layui-form-label " STYLE="width: 100px">商铺图片</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="imgupload2">上传图片</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" id="demo2" width="100px" height="100px"
                             style="margin-left: 126px">
                        <p id="demoText2" style="display: inline-block; margin-left: 109px"></p>
                    </div>
                </div>
            </div>
        </div>

        <%--            操作按钮--%>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left:294px">
                <button class="layui-btn" lay-submit lay-filter="save" id="datatran">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" id="closebtn">重置</button>
            </div>
        </div>
    </form>
</div>





<%--弹出表单 更新用户--%>
<div id="edit-main" style="display: none;">
    <form class="layui-form" id="edit-form" method="post">
        <fieldset class="layui-elem-field layui-field-title" style="margin-top:3px;margin-left:3px">
            <legend><h5 style="color: #009688">用户信息</h5></legend>
        </fieldset>
        <%--     账户       --%>
        <input type="hidden" name="userid">
        <div class="layui-form-item center">
            <label class="layui-form-label" style="width: 100px">账 户</label>
            <div class="layui-input-inline">
                <input type="text" name="modifaccount" required style="width: 240px" lay-verify="required"
                       placeholder="请输入登录账户名" autocomplete="off" class="layui-input">
            </div>

            <%--        昵称--%>
            <label class="layui-form-label" style="width: 100px">昵 称</label>
            <div class="layui-input-inline">
                <input type="text" name="modifnickname" required style="width: 240px" lay-verify="required"
                       placeholder="请输入昵称"
                       autocomplete="off" class="layui-input">
            </div>
        </div>
        <%--            密码--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">密 码</label>
            <div class="layui-input-inline">
                <input type="text" name="modifpwd" required style="width: 240px" lay-verify="required|password"
                       placeholder="请输入密码"
                       autocomplete="off" class="layui-input">
            </div>
            <%--姓名--%>
            <label class="layui-form-label" style="width: 100px">名 字</label>
            <div class="layui-input-inline">
                <input type="text" name="modifname" required value="" style="width: 240px" lay-verify="required"
                       placeholder="请输入姓名" autocomplete="off" class="layui-input">
            </div>

        </div>


        <%--           手机号--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">手 机 号</label>
            <div class="layui-input-inline">
                <input type="text" name="modifphone" required style="width: 240px" lay-verify="required|phone"
                       placeholder="请输入手机号"
                       autocomplete="off" class="layui-input">
            </div>
            <%--         生日--%>
            <label class="layui-form-label" style="width: 100px">生 日</label>
            <div class="layui-input-inline">
                <input type="text" name="modifbirthday" required style="width: 240px" lay-verify="required"
                       placeholder="请选择生日"
                       autocomplete="off" class="layui-input" id="modifbirthday">
            </div>
        </div>


        <%--           身份证号--%>
        <div class="layui-form-item">
            <label class="layui-form-label" style="width: 100px">身份证号</label>
            <div class="layui-input-inline">
                <input type="text" name="modifidcard" required style="width: 240px" lay-verify="required|idcard"
                       placeholder="请输入身份证号"
                       autocomplete="off" class="layui-input">
            </div>
            <label class="layui-form-label" style="width: 100px">用户权限</label>
            <div class="layui-input-inline">
                <select name="grant" lay-verify="required" lay-filter="modifgrant">
                    <option value="">请选择身份</option>
                    <option value="modifuser" >普通用户</option>
                    <option value="modifshop" selected="">商铺用户</option>
                    <option value="modifadmin">管理员</option>
                </select>
            </div>
        </div>




        <%--隐藏标签 接受返回的图片路径值提交 --%>
        <input type="hidden" name="modifuserimage" class="image">
        <%-- 图片上传--%>
        <div class="layui-form-item">
            <label class="layui-form-label " style="width: 100px">用户头像</label>
            <div class="layui-upload">
                <button type="button" class="layui-btn" id="modifimgupload3">上传图片</button>
                <div class="layui-upload-list">
                    <img class="layui-upload-img" id="demo3" width="100px" height="100px" style="margin-left: 126px">
                    <p id="demoText3" style="display: inline-block; margin-left: 109px"></p>
                </div>
            </div>
        </div>


        <%--商铺信息--%>


        <div id="shopinfo2">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top:3px;margin-left:3px">
                <legend><h5 style="color: #009688">商铺信息</h5></legend>
            </fieldset>

            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px">商铺名称</label>
                <div class="layui-input-inline">
                    <input type="text" name="modifshopname" required style="width: 240px" lay-verify="required"
                           placeholder="请输入商铺名称"
                           autocomplete="off" class="layui-input custom">
                </div>

                <label class="layui-form-label" style="width: 100px">商铺地址</label>
                <div class="layui-input-inline">
                    <input type="text" name="modifshopaddress" required style="width: 240px" lay-verify="required"
                           placeholder="请输入商铺地址"
                           autocomplete="off" class="layui-input custom">
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label" style="width: 100px">经营者名字</label>
                <div class="layui-input-inline">
                    <input type="text" name="modifmanagername" required style="width: 240px" lay-verify="required"
                           placeholder="请输入经营者名字"
                           autocomplete="off" class="layui-input custom">
                </div>
                <label class="layui-form-label" style="width: 100px">联系电话</label>
                <div class="layui-input-inline">
                    <input type="text" name="modifshopphone" required style="width: 240px" lay-verify="required|phone"
                           placeholder="请输入联系电话"
                           autocomplete="off" class="layui-input custom">
                </div>
            </div>

            <%--商铺图片--%>
            <input type="hidden" name="modifshopimage" class="image">
            <%-- 图片上传--%>
            <div class="layui-form-item">
                <label class="layui-form-label " STYLE="width: 100px">商铺logo</label>
                <div class="layui-upload">
                    <button type="button" class="layui-btn" id="modifimgupload4">上传图片</button>
                    <div class="layui-upload-list">
                        <img class="layui-upload-img" id="demo4" width="100px" height="100px"
                             style="margin-left: 126px">
                        <p id="demoText4" style="display: inline-block; margin-left: 109px"></p>
                    </div>
                </div>
            </div>
        </div>

        <%--            操作按钮--%>
        <div class="layui-form-item">
            <div class="layui-input-block" style="margin-left:294px">
                <button class="layui-btn" lay-submit lay-filter="update" id="update">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary" id="closebtn2">重置</button>
            </div>
        </div>
    </form>
</div>







<%--直接显示内容--%>
<div class="layui-fluid">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card" id="design">
                <div class="layui-card-header">
                    <div class="layui-row">
                        <div class="layui-col-md4">
                            <button class="layui-btn" name="adduser"><i class="layui-icon">&#xe654;</i>新增</button>
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
                    <table id="demo" lay-filter="test"></table>
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

</html>