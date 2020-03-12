package com.hbnu.controller;

import com.hbnu.entity.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /*
     * 跳转至营业额页面
     * */
    @RequestMapping("/income")
    public String toIncome() {
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

    ;


}
