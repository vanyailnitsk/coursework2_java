package com.example.bredneva_3.service;

import com.example.bredneva_3.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ProductRepository {
    private final DataBaseService dataBaseService;
    static final String SELECT_ALL = "SELECT * FROM products";
    static final String INSERT = "INSERT INTO products(name,description,price) values (?,?,?)";
    static final String DELETE = "DELETE FROM products where id=?";
    static final String UPDATE = "UPDATE products SET name=?,description=?,price=? where id=?";

    public ProductRepository() {
        this.dataBaseService = new DataBaseService();
    }
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try {
            ResultSet resultSet = dataBaseService.select(SELECT_ALL);
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        resultSet.getInt("price")
                );
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public boolean addProduct(Product product) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(INSERT);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getPrice());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean editProduct(Product product) {
        Connection conn = dataBaseService.getConnect();
        try {
            PreparedStatement statement = conn.prepareStatement(UPDATE);
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setInt(3, product.getPrice());
            statement.setInt(4, product.getProductId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
    public boolean deleteProduct(Integer id) {
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
