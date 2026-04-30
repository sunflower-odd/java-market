package com.webmarket.dao;

import com.webmarket.beans.Delivery;
import com.webmarket.util.DBConnection;

import java.sql.*;

public class DeliveryDao {

    // создать доставку
    public boolean createDelivery(Delivery delivery) {
        String sql = "INSERT INTO deliveries " +
                "(order_id, delivery_address, city, postal_code, status, delivery_date) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, delivery.getOrderId());
            stmt.setString(2, delivery.getDeliveryAddress());
            stmt.setString(3, delivery.getCity());
            stmt.setString(4, delivery.getPostalCode());
            stmt.setString(5, delivery.getStatus());
            stmt.setTimestamp(6, delivery.getDeliveryDate());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // получить доставку по id заказа
    public Delivery getByOrderId(int orderId) {
        String sql = "SELECT * FROM deliveries WHERE order_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Delivery d = new Delivery();
                d.setId(rs.getInt("id"));
                d.setOrderId(rs.getInt("order_id"));
                d.setDeliveryAddress(rs.getString("delivery_address"));
                d.setCity(rs.getString("city"));
                d.setPostalCode(rs.getString("postal_code"));
                d.setStatus(rs.getString("status"));
                d.setDeliveryDate(rs.getTimestamp("delivery_date"));
                d.setCreatedAt(rs.getTimestamp("created_at"));

                return d;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // обновить статус
    public boolean updateStatus(int deliveryId, String status) {
        String sql = "UPDATE deliveries SET status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, deliveryId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}