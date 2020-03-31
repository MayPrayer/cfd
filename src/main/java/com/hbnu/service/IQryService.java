package com.hbnu.service;

import com.github.pagehelper.PageInfo;
import com.hbnu.dao.IOrderDetail;
import com.hbnu.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName: IQryService <br/>
 * Description: <br/>
 * date: 2020/2/21 11:55<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IQryService {
    /*
     * 用户服务
     * */
    PageInfo findAll(int pageNum, int pageSize);

    PageInfo selectLikeUser(int pageNum, int pageSize, String account);

    Users selectByAccountAndPassword(String account, String pwd);

    Roles selectByUserid(int userid);

    List<Users> selectByAccount(String account);

    List<Users> selectInfoByUserid(int id);

    int selectidValue();

    /*
     *  商品服务
     * */
    PageInfo selectCurUserGoods(int pageNum, int pageSize, int id);

    PageInfo selectLikeGoods(int pageNum, int pageSize, String name, int shopid);

    /*
     * 营业详情服务
     * */
    int selectCountOrders(int id);

    int selectCountUsers(int id);

    double selectCountIncome(int id);

    List<Map> selectEveryOrders(int id);

    List<Map> selectEveryUsers(int id);

    List<Map> selectEveryIncome(int id);


    /*
     * 公告栏服务
     * */
    List<Informs> selectInformSortBytime(int id);

    int selectIdByUserId(int id);


    /*
     * 订单管理服务
     * */
    PageInfo selectCurOrders(int pageNum, int pageSize, int shopid);


    /*
     * 订单详情服务 根据订单id查询
     * */
    List<OrderDetail> selectOrderGood(long id);
    List<OrderDetail> selectOrderDetailByOrId(long id);

    /*
     *查询地址信息
     * */
    List<Shipaddress> selectaddrById(int id);

    PageInfo selectlikeOrders(int pageNum, int pageSize, int shopid, Date startday, Date enday);
}
