package com.example.bredneva_3.service;

import java.sql.*;

public class DataBaseService {

    public Connection getConnect(){
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        final String url = "jdbc:postgresql://217.107.219.154:49307/bonch_2105093?characterEncoding=UTF-8";
        final String user = "bonch_2105093";
        final String password = "+y253pnT9lw=";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public ResultSet select(String sql){
        Statement statement = null;
        try {
            statement = getConnect().createStatement();
            ResultSet rs = statement.executeQuery(sql);
            return rs;
        } catch (SQLException throwables) {
            System.out.println(throwables.getMessage());
            return null;
        }
    }
}
