package com.webmarket.service;

import com.webmarket.beans.Cart;
import com.webmarket.dao.CartDao;

import java.util.List;

public class CartService {

    private CartDao cartDao = new CartDao();

    public boolean addToCart(int userId, int productId, int quantity) {

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setProductId(productId);
        cart.setQuantity(quantity);

        return cartDao.addToCart(cart);
    }

    public List<Cart> getUserCart(int userId) {
        return cartDao.getCartByUserId(userId);
    }

    public boolean removeItem(int cartId) {
        return cartDao.removeItem(cartId);
    }

    public boolean clearCart(int userId) {
        return cartDao.clearCart(userId);
    }
}