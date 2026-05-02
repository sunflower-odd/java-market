package com.webmarket.dao;

import com.webmarket.beans.Product;
import com.webmarket.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

    // единый mapper для ResultSet
    private Product map(ResultSet rs) throws SQLException {
        Product p = new Product();

        p.setId(rs.getInt("id"));
        p.setName(rs.getString("name"));
        p.setDescription(rs.getString("description"));
        p.setPrice(rs.getBigDecimal("price"));
        p.setCategoryId(rs.getInt("category_id"));
        p.setCategoryName(rs.getString("category_name"));

        return p;
    }

    // получить все товары
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        // CHANGED: убрали лишнюю ; в конце + оставили JOIN
        String sql =
                "SELECT p.id, p.name, p.description, p.price, p.category_id, c.name AS category_name " +
                        "FROM products p LEFT JOIN categories c ON p.category_id = c.id";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // получить товар по id
    public Product findById(int id) {

        String sql =
                "SELECT p.id, p.name, p.description, p.price, p.category_id, c.name AS category_name " +
                        "FROM products p LEFT JOIN categories c ON p.category_id = c.id WHERE p.id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // CHANGED: используем общий mapper
                return map(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // получить товары по категории
    public List<Product> findByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();

        String sql =
                "SELECT p.id, p.name, p.description, p.price, p.category_id, c.name AS category_name " +
                        "FROM products p LEFT JOIN categories c ON p.category_id = c.id WHERE p.category_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoryId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                products.add(map(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    // добавить товар
    public boolean save(Product product) {
        String sql = "INSERT INTO products (name, description, price, category_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setBigDecimal(3, product.getPrice());

            if (product.getCategoryId() != null) {
                stmt.setInt(4, product.getCategoryId());
            } else {
                stmt.setNull(4, java.sql.Types.INTEGER);
            }

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // удалить товар
    public boolean delete(int id) {
        String sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}