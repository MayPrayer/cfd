package com.hbnu.controller;

import com.github.pagehelper.PageInfo;
import com.hbnu.entity.Result;
import com.hbnu.entity.Roles;
import com.hbnu.entity.Shops;
import com.hbnu.entity.Users;
import com.hbnu.service.IQryService;
import com.hbnu.service.IUpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * ClassName: UserInfoOpController <br/>
 * Description: <br/> cfd/updateuserinfo/modifypwd
 * date: 2020/2/25 19:18<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/updateuserinfo")
public class UserInfoOpController {

    @Autowired
    private IUpdateService ius;

    @Autowired
    private IQryService iqs;


    @RequestMapping("/modifypwd")
    @ResponseBody
    public Result modifyPwd(HttpServletRequest request) {
        //获取账号信息和密码信息
        String account = request.getParameter("account");
        String oldpassword = request.getParameter("oldpassword");
        String newpassword = request.getParameter("newpassword");
        String configpassword = request.getParameter("configpassword");
        System.out.println("account:" + account + "\n" + "oldpassword:" + oldpassword + "\n" + "newpassword:" + newpassword + "\n" + "configpassword:" + configpassword);
//        首先调用服务查询是否存在，不存在则不执行更新操作
        if (StringUtils.isEmpty(account) && StringUtils.isEmpty(oldpassword) && StringUtils.isEmpty(configpassword)) {
            return Result.failed("填写数据有误！");
        }
        if (!newpassword.equals(configpassword)) {
            return Result.failed("确认密码输入错误！");
        }

//       否则查询
            Users user = null;
            user = iqs.selectByAccountAndPassword(account, oldpassword);
            if (user == null) {
                return Result.failed("用户名或原始密码输入错误！");
            }
            System.out.println("准备执行这个...");
            ius.updatePwdByAccountAndPassword(newpassword, account);
            System.out.println("执行完毕...");
            return Result.success();
        }



    @RequestMapping("/showpersoninfo")
    public String showPersonInfo(HttpSession session, Model model) {
//        获取Session 拿到User
        System.out.println("session对象为" + session);
        Users user = (Users) session.getAttribute("userinfo");
        System.out.println("user对象为" + user);
        if (user == null) {
            return "login";
        }
//        查询角色关系
        Roles roles = iqs.selectByUserid(user.getId());
        model.addAttribute("role", roles);
        model.addAttribute("user", user);
//        将model对象转到personinfo页面
        return "personinfo";
    }


    @RequestMapping("/showalluserinfo")
    @ResponseBody
    public Result showAllUserInfo(@RequestParam("pagenum") int pageNum, @RequestParam("pagesize") int pageSize) {
        System.out.println("开始页" + pageNum + "\n每页数据量" + pageSize);
        Result result = Result.success();
        PageInfo pageInfo = iqs.findAll(pageNum, pageSize);
        result.setCount((int) pageInfo.getTotal());
        result.setData(pageInfo);
        return result;
    }

    @RequestMapping("/selectlikeuser")
    @ResponseBody
    public Result selectLikeUser(@RequestParam("pagenum") int pageNum, @RequestParam("pagesize") int pageSize, @RequestParam("account") String account) {
        System.out.println("开始页" + pageNum + "\n每页数据量" + pageSize + "\t模糊查询词为：" + account);
        Result result = Result.success();
        PageInfo pageInfo = iqs.selectLikeUser(pageNum, pageSize, account);
        result.setCount((int) pageInfo.getTotal());
        result.setData(pageInfo);
        return result;
    }


    @RequestMapping("/showuser")
    public String showuser() {
        return "usermanage";
    }


    // 处理通过id 删除用户请求
    @RequestMapping("/deleteuser")
    public Result deleteUser(@RequestParam("id") int id) {
        Result result = Result.success();
//       调用服务删除用户
        ius.deleteUserById(id);
//  查看权限
        Roles roles = iqs.selectByUserid(id);
        if (roles.getName().equals("ROLE_SHOP")) {
            //删除商铺
            ius.delOneShop(id);
        }
//删除权限
        ius.delOneRole(id);
        return result;
    }


