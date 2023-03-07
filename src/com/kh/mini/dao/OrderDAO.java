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

    // ORDERLIST 테이블의 데이터를 모두 조회하는 메소드
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

    //위 메소드를 오버로딩한 메소드 id를 파라미터로 받아와서 id값과 일치하는 데이터만을 조회한다.
    public void listOrder(String id) {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            // 기존의 외래키 상품번호 열을 이름으로 바꾸기 위해 부모테이블 PRODUCTS와 자식테이블 ORDERLIST를 조인
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


    // DAO interface에서 오버라이딩한 selectList 메소드를 오버로딩한 메소드
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

    //DAO interface에서 오버라이딩한 데이터 조회 기능을 구현한 메소드
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

    //DAO interface에서 오버라이딩한 데이터 추가 기능을 구현한 메소드
    @Override
    public void insertList() {
        sc = new Scanner(System.in);
        System.out.print("가구번호를 입력해주세요 : ");
        int pdtNo = sc.nextInt();
        System.out.print("아이디를 입력해주세요 : ");
        String userId = sc.next();
        System.out.print("배송지를 입력해주세요 : ");
        String loc = sc.next();
        System.out.print("결제할 금액을 입력해주세요 : ");
        int price = sc.nextInt();

        String sql = "INSERT INTO ORDERLIST VALUES (SEQ_ORDERLIST.NEXTVAL, SYSDATE, ?, ?, ?, ?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pdtNo);
            pstmt.setString(2, userId);
            pstmt.setString(3, loc);
            pstmt.setInt(4, price);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);

    }
    //DAO interface에서 오버라이딩한 데이터 삭가 기능을 구현한 메소드
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