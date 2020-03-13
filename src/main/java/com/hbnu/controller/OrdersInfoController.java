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
import java.math.BigDecimal;
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
    private IQryService iqs;

    /*
     * 跳转至营业额页面
     * */
    @RequestMapping("/income")
    public String toIncome(Model model, HttpSession session) {
//        调用服务 查询 总数
        Users user = (Users) session.getAttribute("userinfo");
        int id = user.getId();


        double totalIncome = iqs.selectCountIncome(id);
        int totalOrders = iqs.selectCountOrders(id);
        int totalUsers = iqs.selectCountUsers(id);
        Map<String, Object> message = new HashMap<>();
        message.put("totalIncome", totalIncome);
        message.put("totalUsers", totalUsers);
        message.put("totalOrders", totalOrders);
        model.addAttribute("message", message);
        return "income";
    }



    /*
     * 获取营业额信息
     * */

    @RequestMapping("/getincome")
    @ResponseBody
    public Result getIncome(HttpSession session) {

        //        调用服务 查询 总数
        Users user = (Users) session.getAttribute("userinfo");
        int id = user.getId();

        List<Map> incomeList = iqs.selectEveryIncome(id);
        List<Map> ordersList = iqs.selectEveryOrders(id);
        List<Map> usersList = iqs.selectEveryUsers(id);

        System.out.println("每日收入详情" + incomeList + '\n' + "每日用户详情" + usersList + '\n' + "每日订单详情" + ordersList);
//        获取订单纵坐标
        ArrayList<String> orderxAxis = new ArrayList<>();
        for (Map map1 : ordersList) {
            String ordertime = map1.get("ordertime").toString();
            orderxAxis.add(ordertime);
        }
        System.out.println("订单表横坐标" + orderxAxis);
        ArrayList<String> orderValue = new ArrayList<>();

        for (Map map2 : ordersList) {
            long count = (long) map2.get("count");
            orderValue.add(String.valueOf(count));
        }
        System.out.println("订单表值" + orderValue);


//  获取用户纵坐标
        ArrayList<String> userxAxis = new ArrayList<>();
        for (Map map1 : usersList) {
            String ordertime = map1.get("ordertime").toString();
            userxAxis.add(ordertime);
        }
        System.out.println("用户表横坐标" + userxAxis);
        ArrayList<String> userValue = new ArrayList<>();

        for (Map map2 : usersList) {
            long count = (long) map2.get("count");
            userValue.add(String.valueOf(count));
        }
        System.out.println("用户表值" + userValue);

        // 获取营业额坐标与值
        ArrayList<String> incomexAxis = new ArrayList<>();
        for (Map map1 : incomeList) {
            String ordertime = map1.get("ordertime").toString();
            incomexAxis.add(ordertime);
        }
        System.out.println("收入表横坐标" + incomexAxis);
        ArrayList<String> incomeValue = new ArrayList<>();

        for (Map map2 : incomeList) {
            BigDecimal orginCount = (BigDecimal) map2.get("count");
            double count = orginCount.doubleValue();
            incomeValue.add(String.valueOf(count));
        }
        System.out.println("收入表值" + incomeValue);

        //填充数据
        Map<String, Object> totalresultMap = new HashMap();
        Map<String, Object> orderMap = new HashMap();
        orderMap.put("xAxis", orderxAxis);
        orderMap.put("value", orderValue);

        Map<String, Object> userMap = new HashMap();
        userMap.put("xAxis", userxAxis);
        userMap.put("value", userValue);

        Map<String, Object> incomeMap = new HashMap();
        incomeMap.put("xAxis", incomexAxis);
        incomeMap.put("value", incomeValue);

//        封装订单，用户，收入信息 结果集
        totalresultMap.put("orderMap", orderMap);
        totalresultMap.put("userMap", userMap);
        totalresultMap.put("incomeMap", incomeMap);


        Result result = Result.success();
        result.setData(totalresultMap);
        return result;
    }


}
