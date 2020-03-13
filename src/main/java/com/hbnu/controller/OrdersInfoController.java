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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public Result getIncome(HttpSession session) {

        //        调用服务 查询 总数
        Users user =(Users) session.getAttribute("userinfo");
        int id = user.getId();

        List<Map> incomeList = iqs.selectEveryIncome(id);
        List<Map> ordersList = iqs.selectEveryOrders(id);
        List<Map> usersList = iqs.selectEveryUsers(id);

        System.out.println("每日收入详情"+incomeList+'\n'+"每日用户详情"+usersList+'\n'+"每日订单详情"+ordersList);
//        获取纵坐标
        ArrayList<String> xAxisList = new ArrayList<>();
        for (Map map1 :ordersList){
            String ordertime = map1.get("ordertime").toString();
            xAxisList.add(ordertime);
        }
        System.out.println("订单表横坐标"+xAxisList);
        ArrayList<String> value = new ArrayList<>();

        for (Map map2 :ordersList){
           long count = (long) map2.get("count");
            value.add(String.valueOf(count));
        }
        System.out.println("订单表值"+value);
        ArrayList list = new ArrayList();
        list.add(xAxisList);
        list.add(value);
        System.out.println("输出数据为："+list);
        Result result = Result.success();
        result.setData(list);
        return  result;
    }


}
