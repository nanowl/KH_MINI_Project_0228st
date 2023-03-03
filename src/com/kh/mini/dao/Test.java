package com.kh.mini.dao;

import java.util.Scanner;

public class Test {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
        while(true) {
            System.out.println("===== [DB Controller] =====");
            System.out.println("테이블을 선택하세요 :");
            System.out.print("[1]PRODUCTS, [2]CUSTOMER, [3]CART, [4]ORDER, [5]EXIT : ");
            int table = sc.nextInt();
            switch (table) {
                case 1:
                    ProductDAO productDAO = new ProductDAO();
                    productDAO.productSelect();
                    break;
                case 2:
                    CustomerDAO customerDAO = new CustomerDAO();
                    customerDAO.customerSelect();
                    break;
                case 3:
                    CartDAO cartDAO = new CartDAO();
                    cartDAO.cartSelect();
                    break;
                case 4:
                    OrderDAO orderDAO = new OrderDAO();
                    orderDAO.listOrder();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }


}
