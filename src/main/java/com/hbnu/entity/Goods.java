package com.hbnu.entity;

/**
 * ClassName: Goods <br/>
 * Description: <br/>
 * date: 2020/3/2 16:07<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public class Goods {
    private int id;
    private int shopid;
    private String name;
    private String describes;
    private String img;
    private double price;
    private double discountprice; //折扣
    private int sales;  //销量
    private int inventory; //库存
    private String goodsstatus; //商品状态
    private String goodstype;//商品类型

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountprice() {
        return discountprice;
    }

    public void setDiscountprice(double discountprice) {
        this.discountprice = discountprice;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public String getGoodsstatus() {
        return goodsstatus;
    }

    public void setGoodsstatus(String goodsstatus) {
        this.goodsstatus = goodsstatus;
    }

    public String getGoodstype() {
        return goodstype;
    }

    public void setGoodstype(String goodstype) {
        this.goodstype = goodstype;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", shopid=" + shopid +
                ", name='" + name + '\'' +
                ", describes='" + describes + '\'' +
                ", img='" + img + '\'' +
                ", price=" + price +
                ", discountprice=" + discountprice +
                ", sales=" + sales +
                ", inventory=" + inventory +
                ", goodsstatus='" + goodsstatus + '\'' +
                ", goodstype='" + goodstype + '\'' +
                '}';
    }
}
