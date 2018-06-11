package com.baizhi.entity;

import java.util.Date;

public class User {
    private String id;
    private String phoneNum;
    private String password;
    private String salt;
    private String headPic;
    private String dhamaName;
    private String username;
    private String sex;
    private String province;
    private String city;
    private String sign;
    private String status;
    private Date date;
    private String greatId;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", headPic='" + headPic + '\'' +
                ", dhamaName='" + dhamaName + '\'' +
                ", username='" + username + '\'' +
                ", sex='" + sex + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", sign='" + sign + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                ", greatId='" + greatId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getDhamaName() {
        return dhamaName;
    }

    public void setDhamaName(String dhamaName) {
        this.dhamaName = dhamaName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getGreatId() {
        return greatId;
    }

    public void setGreatId(String greatId) {
        this.greatId = greatId;
    }

    public User() {

    }

    public User(String id, String phoneNum, String password, String salt, String headPic, String dhamaName, String username, String sex, String province, String city, String sign, String status, Date date, String greatId) {

        this.id = id;
        this.phoneNum = phoneNum;
        this.password = password;
        this.salt = salt;
        this.headPic = headPic;
        this.dhamaName = dhamaName;
        this.username = username;
        this.sex = sex;
        this.province = province;
        this.city = city;
        this.sign = sign;
        this.status = status;
        this.date = date;
        this.greatId = greatId;
    }
}
