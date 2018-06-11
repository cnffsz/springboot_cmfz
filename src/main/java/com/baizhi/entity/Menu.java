package com.baizhi.entity;

import java.util.List;

public class Menu {
    private Integer id;
    private String title;
    private String iconCls;
    private Integer parentId;
    private String url;
    private List<Menu> seconds;

    public Menu(Integer id, String title, String iconCls, Integer parentId, String url, List<Menu> seconds) {
        this.id = id;
        this.title = title;
        this.iconCls = iconCls;
        this.parentId = parentId;
        this.url = url;
        this.seconds = seconds;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", iconCls='" + iconCls + '\'' +
                ", parentId=" + parentId +
                ", url='" + url + '\'' +
                ", seconds=" + seconds +
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

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Menu> getSeconds() {
        return seconds;
    }

    public void setSeconds(List<Menu> seconds) {
        this.seconds = seconds;
    }

    public Menu() {

    }
}