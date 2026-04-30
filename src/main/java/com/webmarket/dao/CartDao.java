package com.webmarket.dao;

import com.webmarket.beans.Cart;
import com.webmarket.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDao {

    // добавить в корзину
    public boolean addToCart(Cart cart) {
        String sql = "INSERT INTO cart (user_id, product_id, quantity) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cart.getUserId());
            stmt.setInt(2, cart.getProductId());
            stmt.setInt(3, cart.getQuantity());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // получать корзину по id пользователя
    public List<Cart> getCartByUserId(int userId) {
        List<Cart> cartItems = new ArrayList<>();

        String sql = "SELECT * FROM cart WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Cart cart = new Cart();
                cart.setId(rs.getInt("id"));
                cart.setUserId(rs.getInt("user_id"));
                cart.setProductId(rs.getInt("product_id"));
                cart.setQuantity(rs.getInt("quantity"));

                cartItems.add(cart);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cartItems;
    }

    // обновить количество товара в корзине
    public boolean updateQuantity(int cartId, int quantity) {
        String sql = "UPDATE cart SET quantity = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setInt(2, cartId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // удалить товар из корзины
    public boolean removeItem(int cartId) {
        String sql = "DELETE FROM cart WHERE id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cartId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // очистить корзину
    public boolean clearCart(int userId) {
        String sql = "DELETE FROM cart WHERE user_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}