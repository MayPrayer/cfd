package com.hbnu.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName: UserInfo <br/>
 * Description: <br/>
 * date: 2020/2/21 11:41<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
public class Users {

    //下面就是一个普通的javaBean 属性私有化

    private int id;
    private String name;
//   账户名
    private String account;
    private String phone;
    private String pwd;
//    头像链接
    private String avatar;
    private int change;
    private Date birthday;
    private String salt;
    private String secret;
//    昵称
    private String nickname;
    private String idcard;
    private String wechat; //微信编号
    private String cardholdfront;//省份证正面手持
    private String cardholdback;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getChange() {
        return change;
    }

    public void setChange(int change) {
        this.change = change;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getCardholdfront() {
        return cardholdfront;
    }

    public void setCardholdfront(String cardholdfront) {
        this.cardholdfront = cardholdfront;
    }

    public String getCardholdback() {
        return cardholdback;
    }

    public void setCardholdback(String cardholdback) {
        this.cardholdback = cardholdback;
    }


    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", phone='" + phone + '\'' +
                ", pwd='" + pwd + '\'' +
                ", avatar='" + avatar + '\'' +
                ", change=" + change +
                ", birthday=" + birthday +
                ", salt='" + salt + '\'' +
                ", secret='" + secret + '\'' +
                ", nickname='" + nickname + '\'' +
                ", idcard='" + idcard + '\'' +
                ", wechat='" + wechat + '\'' +
                ", cardholdfront='" + cardholdfront + '\'' +
                ", cardholdback='" + cardholdback + '\'' +
                '}';
    }
}
