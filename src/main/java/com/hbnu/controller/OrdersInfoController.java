package com.hbnu.controller;

import com.hbnu.entity.Result;
import com.hbnu.entity.Users;
import com.hbnu.service.IQryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: OrdersInfoController <br/>
 * Description: 订单管理
 * date: 2020/3/12 10:22<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/ordermanager")
public class OrdersInfoController {
    @Autowired
    private IQryService iqs ;
    /*
     * 跳转至营业额页面
     * */
    @RequestMapping("/income")
    public String toIncome(Model model, HttpSession session) {
//        调用服务 查询 总数
        Users user =(Users) session.getAttribute("userinfo");
        int id = user.getId();
        double totalIncome = iqs.selectCountIncome(id);
        int totalOrders = iqs.selectCountOrders(id);
        int totalUsers =iqs.selectCountUsers(id);
        Map<String,Object> message = new HashMap<>();
        message.put("totalIncome",totalIncome);
        message.put("totalUsers",totalUsers);
        message.put("totalOrders",totalOrders);
        model.addAttribute("message",message);
        return "income";
    }



    /*
     * 获取营业额信息
     * */

    @RequestMapping("/getincome")
    @ResponseBody
    public Result getIncome() {

        return Result.failed("获取营业额失败");
    }


}
