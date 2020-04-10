package com.hbnu.controller;

import com.hbnu.entity.Result;
import com.hbnu.entity.Roles;
import com.hbnu.entity.Users;
import com.hbnu.service.IQryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.management.relation.Role;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * ClassName: DisPlay <br/>
 * Description: <br/>
 * date: 2020/2/21 12:04<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */

@Controller
@RequestMapping("/user")
public class UserLoginController {
    @Autowired
    private IQryService iqs;


    //   跳转到管理界面
    @RequestMapping("/index")
    public String index(ModelMap model, HttpSession session, HttpServletRequest request) {
        Users user = (Users) session.getAttribute("userinfo");

//  查询角色 ，根据userid查询角色
        Roles role = null;

            role = iqs.selectByUserid(user.getId());
        if (role != null) {
            if (role.getName().equals("ROLE_ADMIN")) {
                request.getSession().setAttribute("admin", role);
            }
        }
        model.addAttribute("user", user);
        return "admin";
    }


    //  跳转到登录页
    @RequestMapping("/login")
    public String login(HttpSession session) {
        return "login";
    }


    @RequestMapping("/delsession")
    public void delsession(HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session.invalidate();
        request.getRequestDispatcher("/WEB-INF/view/jsp/login.jsp").forward(request, response);
    }


    //设置cookie 登录
    @RequestMapping("/verify")
    @ResponseBody
    public Result verify(HttpServletRequest request) {

        String account = request.getParameter("account");
        String pwd = request.getParameter("password");
        if (StringUtils.isEmpty(account) && StringUtils.isEmpty(pwd)) {
            return Result.failed("用户名和密码不能为空！");
        }
        System.out.println("输入的用户名为：" + account + "\n输入的密码为：" + pwd);

        Users user = null;
        try {
            user = iqs.selectByAccountAndPassword(account, pwd);
        } catch (Exception e) {
            return Result.failed("用户名或密码不正确！");
        }

//        查询角色信息
        Roles role = null;
        try {
            role = iqs.selectByUserid(user.getId());
        } catch (Exception e) {
            return Result.failed("用户名或密码不正确！");
        }
        if (role == null) {
            return Result.failed("用户名或密码不正确！");
        }
        if (role.getName().equals("ROLE_SHOP")) {
            //查询商铺id 不为0则保存到session
            int shopid = 0;
            try {
                shopid = iqs.selectIdByUserId(user.getId());
            } catch (Exception e) {
                return Result.failed("用户名或密码不正确");
            }
            if (shopid != 0) {
                request.getSession().setAttribute("shopid", shopid);
            }
        }

        //           设置session
        request.getSession().setAttribute("userinfo", user);
        return Result.success();
    }


    //   点击菜单栏内容跳转到修改密码页面
    @RequestMapping("/modifypwd")
    public String modifyPassword() {
        return "updatepwd";
    }


    //验证用户名是否存在
    @RequestMapping("/vifaccount")
    @ResponseBody
    public Result vifAccount(@RequestParam("account") String account) {
//        调用服务查询是否存在用户
        List<Users> users = iqs.selectByAccount(account);
        System.out.println("user的信息为\n" + users);
        if (users.isEmpty()) {
            return Result.success();
        } else {
            return Result.failed("用户已存在");
        }

    }


}



