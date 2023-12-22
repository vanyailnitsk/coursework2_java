package com.example.bredneva_3.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {
    private final String AUTH_ADMIN = "SELECT * FROM admin WHERE login = ? AND password = ?";
    private final DataBaseService dataBaseService = new DataBaseService();
    public boolean checkAdminAuth(String login,String password) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(AUTH_ADMIN);
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
