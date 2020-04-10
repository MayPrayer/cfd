package com.hbnu.service;

import com.hbnu.dao.IOrderDetail;
import com.hbnu.entity.Users;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

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

    int updateOneUser(String account, String pwd, String name, Date birthday, String idcard, String avatar, String phone, String nickname, int id);

    /*
     * 商品服务
     * */
    int delGoodById(int id);

    int addGood(int shopid, String name, String describes, String img, double price, double discountprice, int sales, int inventory, String goodsstatus, String goodstype);

    int editGood(String name, String describes, String img, double price, double discountprice, int sales, int inventory, String goodsstatus, String goodstype, int id);


    /*
     * 公告栏服务
     * */
    int insertOneInform(String title, String content, int shopid, int important);

    int delOneInformById(int id);


    /*
     *订单服务
     * */
    int deleteOneOrder(long id);




    /*
     * 权限服务
     * */

    int insertOneRole(int userid, String name);

    int delOneRole(int userid);

    int updateOneRole( String name, int userid);


    /*
     * 商铺服务
     * */
    int addOneShop(int userid, String name, String logo, String position, String managername, String managerphone);

    int delOneShop(int userid);

    int updateOneShop(String name, String logo, String position, String managername, String managerphone,int userid);


}
