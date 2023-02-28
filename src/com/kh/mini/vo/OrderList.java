package com.kh.mini.vo;

import java.sql.Date;

// 주문 정보 : 주문번호(PK), 주문일자, 가구번호(FK), 고객아이디(FK), 배송지, 결제금액
public class OrderList {
    int no;
    Date date;
    int pdtNo;
    String custId;
    String loc;
    int price;

    public OrderList(int no, Date date, int pdtNo, String custId, String loc, int price) {
        this.no = no;
        this.date = date;
        this.pdtNo = pdtNo;
        this.custId = custId;
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

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
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
