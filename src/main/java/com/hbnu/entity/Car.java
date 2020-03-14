package com.hbnu.entity;

import org.springframework.stereotype.Component;

/**
 * ClassName: Car <br/>
 * Description: <br/>
 * date: 2020/2/26 11:58<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
public class Car {
    private int id;
    private  int userid;        //用户id
    private  int shopid;        //商铺id
    private  int goodsid;       //商品id
    private  int quantity;      //数量
    private  int price;         //单价
    private  String goodsname;   //商品名臣
    private  String img;         //图片地址url字符串


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public int getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(int goodsid) {
        this.goodsid = goodsid;
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

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", userid=" + userid +
                ", shopid=" + shopid +
                ", goodsid=" + goodsid +
                ", quantity=" + quantity +
                ", price=" + price +
                ", goodsname='" + goodsname + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}

