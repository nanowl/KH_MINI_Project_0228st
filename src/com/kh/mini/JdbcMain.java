package com.kh.mini;

import com.kh.mini.dao.*;

import java.sql.SQLException;
import java.util.Scanner;

public class JdbcMain {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        mainView();
    }

    public static void mainView()  {
        CustomerDAO customerDAO = new CustomerDAO();
        while (true) {
            System.out.print("========= 온라인 가구 샵 =========");
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

    public static void shopView(String id) {
        while (true) {
            System.out.print("========= 온라인 가구 샵 =========");
            System.out.print("[1]상품보기 [2]장바구니담기 [3]장바구니보기 [4]구매 [5]구매내역 [6]나가기 : ");
            int sel = sc.nextInt();
            switch (sel) {
                case 1 :
                    ProductDAO productDAO = new ProductDAO();
                    productDAO.selectList();
                    break;
                case 2 :
                    CartDAO cartDAO = new CartDAO();
                    cartDAO.insertList();
                    break;
                case 3 :
                    //장바구니의 상품번호를 대신해서 상품이름을 조회
                    //Products 테이블이랑 조인해서 가져올것
                    break;
                case 4 :
                    OrderDAO orderDAO = new OrderDAO();
                    orderDAO.insertList();
                    break;
                case 5 :
                    OrderDAO orderDAO1 = new OrderDAO();
                    orderDAO1.selectList();
                    break;
                case 6 :
                    System.out.print("종료");
                    break;
            }
        }
    }
}
