package com.hbnu.service;

import com.hbnu.dao.IOrderDetail;
import com.hbnu.entity.Users;

/**
 * ClassName: IUpdate <br/>
 * Description: <br/>
 * date: 2020/2/25 20:27<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public interface IUpdateService {
    /*
     * 用户服务
     * */
    void updatePwdByAccountAndPassword(String pwd, String account);

    void deleteUserById(int id);

    int insertOneUser(Users user);

    int updateOneUser(String name, String phone, String nickname, int id);

    /*
     * 商品服务
     * */
    int delGoodById(int id);
    int addGood(int shopid,String name,String describes,String img,double price, double discountprice,int sales,int inventory,String goodsstatus,String goodstype);
    int editGood(String name,String describes,String img,double price, double discountprice,int sales,int inventory,String goodsstatus,String goodstype,int id);


    /*
     * 公告栏服务
     * */
    int insertOneInform(String title,String content,int shopid ,int important);
    int delOneInformById(int id);




    /*
    *订单服务
    * */
    int deleteOneOrder(int id);




}
