package com.kh.mini.dao;

import com.kh.mini.util.Common;
import com.kh.mini.vo.Customer;
import com.kh.mini.vo.Products;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerDAO implements DAO {
    Connection conn = null;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    List<Customer> list = new ArrayList<>();

    public void listCustomer() {
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM Customer";
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String userId = rs.getString("USER_ID");
                String userPwd = rs.getString("USER_PWD");
                String userName = rs.getString("USER_NAME");
                String phone = rs.getString("PHONE");
                String eMail = rs.getString("EMAIL");
                String address = rs.getString("ADDRESS");


                Customer vo = new Customer(userId, userPwd, userName, phone, eMail, address);

                list.add(vo);
            }
            Common.close(rs);
            Common.close(stmt);
            Common.close(conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerSelect() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("===== [Customer Table] =====");
            System.out.println("메뉴를 선택 하세요 : ");
            System.out.println("[1]SELECT, [2]INSERT, [3]UPDATE, [4]DELETE, [5]EXIT");
            int sel = sc.nextInt();
            switch (sel) {
                case 1:
                    listCustomer();
                    selectList();
                    break;
                case 2:
                    insertList();
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


    @Override
    public void selectList() {
        for (Customer e : list) {
            System.out.print(e.getUserId() + " ");
            System.out.print(e.getUserPwd() + " ");
            System.out.print(e.getUserName() + " ");
            System.out.print(e.getPhone() + " ");
            System.out.print(e.geteMail() + " ");
            System.out.print(e.getAddress() + " ");
            System.out.println();
        }
    }


    @Override
    public void insertList() {
        Scanner sc = new Scanner(System.in);


        System.out.print("ID를 입력해주세요.");
        String userId = sc.next();
        System.out.print("비밀번호를 입력해주세요.");
        String userPwd = sc.next();
        System.out.print("성함을 입력해주세요.");
        String userName = sc.next();
        System.out.print("전화번호를 입력해주세요.");
        String phone = sc.next();
        System.out.print("이메일을 입력해주세요.");
        String eMail = sc.next();
        System.out.print("주소를 입력해주세요.");
        String address = sc.next();

        String sql = "INSERT INTO Customer VALUES (?, ?, ?, ?, ?, ?)";

        try {
            conn = Common.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userId);
            pstmt.setString(2, userPwd);
            pstmt.setString(3, userName);
            pstmt.setString(4, phone);
            pstmt.setString(5, eMail);
            pstmt.setString(6, address);
            pstmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        Common.close(pstmt);
        Common.close(conn);
    }


    @Override
    public void deleteList() {

    }

    public boolean login(String ID, String Password) {
        conn = Common.getConnection();
        try {
            String SQL = "SELECT USER_ID, USER_PWD FROM CUSTOMER WHERE USER_ID = ? AND USER_PWD = ?";
            pstmt = conn.prepareStatement(SQL);
            pstmt.setString(1, ID);
            pstmt.setString(2, Password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                if (rs.getString("USER_PWD").equals(Password) && rs.getString("USER_ID").equals(ID))
                    return true;
                else
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}







