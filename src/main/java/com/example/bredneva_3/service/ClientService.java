package com.example.bredneva_3.service;

import com.example.bredneva_3.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientService {
    private final DataBaseService dataBaseService;
    static final String SELECT_BY_ID = "SELECT * FROM clients where client_id=?";
    private final String AUTH_CLIENT = "SELECT client_id FROM clients WHERE login = ? AND password = ?";
    private final String SELECT_CLIENT_EXIST = "SELECT COUNT(*) from clients where login=?";
    private final String REGISTER = "INSERT INTO clients (name, contact,login, password)" +
            " VALUES (?,?,?,?) RETURNING client_id";

    public ClientService() {
        this.dataBaseService = new DataBaseService();
    }

    public int getUserIdByUsernameAndPassword(String username,String password) {
        Connection conn = dataBaseService.getConnect();
        int userId=-1;
        try {
            PreparedStatement statement = conn.prepareStatement(AUTH_CLIENT);
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userId;
    }
    public int registerClient(String name,String contact,String login, String password) {
        Connection conn = dataBaseService.getConnect();
        int userId=-1;
        try {
            PreparedStatement statement = conn.prepareStatement(REGISTER);
            statement.setString(1, name);
            statement.setString(2,contact);
            statement.setString(3, login);
            statement.setString(4, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("client_id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userId;
    }

    public boolean isClientExistsByLogin(String login) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_CLIENT_EXIST);
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("count") != 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    public Client getClientById(int id) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_ID);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Client(
                        resultSet.getInt("client_id"),
                        resultSet.getString("name"),
                        resultSet.getString("contact"),
                        resultSet.getString("login"),
                        resultSet.getString("password")
                );
            }
            else {
                throw new IllegalArgumentException("No client with id "+id);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
