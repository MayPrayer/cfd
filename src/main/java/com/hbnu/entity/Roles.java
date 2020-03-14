package com.hbnu.entity;

import org.springframework.stereotype.Component;

/**
 * ClassName: Role <br/>
 * Description: 角色表
 * date: 2020/2/26 11:54<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
public class Roles {
    private int id;
    private int userid;  //用户id
    private String name; //角色名
    private String describes;


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


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", userid=" + userid +
                ", name='" + name + '\'' +
                ", describes='" + describes + '\'' +
                '}';
    }
}
