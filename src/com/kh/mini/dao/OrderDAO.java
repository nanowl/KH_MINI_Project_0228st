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
    List<OrderList> list = new ArrayList<>();
    Scanner sc = null;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;


    public void listOrder() {
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

    public void listOrder(String id) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT O.ORDER_NO, O.ORDER_DATE, P.PRODUCT_NAME, O.USER_ID, O.LOC, O.PRICE " +
                            "FROM ORDERLIST O JOIN PRODUCTS P " +
                                    "ON O.PDT_NO = P.PRODUCT_ID " +
                            "WHERE USER_ID = '" + id + "'" ;
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                int no = rs.getInt("ORDER_NO");
                Date date = rs.getDate("ORDER_DATE");
                String pdtName = rs.getString("PRODUCT_NAME");
                String userId = rs.getString("USER_ID");
                String loc = rs.getString("LOC");
                int price = rs.getInt("PRICE");

                OrderList vo = new OrderList();
                vo.setNo(no);
                vo.setDate(date);
                vo.setPdtName(pdtName);
                vo.setUserId(userId);
                vo.setLoc(loc);
                vo.setPrice(price);
                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void selectList(String id) {
        listOrder(id);
        System.out.println("===========================================================================");
        System.out.println("주문번호     주문일자               가구이름         고객아이디         배송지       가격");
        System.out.println("===========================================================================");
        for(OrderList e : list) {
            System.out.printf("%4d",e.getNo());
            System.out.print("    " + e.getDate() + "    ");
            System.out.printf("%15s", e.getPdtName());
            System.out.printf("%15s " ,e.getUserId());
            System.out.printf("%15s", e.getLoc());
            System.out.printf("%6d",e.getPrice());
            System.out.println();
        }
    }
    @Override
    public void selectList() {
        System.out.println("=======================================================================");
        System.out.println("주문번호     주문일자       가구번호        고객아이디         배송지       가격");
        System.out.println("=======================================================================");
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

//    public void orderSelect() {
//        Scanner sc = new Scanner(System.in);
//        while(true) {
//            System.out.println("===== [OrderList Table] =====");
//            System.out.println("메뉴를 선택 하세요 : ");
//            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT");
//            int sel = sc.nextInt();
//            switch(sel) {
//                case 1:
//                    listOrder();
//                    selectList();
//                    break;
//                case 2 :
//                    insertList();
//                    break;
//                case 3 :
//                    break;
//                case 4 :
//                    deleteList();
//                    break;
//                case 5 :
//                    System.out.println("메뉴를 종료 합니다");
//                    return;
//            }
//        }
//    }