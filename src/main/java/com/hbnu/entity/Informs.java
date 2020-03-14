package com.hbnu.entity;

import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * ClassName: Informs <br/>
 * Description: <br/>
 * date: 2020/3/14 12:02<br/>
 *
 * @author MayPrayer<br />
 * @since JDK 1.8
 */
@Component
public class Informs {
    private int id;
    private int shopid;
    private String title;
    private String content;
    private Date createtime;
    private int important;


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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public int getImportant() {
        return important;
    }

    public void setImportant(int important) {
        this.important = important;
    }

    @Override
    public String toString() {
        return "Informs{" +
                "id=" + id +
                ", shopid=" + shopid +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createtime=" + createtime +
                ", important=" + important +
                '}';
    }
}
