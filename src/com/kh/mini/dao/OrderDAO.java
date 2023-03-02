package com.kh.mini.dao;

import com.kh.mini.util.Common;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public List<OrderList> listEmp() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<OrderList> list = new ArrayList<>();
        try {
            conn = Common.getConnection();
            stmt = conn.createStatement();
            String query = "SELECT * FROM PRODUCTS";
            rs = stmt.executeQuery(query);

            while(rs.next()) {
                int id = rs.getInt("PRODUCT_ID");
                String name= rs.getString("PRODUCT_NAME");
                String color = rs.getString("COLOR");
                int price = rs.getInt("PRICE");
                String made = rs.getString("MADE_IN");


                OrderList vo = new OrderList(id, name, color, price, made);

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
