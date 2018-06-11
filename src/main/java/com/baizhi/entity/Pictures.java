package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.Date;

public class Pictures implements Serializable {
    private Integer id;
    private String  title;
    private String imgPath;
    private String ins;
    private String sta;
    @JSONField(format= "yyyy-MM-dd")
    private Date  date;

    @Override
    public String toString() {
        return "Pictures{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", imgPath='" + imgPath + '\'' +
                ", ins='" + ins + '\'' +
                ", sta='" + sta + '\'' +
                ", date=" + date +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Pictures() {

    }

    public Pictures(Integer id, String title, String imgPath, String ins, String sta, Date date) {

        this.id = id;
        this.title = title;
        this.imgPath = imgPath;
        this.ins = ins;
        this.sta = sta;
        this.date = date;
    }
}
