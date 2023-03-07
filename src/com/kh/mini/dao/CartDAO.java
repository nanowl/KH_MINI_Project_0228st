package com.kh.mini.dao;

import com.kh.mini.JdbcMain;
import com.kh.mini.util.Common;
import com.kh.mini.vo.CartList;
import com.kh.mini.vo.OrderList;
import com.kh.mini.vo.Products;

import java.sql.*;
import java.util.*;

/*
장바구니 표시 데이터 : 상품이름 , 고객아이디, 개수
    select products.product_name, customer.user_id, cart.cnt
    from products
 */
public class CartDAO {
    List<CartList> list = new ArrayList<>();
    String id;
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;


    public void viewCart(String id) {
        try {
            list.clear();
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM CART C JOIN PRODUCTS P ON C.PDT_NO_CART = P.PRODUCT_ID WHERE CNT >= 1";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String no = rs.getString("PRODUCT_NAME");
                String userId = rs.getString("USER_ID_CART");
                int cnt = rs.getInt("cnt");
                if (id.equals(userId)) {
                    CartList vo = new CartList(userId, no, cnt);

                    list.add(vo);
                }
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=========================================");
        System.out.println( id + " 님의 장바구니 목록");
        System.out.println("=========================================");
        for (CartList e : list) {

            System.out.println("상품명 : " + e.getPRODUCT_NAME() + "["+ e.getCnt() +"]" );

            //System.out.println(" 수량 :  " + e.getCnt());
            System.out.println("-----------------------------------------");
        }
    }



    public void cartSelect() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== [CartList Table] =====");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:

                    selectList();
                    break;
                case 2:
                    insertList(id);
                    break;
                case 3:
                    break;
                case 4:
                    deleteList();
                    break;
                case 5:
                    System.out.println("메뉴를 종료 합니다");
                    return;
            }
        }
    }


    public void selectList() {
        System.out.println("====================================");
        System.out.println("가구번호     유저아이디       개수        ");
        for (CartList e : list) {
            System.out.printf("%d", e.getPDT_NO_NUMBER());
            System.out.printf(" %s", e.getUSER_ID_CART() + " ");
            System.out.printf("%d", e.getCnt());
            System.out.println();
        }
    }


    // 장바구니 추가 관련 기능
    // 참조하는 상품명을 따로 리스트를 불러와서 추가하는 구현 기능


    public void insertList(String id) {
        Scanner sc = new Scanner(System.in);
        Map view = new HashMap();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM PRODUCTS";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id2 = rs.getInt("PRODUCT_ID");
                String name = rs.getString("PRODUCT_NAME");
                int price = rs.getInt("PRICE");
                view.put(id2, name + " (" + price + "원)");
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }

        view.forEach((key, value) -> {
            System.out.println("[" + key + "]" + " : " + value);
        });

        String user_id_cart = id;
        System.out.print("추가할 상품을 선택해주세요 [숫자입력] : ");
        int PDT_NO_NUMBER = sc.nextInt();
        System.out.print("상품 수량을 입력해주세요 : ");
        int cnt = sc.nextInt();


        String qur = "SELECT * FROM CART C JOIN PRODUCTS P ON C.PDT_NO_CART = P.PRODUCT_ID WHERE USER_ID_CART = ?";
        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(qur);
            pstmt.setString(1, user_id_cart);

            rs = pstmt.executeQuery();


            int getId = 0;
            while (rs.next()) {
                getId = rs.getInt("PRODUCT_ID");

                if (PDT_NO_NUMBER == getId) {
                    System.out.print("이미 장바구니에 해당상품이 있습니다 " +"["+ cnt +"]"+" 만큼 수량 변경 하였습니다." );
                    String qur2 = "UPDATE CART SET CNT = ? WHERE USER_ID_CART = ? AND PDT_NO_CART = ?";
                    try {
                        int cnt1 = rs.getInt("CNT");
                        pstmt = conn.prepareStatement(qur2);
                        pstmt.setInt(1, cnt + cnt1);
                        pstmt.setString(2, id);
                        pstmt.setInt(3, getId);
                        pstmt.executeUpdate();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
            if (getId != PDT_NO_NUMBER) {
                String sql = "INSERT INTO CART VALUES (?, ?, ?)";
                try {
                    pstmt = conn.prepareStatement(sql);
                    pstmt.setInt(1, PDT_NO_NUMBER);
                    pstmt.setString(2, user_id_cart);
                    pstmt.setInt(3, cnt);
                    pstmt.executeUpdate();

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Common.close(rs);
            Common.close(pstmt);
            Common.close(conn);
        }

    }



    public void deleteList() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 가구번호를 입력 하세요 : ");
        int pdt_no = sc.nextInt();
        String sql = "DELETE FROM CART WHERE PDT_NO_CART = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pdt_no);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
    }

