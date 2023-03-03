package com.kh.mini.dao;

import com.kh.mini.util.Common;
import com.kh.mini.vo.OrderList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
장바구니 표시 데이터 : 상품이름 , 고객아이디, 개수
    select products.product_name, customer.user_id, cart.cnt
    from products
 */
public class OrderDAO implements DAO{
    static List<OrderList> list = new ArrayList<>();
    public void listOrder() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        Scanner sc = new Scanner(System.in);
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM ORDERLIST";
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int no = rs.getInt("ORDER_NO");
                Date date = rs.getDate("ORDER_DATE");
                int pdtNo = rs.getInt("PDT_NO");
                String userId = rs.getString("USER_ID");
                String loc = rs.getString("LOC");
                int price = rs.getInt("PRICE");

                OrderList vo = new OrderList(no, date, pdtNo, userId, loc, price);

                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void orderSelect() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("===== [OrderList Table] =====");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT");
            int sel = sc.nextInt();
            switch(sel) {
                case 1:
                    listOrder();
                    selectList();
                    break;
                case 2 :
                    insertList();
                    break;
                case 3 :
                    break;
                case 4 :
                    deleteList();
                    break;
                case 5 :
                    System.out.println("메뉴를 종료 합니다");
                    return;
            }
        }
    }

    @Override
    public void selectList() {
        System.out.println("=======================================================================");
        System.out.println("주문번호     주문일자       상품번호        고객아이디         배송지       가격");
        for(OrderList e : list) {
            System.out.printf("%4d",e.getNo());
            System.out.print("    " + e.getDate() + "    ");
            System.out.printf("%4d", e.getPdtNo());
            System.out.printf("%15s " ,e.getUserId());
            System.out.printf("%15s", e.getLoc());
            System.out.printf("%6d",e.getPrice());
            System.out.println();
        }
    }

    @Override
    public void insertList() {
        System.out.print("");

    }

    @Override
    public void deleteList() {

    }
}
