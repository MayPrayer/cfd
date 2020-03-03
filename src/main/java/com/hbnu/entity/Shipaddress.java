package com.hbnu.entity;

/**
 * ClassName: Shipaddress <br/>
 * Description: <br/>
 * date: 2020/3/2 16:26<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
public class Shipaddress {
    private int id ;
    private int userid ;
    private String name ;//姓名
    private String phone ;//手机号
    private String address ;//地址
    private String label ;//标签
    private String housenumber ;//门牌号


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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }


    @Override
    public String toString() {
        return "Shipaddress{" +
                "id=" + id +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", label='" + label + '\'' +
                ", housenumber='" + housenumber + '\'' +
                '}';
    }
}
