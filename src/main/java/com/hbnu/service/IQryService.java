package com.hbnu.service;

import com.github.pagehelper.PageInfo;
import com.hbnu.entity.Goods;
import com.hbnu.entity.Roles;
import com.hbnu.entity.Users;
import org.apache.ibatis.annotations.Param;

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

    /*
     *  商品服务
     * */
    PageInfo selectCurUserGoods(int pageNum, int pageSize, int id);


    /*
     * 营业详情服务
     * */
    int selectCountOrders(int id);

     int selectCountUsers(int id);

     int selectCountIncome(int id);

    List<Map> selectEveryOrders(int id);
    List<Map> selectEveryUsers(int id);
    List<Map> selectEveryIncome(int id);




}
