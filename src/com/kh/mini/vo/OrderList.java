package com.kh.mini.vo;

import java.sql.Date;

public class OrderList {
    int no ;
    Date date;
    int pdtNo;
    String pdtName;
    String userId;
    String loc;
    int price;

    public OrderList() {}

    public OrderList(int no, Date date, int pdtNo, String userId, String loc, int price) {
        this.no = no;
        this.date = date;
        this.pdtNo = pdtNo;
        this.userId = userId;
        this.loc = loc;
        this.price = price;
    }


    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getPdtNo() {
        return pdtNo;
    }

    public void setPdtNo(int pdtNo) {
        this.pdtNo = pdtNo;
    }

    public String getPdtName() {
        return pdtName;
    }

    public void setPdtName(String pdtName) {
        this.pdtName = pdtName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
