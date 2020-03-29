package com.hbnu.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.stereotype.Component;

/**
 * ClassName: OrderDetail <br/>
 * Description: <br/>
 * date: 2020/3/2 16:18<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
public class OrderDetail {
    private int id ;
    private int goodsid ;
    @JsonSerialize(using = ToStringSerializer.class)
    private long orderid ;
    private double amount ; //总额
    private String taste ;//口味
    private int quantity ;//数量
    private double price ;
    private String  goodsimg  ;
    private String goodsname ;
//    一对一
    private Goods goods;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
    }

    public long getOrderid() {
        return orderid;
    }

    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGoodsimg() {
        return goodsimg;
    }

    public void setGoodsimg(String goodsimg) {
        this.goodsimg = goodsimg;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }


    @Override
    public String toString() {
        return "OrderDetail{" +
                "id=" + id +
                ", goodsid=" + goodsid +
                ", orderid=" + orderid +
                ", amount=" + amount +
                ", taste='" + taste + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", goodsimg='" + goodsimg + '\'' +
                ", goodsname='" + goodsname + '\'' +
                ", goods=" + goods +
                '}';
    }
}
