package com.hbnu.controller;

import com.github.pagehelper.PageInfo;
import com.hbnu.entity.OrderDetail;
import com.hbnu.entity.Result;
import com.hbnu.entity.Shipaddress;
import com.hbnu.entity.Users;
import com.hbnu.service.IQryService;
import com.hbnu.service.IUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
    @Autowired
    private IUpdateService ius;

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


    /*
     * 订单操作
     *
     * */

    @RequestMapping("/getcurorders")
    @ResponseBody
    public Result getCurOredrs(HttpSession session, @RequestParam("pagenum") int pageNum, @RequestParam("pagesize") int pageSize) {
        int shopid = (int) session.getAttribute("shopid");
        PageInfo pageInfo = iqs.selectCurOrders(pageNum, pageSize, shopid);

        Result result = Result.success();
        result.setCount((int) pageInfo.getTotal());
        result.setData(pageInfo);
        return result;
    }


    @RequestMapping("/curorders")
    public String toCurOrders() {
        return "curorders";
    }

    @RequestMapping("/deloneorder")
    @ResponseBody
    public Result delOneOrder(@RequestParam("id") long id) {
        ius.deleteOneOrder(id);
        Result result = Result.success();
        return result;
    }

    /*
     * 查看详情
     * */
    @RequestMapping("/seemore")
    @ResponseBody
    public Result delOneOrder(@RequestParam("orderid") long orderid, @RequestParam("userid") int userid, @RequestParam("shipadrid") int shipadrid) {
//       商品详情
        System.out.println("商品详情参数" + orderid);

        List<OrderDetail> od = iqs.selectOrderGood(orderid);
        List<OrderDetail> odtemp = iqs.selectOrderDetailByOrId(orderid);
        System.out.println("商品详情1" + od);
        System.out.println("商品详情2" + odtemp);
//        用户信息
        List<Users> user = iqs.selectInfoByUserid(userid);
//        联系方式
        List<Shipaddress> addr = iqs.selectaddrById(shipadrid);
//        封装信息
        Map<String, Object> moreinfo = new HashMap<>();
        moreinfo.put("goodsinfo", od);
        moreinfo.put("userinfo", user);
        moreinfo.put("addrinfo", addr);

        System.out.println("返回信息为" + moreinfo);
        Result result = Result.success();
        result.setData(moreinfo);
        return result;
    }

    /*
     * 查询
     * */
    @RequestMapping("/selctlike")
    @ResponseBody
    public Result selctlike(HttpSession session, @RequestParam("pagenum") int pageNum, @RequestParam("pagesize") int pageSize,
                            @RequestParam("startday") String orlstartday, @RequestParam("enday") String orlenday) throws ParseException {
        int shopid = (int) session.getAttribute("shopid");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startday = df.parse(orlstartday);
        Date enday = df.parse(orlenday);

        PageInfo pageInfo = iqs.selectlikeOrders(pageNum, pageSize, shopid,startday,enday);

        Result result = Result.success();
        result.setCount((int) pageInfo.getTotal());
        result.setData(pageInfo);
        return result;
    }


}
