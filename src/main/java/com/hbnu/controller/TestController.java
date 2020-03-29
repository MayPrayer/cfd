package com.hbnu.controller;

import com.hbnu.dao.IOrderDetail;
import com.hbnu.entity.OrderDetail;
import com.hbnu.entity.Orders;
import com.hbnu.entity.Result;
import com.hbnu.service.IQryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassName: TestController <br/>
 * Description: <br/>
 * date: 2020/3/27 11:05<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/test")
public class TestController {
    @Autowired
    private IQryService iqs;

    @RequestMapping("/selectorderdetail")
    public String selectOrderDetail() {
        long id = 1585148804817184813L;
        List<OrderDetail> i = iqs.selectOrderGood(id);
        System.out.println("订单信息为" + i);

        return "login";
    }

}
