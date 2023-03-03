package com.kh.mini.vo;

public class Customer {
    String userId;
    String userPwd;
    String userName;
    String phone;
    String eMail;
    String address;

    public Customer(String userId, String userPwd, String userName, String phone, String eMail, String address) {
        this.userId = userId;
        this.userPwd = userPwd;
        this.userName = userName;
        this.phone = phone;
        this.eMail = eMail;
        this.address = address;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
