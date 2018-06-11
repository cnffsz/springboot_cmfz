package com.baizhi.entity;

import java.io.Serializable;

public class FileName implements Serializable {
    private String url;
    private String oldName;
    private String newName;

    @Override
    public String toString() {
        return "FileName{" +
                "url='" + url + '\'' +
                ", oldName='" + oldName + '\'' +
                ", newName='" + newName + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }

    public FileName() {


    }

    public FileName(String url, String oldName, String newName) {

        this.url = url;
        this.oldName = oldName;
        this.newName = newName;
    }
}
