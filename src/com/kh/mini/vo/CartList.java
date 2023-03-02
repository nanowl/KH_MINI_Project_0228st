package com.kh.mini.vo;

public class CartList {
    private int PDT_NO_NUMBER;
    private String USER_ID_CART;

    public int getPDT_NO_NUMBER() {
        return PDT_NO_NUMBER;
    }

    public void setPDT_NO_NUMBER(int PDT_NO_NUMBER) {
        this.PDT_NO_NUMBER = PDT_NO_NUMBER;
    }

    public String getUSER_ID_CART() {
        return USER_ID_CART;
    }

    public void setUSER_ID_CART(String USER_ID_CART) {
        this.USER_ID_CART = USER_ID_CART;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public CartList(int PDT_NO_NUMBER, String USER_ID_CART, int cnt) {
        this.PDT_NO_NUMBER = PDT_NO_NUMBER;
        this.USER_ID_CART = USER_ID_CART;
        this.cnt = cnt;
    }

    private int cnt;
}
