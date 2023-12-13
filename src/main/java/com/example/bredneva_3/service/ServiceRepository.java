package com.example.bredneva_3.service;

import com.example.bredneva_3.model.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_ALL = "SELECT * FROM services;";
    static final String SELECT_BY_CLIENT = "SELECT * FROM services WHERE client_id=?";
    static final String INSERT = "INSERT INTO services(client_id, description, status) values (?,?,?)";
    static final String DELETE = "DELETE FROM services where service_id=?";
    static final String UPDATE = "UPDATE services SET description=?,status=? where service_id=?";
    public ServiceRepository() {
        this.dataBaseService = new DataBaseService();
    }
    public List<Service> getAllServices() {
        List<Service> services = new ArrayList<>();
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_ALL);
            while (resultSet.next()) {
                Service service = new Service(
                        resultSet.getInt("service_id"),
                        resultSet.getInt("client_id"),
                        resultSet.getString("description"),
                        resultSet.getString("status")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return services;
    }
    public List<Service> getAllServiceByClient(int clientId) {
        List<Service> services = new ArrayList<>();
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(SELECT_BY_CLIENT);
            statement.setInt(1, clientId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Service service = new Service(
                        resultSet.getInt("service_id"),
                        resultSet.getInt("client_id"),
                        resultSet.getString("description"),
                        resultSet.getString("status")
                );
                services.add(service);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return services;
    }
    public boolean addService(Service service) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setInt(1,service.getClient_id());
            statement.setString(2,service.getDescription());
            statement.setString(3,"Accept");
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean editCategory(Service service) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, service.getDescription());
            statement.setString(2,service.getStatus());
            statement.setInt(2, service.getService_id());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean deleteCategory(Integer id) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(DELETE);
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

}
