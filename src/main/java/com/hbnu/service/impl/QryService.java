package com.hbnu.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbnu.dao.*;
import com.hbnu.entity.*;
import com.hbnu.service.IQryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * ClassName: QryService <br/>
 * Description: <br/>
 * date: 2020/2/21 11:58<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Service
public class QryService implements IQryService {
    @Autowired
    private IUserInfo users;
    @Autowired
    private IRoles roles;
    @Autowired
    private IGoods goods;
    @Autowired
    private IOrders orders;
    @Autowired
    private IInforms informs;
    @Autowired
    private IShops shops;
    @Autowired
    private IOrderDetail orderdetail;
    @Autowired
    private IShipaddress shipaddress;



    @Override
    public PageInfo findAll(int pageNum, int pageSize) {
//        开始分页
        PageHelper.startPage(pageNum, pageSize);
        List<Users> list = users.findAll();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo selectLikeUser(int pageNum, int pageSize, String account) {
        PageHelper.startPage(pageNum, pageSize);
        List<Users> list = users.selectLikeUser(account);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    @Override
    public Users selectByAccountAndPassword(String account, String pwd) {
        return users.selectByAccountAndPassword(account, pwd);
    }

    @Override
    public Roles selectByUserid(int userid) {
        return roles.selectByUserid(userid);
    }

    @Override
    public List<Users> selectByAccount(String account) {
        return users.selectByAccount(account);
    }

    @Override
    public List<Users> selectInfoByUserid(int id) {
        return users.selectInfoByUserid(id);
    }


    //当前用户商品信息
    @Override
    public PageInfo selectCurUserGoods(int pageNum, int pageSize, int id) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goods.selectCurUserGoods(id);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public PageInfo selectLikeGoods(int pageNum, int pageSize, String name,int shopid) {
        PageHelper.startPage(pageNum, pageSize);
        List<Goods> list = goods.selectLikeGoods(name,shopid);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }


    /*
     *  查询用户总数 总收入 订单总数
     * */
    @Override
    public int selectCountOrders(int id) {
        return orders.selectCountOrders(id);
    }

    @Override
    public int selectCountUsers(int id) {
        return orders.selectCountUsers(id);
    }

    @Override
    public double selectCountIncome(int id) {

        return orders.selectCountIncome(id);
    }

    /*
     * 查询每日的的用户数，输欧如，订单数
     * */

    @Override
    public List<Map> selectEveryOrders(int id) {
        return orders.selectEveryOrders(id);
    }

    @Override
    public List<Map> selectEveryUsers(int id) {
        return orders.selectEveryUsers(id);
    }

    @Override
    public List<Map> selectEveryIncome(int id) {
        return orders.selectEveryIncome(id);
    }



    /*
     * 查询分页公告
     * */
    @Override
    public List<Informs> selectInformSortBytime(int id) {
        List<Informs> list = informs.selectInformSortBytime(id);
        return list;
    }

    /*
     * 根据用户id查询商铺id
     * */
    @Override
    public int selectIdByUserId(int id) {
        return shops.selectIdByUserId(id);
    }

    /*
    查询当前商铺订单
    * */
    @Override
    public PageInfo selectCurOrders(int pageNum, int pageSize,int shopid) {
        PageHelper.startPage(pageNum, pageSize);

        return new PageInfo(orders.selectCurOrders(shopid));
    }

    @Override
    public List<OrderDetail> selectOrderGood(long id) {
        return  orderdetail.selectOrderGood(id);
    }

    @Override
    public List<OrderDetail> selectOrderDetailByOrId(long id) {
        return orderdetail.selectOrderDetailByOrId(id);
    }

    @Override
    public List<Shipaddress> selectaddrById(int id) {
        return shipaddress.selectaddrById(id);
    }


}
