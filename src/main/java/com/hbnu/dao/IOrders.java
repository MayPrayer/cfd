package com.hbnu.dao;

import java.util.List;

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

    int selectCountIncome(int id);


    /*
     * 查询 每天的订单数 用户数 收入
     * */



}
