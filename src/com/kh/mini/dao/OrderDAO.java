package com.kh.mini.dao;

import com.kh.mini.util.Common;
import com.kh.mini.vo.OrderList;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/*
장바구니 표시 데이터 : 상품이름 , 고객아이디, 개수
 */
public class OrderDAO {
    public List<OrderList> listEmp() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<OrderList> list = new ArrayList<>();
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
        return list;
    }
}
