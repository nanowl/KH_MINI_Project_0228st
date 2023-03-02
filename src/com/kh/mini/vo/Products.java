package com.kh.mini.vo;
/*
CREATE TABLE PRODUCTS(
PRODUCT_ID NUMBER PRIMARY KEY,
PRODUCT_NAME VARCHAR2(20) NOT NULL,
COLOR VARCHAR2(20) NOT NULL,
PRICE NUMBER NOT NULL,
MADE_IN VARCHAR2(20) NOT NULL
);
 */
public class Products {
    int productId;
    String productName;
    String color;
    int price;
    String made;

    public Products(int productId, String productName, String color, int price, String made) {
        this.productId = productId;
        this.productName = productName;
        this.color = color;
        this.price = price;
        this.made = made;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getMade() {
        return made;
    }

    public void setMade(String made) {
        this.made = made;
    }
}
