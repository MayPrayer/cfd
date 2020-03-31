package com.hbnu.controller;

import com.github.pagehelper.PageInfo;
import com.hbnu.entity.Result;
import com.hbnu.entity.Roles;
import com.hbnu.entity.Users;
import com.hbnu.service.IQryService;
import com.hbnu.service.IUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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
        try {
            user = iqs.selectByAccountAndPassword(account, oldpassword);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            if (user == null) {
                return Result.failed("用户名或原始密码输入错误！");
            }
            System.out.println("准备执行这个...");
            ius.updatePwdByAccountAndPassword(newpassword, account);
            System.out.println("执行完毕...");
            return Result.success();
        }

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
        return result;
    }


    //添加一位用户
    @RequestMapping("/adduser")
    @ResponseBody
    public Result addUser(@RequestParam("account") String account, @RequestParam("nickname") String nickname, @RequestParam("pwd") String pwd, @RequestParam("avatar") String avatar, @RequestParam("name") String name, @RequestParam("phone") String phone, @RequestParam("birthday") String birthday,
                          @RequestParam("idcard") String idcard
    ) {
        Users user = new Users();
        user.setAccount(account);
        user.setNickname(nickname);
        user.setPwd(pwd);
        user.setAvatar(avatar);
        user.setName(name);
        user.setPhone(phone);
        user.setIdcard(idcard);
        System.out.println("生日为"+birthday);
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date birth=null;
        try {
            birth =  df.parse(birthday);
        }catch(Exception e){

        }
        user.setBirthday(birth);
        int mesgcode = ius.insertOneUser(user);
        if(mesgcode==1){
           Result result =  Result.success();
           result.setMessage("添加用户成功！");
           return  result;
        }
        return Result.failed("添加用户失败，请检查填写信息！");
    }

//   编辑用户信息
    @RequestMapping("/updateuser")
    @ResponseBody
    public Result editUser(@RequestParam("modifname")String name ,@RequestParam("modifphone")String phone,@RequestParam("modifnickname")String nickname,@RequestParam("modifid")int id){
        ius.updateOneUser(name,phone,nickname,id);
            Result result =  Result.success();
            result.setMessage("更新用户成功！");
            return  result;
    }

}
