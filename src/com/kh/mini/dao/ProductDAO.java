package com.kh.mini.dao;

import com.kh.mini.util.Common;
import com.kh.mini.vo.OrderList;
import com.kh.mini.vo.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
장바구니 표시 데이터 : 상품이름 , 고객아이디, 개수
 */
public class ProductDAO implements DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement  pstmt = null;
    ResultSet rs = null;
    List<Products> list = new ArrayList<>();

    public List<Products> listProducts() {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM PRODUCTS";
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("PRODUCT_ID");
                String name = rs.getString("PRODUCT_NAME");
                String color = rs.getString("COLOR");
                int price = rs.getInt("PRICE");
                String made = rs.getString("MADE_IN");


                    Products vo = new Products(id, name, color, price, made);

                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void productSelect() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("===== [Products Table] =====");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT");
            int sel = sc.nextInt();
            switch(sel) {
                case 1:
                    listProducts();
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
        listProducts();
        for(Products e : list) {
            System.out.print(e.getProductId() + " ");
            System.out.print(e.getProductName() + " ");
            System.out.print(e.getColor() + " ");
            System.out.print(e.getPrice() + " ");
            System.out.print(e.getMade() + " ");
            System.out.println();
        }
    }


    @Override
    public void insertList() {
        Scanner sc = new Scanner(System.in);


        System.out.print("상품명을 입력해주세요.");
        String productName = sc.next();
        System.out.print("색상을 입력해주세요.");
        String color = sc.next();
        System.out.print("가격을 입력해주세요.");
        int price = sc.nextInt();
        System.out.print("생산지를 입력해주세요.");
        String made = sc.next();

        String sql = "INSERT INTO PRODUCTS VALUES (SEQ_PRODUCTS.NEXTVAL, ?, ?, ?, ?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, productName  );
            pstmt.setString(2, color);
            pstmt.setInt(3, price);
            pstmt.setString(4, made );
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
        System.out.print("삭제할 목록을 입력 하세요 : ");
        String name = sc.next();
        String sql = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }
}
