package com.hbnu.dao;

import com.hbnu.entity.OrderDetail;
import com.hbnu.entity.Orders;
import org.springframework.core.annotation.Order;

import java.util.List;
import java.util.Map;

/**
 * ClassName: IOrders <br/>
 * Description: <br/>
 * date: 2020/3/12 14:54<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IOrders {

    /*
     * 查询总订单数 总用户数  总收入
     * */
    int selectCountOrders(int id);

    int selectCountUsers(int id);

    double selectCountIncome(int id);


    /*
     * 查询 每天的订单数 用户数 收入
     * */
    List<Map> selectEveryOrders(int id);
    List<Map> selectEveryUsers(int id);
    List<Map> selectEveryIncome(int id);

    /*
    * 查询当前商铺所有订单
    * */

    List<Orders> selectCurOrders(int shopid);
    int deleteOneOrder(long id);





}
