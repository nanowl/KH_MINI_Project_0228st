package com.kh.mini;

import com.kh.mini.dao.*;

import java.util.Scanner;

public class JdbcMain {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        mainView();
    }
    // 로그인 화면 UI 메소드
    public static void mainView()  {
        CustomerDAO customerDAO = new CustomerDAO();
        while (true) {
            System.out.println("========= 온라인 가구 샵 =========");
            System.out.print("[1]로그인, [2]회원가입, [3]EXIT : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    // CustomerDAO 에서 스캐너로 입력받은 아이디와 비밀번호가 모두 일치하는 데이터를 테이블에서 대조 하고
                    // 일치하면 true, 일치하지 않으면 false 를 반환하는 메소드만들기
                    System.out.print("ID : ");
                    String id = sc.next(); // 입력한 아이디 저장
                    System.out.print("PW : ");
                    String pwd = sc.next(); // 입력한 비밀번호 저장

                    boolean isLogin = false;
                    isLogin = customerDAO.login(id, pwd);
                    if (isLogin) shopView(id);
                    break;
                case 2 :
                    customerDAO.insertList();
                    break;
                case 3 :
                    System.out.print("종료.");
                    return;
            }
        }
    }

    // 가구 쇼핑몰의 UI 메소드
    public static void shopView(String id) {
        System.out.println("========= 온라인 가구 샵 =========");
        ProductDAO productDAO = new ProductDAO();
        productDAO.selectList();
        while (true) {
            System.out.print("[1]장바구니담기 [2]장바구니보기 [3]구매 [4]구매내역 [5]나가기 : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    CartDAO cartDAO = new CartDAO();
                    cartDAO.insertList(id);
                    break;
                case 2 :
                    CartDAO cartDAO1 = new CartDAO();
                    cartDAO1.viewCart(id);
                    break;
                case 3 :
                    OrderDAO orderDAO = new OrderDAO();
                    orderDAO.insertList();
                    break;
                case 4 :
                    OrderDAO orderDAO1 = new OrderDAO();
                    orderDAO1.selectList(id);
                    break;
                case 5 :
                    System.out.print("종료");
                    break;
            }
        }
    }
}
