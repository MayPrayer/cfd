package com.hbnu.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * ClassName: Orders <br/>
 * Description: <br/>
 * date: 2020/3/2 16:21<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
public class Orders {
//    解决精度损失问题
    @JsonSerialize(using = ToStringSerializer.class)
    private long id;
    private int userid;
    private int shopid;    //商铺编号
    private int shipadrid;  //收获地址编号
    private int amount;   //订单总额
    private String ordertime;  //订单日期
    private  String orderstate;
    private  String message;  //备注消息
    private  String deliverytime; //交付时间
//  一对多
    private  List<OrderDetail> orderDetails;


    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getShipadrid() {
        return shipadrid;
    }

    public void setShipadrid(int shipadrid) {
        this.shipadrid = shipadrid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(String ordertime) {
        this.ordertime = ordertime;
    }

    public String getOrderstate() {
        return orderstate;
    }

    public void setOrderstate(String orderstate) {
        this.orderstate = orderstate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDeliverytime() {
        return deliverytime;
    }

    public void setDeliverytime(String deliverytime) {
        this.deliverytime = deliverytime;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    //    @Override
//    public String toString() {
//        return "Orders{" +
//                "id=" + id +
//                ", userid=" + userid +
//                ", shopid=" + shopid +
//                ", shipadrid=" + shipadrid +
//                ", amount=" + amount +
//                ", ordertime=" + ordertime +
//                ", orderstate='" + orderstate + '\'' +
//                ", message='" + message + '\'' +
//                ", deliverytime='" + deliverytime + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", userid=" + userid +
                ", shopid=" + shopid +
                ", shipadrid=" + shipadrid +
                ", amount=" + amount +
                ", ordertime=" + ordertime +
                ", orderstate='" + orderstate + '\'' +
                ", message='" + message + '\'' +
                ", deliverytime='" + deliverytime + '\'' +
                ", orderDetails=" + orderDetails +
                '}';
    }
}
