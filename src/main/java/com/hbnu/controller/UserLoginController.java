package com.hbnu.controller;

import com.hbnu.entity.Result;
import com.hbnu.entity.Users;
import com.hbnu.service.IQryService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


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
    public String index(ModelMap model, HttpSession session) {
        Users user = (Users) session.getAttribute("userinfo");
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
            e.getStackTrace();
        }
        if (user == null) {
            return Result.failed("用户名或密码不正确！");
        } else {
            System.out.println("返回数据");
//           设置session
            request.getSession().setAttribute("userinfo", user);
            return Result.success();
        }
    }

    //   点击菜单栏内容跳转到修改密码页面
    @RequestMapping("/modifypwd")
    public String modifyPassword() {
        return "updatepwd";
    }
}



