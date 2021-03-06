package com.hbnu.service.impl;

import com.hbnu.dao.*;
import com.hbnu.entity.Users;
import com.hbnu.service.IUpdateService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;


/**
 * ClassName: IUpdateService <br/>
 * Description: <br/>
 * date: 2020/2/25 20:26<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
//必须写这个否则无法自动注入
@Service
public class UpdateService implements IUpdateService {
    @Autowired
    private IUserInfo users;
    @Autowired
    private IGoods goods;
    @Autowired
    private IInforms informs;
    @Autowired
    private IOrders orders;

    @Autowired
    private IRoles roles ;
    @Autowired
    private IShops shops;

    @Override
    public void updatePwdByAccountAndPassword(String pwd, String account) {
        users.updatePwdByAccountAndPassword(pwd, account);
    }

    @Override
    public void deleteUserById(int id) {
        users.deleteUserById(id);
    }

    @Override
    public int insertOneUser(Users user) {
        int code = users.insertOneUser(user);
        System.out.println("插入数据返回值为"+code);
        return code;
    }

    @Override
    public int updateOneUser(String account, String pwd, String name, Date birthady, String idcard, String avatar, String phone, String nickname, int id){
        int code=users.updateOneUser(account, pwd,name, birthady,idcard,avatar, phone, nickname,id);
        System.out.println("更新数据返回值为"+code);
        return code;
    }



    @Override
    public int delGoodById(int id) {
        int code = goods.delGoodById(id);
        System.out.println("删除数据返回值为"+code);
        return code;
    }

    @Override
    public int addGood(int shopid, String name, String describes, String img, double price, double discountprice, int sales, int inventory, String goodsstatus, String goodstype) {
        int code = goods.addGood(shopid,name,describes,img,price,discountprice,sales,inventory,goodsstatus,goodstype);
        System.out.println("新增数据返回值为"+code);
        return code;
    }

    @Override
    public int editGood( String name, String describes, String img, double price, double discountprice, int sales, int inventory, String goodsstatus, String goodstype, int id) {
        int code = goods.editGood(name,describes,img,price,discountprice,sales,inventory,goodsstatus,goodstype,id);
        System.out.println("编辑数据返回值为"+code);
        return code;
    }




    @Override
    public int insertOneInform(String title, String content, int shopid ,int important) {
        return informs.insertOneInform(title,content,shopid,important) ;
    }

    @Override
    public int delOneInformById(int id) {
        return informs.delOneInformById(id);
    }

    @Override
    public int deleteOneOrder(long id) {
        return orders.deleteOneOrder(id);
    }




    @Override
    public int insertOneRole(int userid, String name) {
        return roles.insertOneRole(userid,name);
    }

    @Override
    public int delOneRole(int userid) {
        return roles.delOneRole(userid);
    }

    @Override
    public int updateOneRole(String name, int userid) {
        return roles.updateOneRole(name,userid);
    }


    @Override
    public int addOneShop( int userid,String name,String logo,String position,String managername, String managerphone) {
        return shops.addOneShop(userid,name,logo,position,managername,managerphone);
    }

    @Override
    public int delOneShop(int userid) {
        return shops.delOneShop(userid);
    }

    @Override
    public int updateOneShop(String name, String logo, String position, String managername, String managerphone,int userid) {
        return shops.updateOneShop(name,logo,position,managername,managerphone,userid);
    }


}
