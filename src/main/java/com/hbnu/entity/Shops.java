package com.hbnu.entity;

import org.springframework.stereotype.Component;

/**
 * ClassName: Shops <br/>
 * Description: <br/>
 * date: 2020/3/2 16:29<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
public class Shops {
    private int id ;
    private int userid ;
    private String name ;
    private String describes;
    private String logo ;
    private String showimgs ;
    private String position ;
    private String shopstatus ;
    private String facadeimg ;
    private String blicense;
    private String foodblicense ;
    private String shoptype ;
    private String managername;
    private String managerphone;


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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getShowimgs() {
        return showimgs;
    }

    public void setShowimgs(String showimgs) {
        this.showimgs = showimgs;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getShopstatus() {
        return shopstatus;
    }

    public void setShopstatus(String shopstatus) {
        this.shopstatus = shopstatus;
    }

    public String getFacadeimg() {
        return facadeimg;
    }

    public void setFacadeimg(String facadeimg) {
        this.facadeimg = facadeimg;
    }

    public String getBlicense() {
        return blicense;
    }

    public void setBlicense(String blicense) {
        this.blicense = blicense;
    }

    public String getFoodblicense() {
        return foodblicense;
    }

    public void setFoodblicense(String foodblicense) {
        this.foodblicense = foodblicense;
    }

    public String getShoptype() {
        return shoptype;
    }

    public void setShoptype(String shoptype) {
        this.shoptype = shoptype;
    }

    public String getManagername() {
        return managername;
    }

    public void setManagername(String managername) {
        this.managername = managername;
    }

    public String getManagerphone() {
        return managerphone;
    }

    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone;
    }

    @Override
    public String toString() {
        return "Shops{" +
                "id=" + id +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", describes='" + describes + '\'' +
                ", logo='" + logo + '\'' +
                ", showimgs='" + showimgs + '\'' +
                ", position='" + position + '\'' +
                ", shopstatus='" + shopstatus + '\'' +
                ", facadeimg='" + facadeimg + '\'' +
                ", blicense='" + blicense + '\'' +
                ", foodblicense='" + foodblicense + '\'' +
                ", shoptype='" + shoptype + '\'' +
                ", managername='" + managername + '\'' +
                ", managerphone='" + managerphone + '\'' +
                '}';
    }
}