    //添加一位用户
    @RequestMapping("/adduser")
    @ResponseBody
    public Result addUser(@RequestParam("account") String account, @RequestParam("nickname") String nickname, @RequestParam("pwd") String pwd, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("birthday") String birthday,
                          @RequestParam("idcard") String idcard, @RequestParam("grant") String grant, @RequestParam("userimage") String userimage, @RequestParam("shopname") String shopname, @RequestParam("shopaddress") String shopaddress, @RequestParam("shopphone") String shopphone, @RequestParam("shopimage") String shopimage
            , @RequestParam("managername") String managername
    ) throws ParseException {


        Users user = new Users();
        user.setAccount(account);
        user.setNickname(nickname);
        user.setPwd(pwd);
        user.setAvatar(userimage);
        user.setName(name);
        user.setPhone(phone);
        user.setIdcard(idcard);
        System.out.println("生日为" + birthday);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        Date  birth = df.parse(birthday);

        user.setBirthday(birth);
        int mesgcode = ius.insertOneUser(user);
        //            查询用户id
        List<Users> users = iqs.selectByAccount(account);
        int userid = users.get(0).getId();

        //创建商铺用户
        if (grant.equals("shop")) {
//创建角色，绑定id 创建商铺
            ius.insertOneRole(userid, "ROLE_SHOP");
            ius.addOneShop(userid, shopname, shopimage, shopaddress, managername, shopphone);

        }

        if(grant.equals("admin")){
            //创建管理员
            ius.insertOneRole(userid, "ROLE_ADMIN");
        }

        if (mesgcode == 1) {
            Result result = Result.success();
            result.setMessage("添加用户成功！");
            return result;
        }
        return Result.failed("添加用户失败，请检查填写信息！");
    }

    //   编辑用户信息
    @RequestMapping("/updateuser")
    @ResponseBody
    public Result editUser(@RequestParam("userid") int userid,@RequestParam("modifaccount") String account, @RequestParam("modifnickname") String nickname, @RequestParam("modifpwd") String pwd, @RequestParam("modifname") String name, @RequestParam("modifphone") String phone, @RequestParam("modifbirthday") String birthday,
                           @RequestParam("modifidcard") String idcard, @RequestParam("grant") String grant, @RequestParam("modifuserimage") String userimage, @RequestParam("modifshopname") String shopname, @RequestParam("modifshopaddress") String shopaddress, @RequestParam("modifshopphone") String shopphone, @RequestParam("modifshopimage") String shopimage
            , @RequestParam("modifmanagername") String managername) throws ParseException {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date  birth = df.parse(birthday);
//首先查询当前权限
       Roles role=  iqs.selectByUserid(userid);
       //普通用户
       if (role==null){
           if (grant.equals("modifuser")){
               //只修改用户
               ius.updateOneUser(account, pwd,name, birth,idcard,userimage, phone, nickname,userid);
           }else if (grant.equals("modifadmin")){
               //增加一个管理角色
               ius.insertOneRole(userid,"ROLE_ADMIN");
           }else{
               //增加一个商铺角色
               ius.insertOneRole(userid,"ROLE_ADMIN");
               //创建一个商铺
               ius.addOneShop(userid,shopname,shopimage,shopaddress,managername,shopphone);
           }
       }else {
           //商铺用户
           if (role.getName().equals("ROLE_SHOP")) {
               if (grant.equals("modifuser")) {
                   //清除商铺 ，商铺角色
                   ius.delOneShop(userid);
                   ius.delOneRole(userid);
               } else if (grant.equals("modifadmin")) {
                   //清除商铺 ，将角色修改为管理
                   ius.delOneShop(userid);
                   ius.updateOneRole("ROLE_ADMIN", userid);
               } else {
                   //修改用戶，修改商铺信息
                   ius.updateOneUser(account, pwd,name, birth,idcard,userimage, phone, nickname,userid);
                   ius.updateOneShop(shopname, shopimage, shopaddress, managername, shopphone,userid);
               }
           }

           if (role.getName().equals("ROLE_ADMIN")) {
               if (grant.equals("modifuser")) {
                   //删除管理角色
                   ius.delOneRole(userid);
               } else if (grant.equals("modifadmin")) {
                   //修改用户信息
                   ius.updateOneUser(account, pwd, name, birth, idcard, userimage, phone, nickname, userid);
               } else {
                   //修改角色为商铺，并且创建一个商铺
                   ius.updateOneRole("ROLE_SHOP", userid);
                   ius.addOneShop(userid, shopname, shopimage, shopaddress, managername, shopphone);
               }
           }

       }
        Result result = Result.success();
        result.setMessage("更新用户成功！");
        return result;
    }

//获取其他编辑显示用户信息
    @RequestMapping("/extrainfo")
    @ResponseBody
    public Result getExtraInfo(@RequestParam("userid") int userid) {
//        获取用户信息
        List<Users> users = iqs.selectInfoByUserid(userid);
        Users user = users.get(0);
//        查询角色s信息
        Roles role=null;
        try {
            role = iqs.selectByUserid(userid);
        }catch ( Exception e){
            return Result.failed("查询失败");
        }
        Map<String,Object> map = new HashMap();
        map.put("userinfo",user);
        if (role==null){
            map.put("user",true);
        }else{
            if (role.getName().equals("ROLE_SHOP")){
                Shops shop =  iqs.selectShopinfoByUserid(userid);
                map.put("shopinfo",shop);
            }
            if (role.getName().equals("ROLE_ADMIN")){
                map.put("admin",true);
            }
        }
        Result result = Result.success();
        result.setMessage("显示额外信息！");
        result.setData(map);
        return result;
    }



}
