package com.kh.mini.dao;

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
public class CartDAO implements DAO{
    List<CartList> list = new ArrayList<>();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    PreparedStatement pstmt = null;

    public void listCart() {
        try {
            list.clear();
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM CART";
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int no = rs.getInt("PDT_NO_CART");
                String userId = rs.getString("USER_ID_CART");
                int cnt = rs.getInt("cnt");

                CartList vo = new CartList(no, userId,cnt);

                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cartSelect() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("===== [CartList Table] =====");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT");
            int sel = sc.nextInt();
            switch(sel) {
                case 1:
                    listCart();
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
        System.out.println("====================================");
        System.out.println("가구번호     유저아이디       개수        ");
        for(CartList e : list) {
            System.out.printf("%d", e.getPDT_NO_NUMBER());
            System.out.printf(" %s",  e.getUSER_ID_CART() + " ");
            System.out.printf("%d", e.getCnt());
            System.out.println();
        }
    }


    // 장바구니 추가 관련 기능
    // 참조하는 상품명을 따로 리스트를 불러와서 추가하는 구현 기능
    @Override
    public void insertList() {
        Scanner sc = new Scanner(System.in);
        Map view = new HashMap();

        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM PRODUCTS";
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("PRODUCT_ID");
                String name = rs.getString("PRODUCT_NAME");
                int price = rs.getInt("PRICE");
                view.put(id,name+" ("+price+"원)");
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }

        view.forEach((key, value) -> {
            System.out.println("["+key+"]" + " : " + value);
        });

        System.out.print("장바구니에 넣을 가구를 선택해주세요 [숫자 입력]");
        int PDT_NO_NUMBER = sc.nextInt();
        System.out.print("유저아이디를 입력해주세요.");
        String user_id_cart = sc.next();
        System.out.print("수량을 입력해주세요.");
        int cnt = sc.nextInt();


        String sql = "INSERT INTO CART VALUES ( ?, ?, ?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, PDT_NO_NUMBER);
            pstmt.setString(2, user_id_cart);
            pstmt.setInt(3, cnt);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }




    @Override
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

