package com.baizhi.entity;

import java.io.Serializable;

public class UserMap implements Serializable{
    private String province;
    private int count;

    public UserMap() {
    }
    @Override
    public String toString() {
        return "UserMap{" +
                "province='" + province + '\'' +
                ", count=" + count +
                '}';
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public UserMap(String province, int count) {

        this.province = province;
        this.count = count;
    }
}
