package com.hbnu.entity;

/**
 * ClassName: OrderDetail <br/>
 * Description: <br/>
 * date: 2020/3/2 16:18<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public class OrderDetail {
    private int id ;
    private int goodsid ;
    private int orderid ;
    private int amount ; //总额
    private String taste ;//口味
    private int quantity ;//数量
    private int price ;
    private String  goodsimg  ;
    private String goodsname ;

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

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
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
                '}';
    }
}
