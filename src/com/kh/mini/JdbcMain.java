package com.kh.mini;

import com.kh.mini.dao.OrderDAO;
import com.kh.mini.util.Common;
import com.kh.mini.vo.OrderList;

import java.util.Scanner;

public class JdbcMain {
    public static void main(String[] args) {
        menuSelect();
    }
    public static void menuSelect() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("===== [DB Controller] =====");
            System.out.println("테이블을 선택하세요 :");
            System.out.print("[1]PRODUCTS, [2]CUSTOMER, [3]CART, [4]ORDER, [5]EXIT : ");
            int table = sc.nextInt();
            switch (table) {
                case 1:
                case 2:
                case 3:
                case 4:
                    OrderDAO orderDAO = new OrderDAO();
                    orderDAO.orderSelect();
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }

        }
    }
}
