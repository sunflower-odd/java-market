package com.webmarket.service;

import com.webmarket.beans.Cart;
import com.webmarket.beans.Order;
import com.webmarket.beans.OrderItem;
import com.webmarket.dao.CartDao;
import com.webmarket.dao.OrderDao;
import com.webmarket.dao.OrderItemDao;

import java.util.List;
import com.webmarket.dao.ProductDao;
import com.webmarket.beans.Product;

public class OrderService {

    private OrderDao orderDao = new OrderDao();
    private OrderItemDao orderItemDao = new OrderItemDao();
    private CartDao cartDao = new CartDao();
    private ProductDao productDao = new ProductDao();

    // оформление заказа
    public int createOrder(int userId) {

        // создаём заказ
        Order order = new Order();
        order.setUserId(userId);

        int orderId = orderDao.createOrder(order);

        if (orderId == -1) {
            return -1;
        }

        // находим корзину пользователя
        List<Cart> cartItems = cartDao.getCartByUserId(userId);

        // заполняем order_items
        for (Cart item : cartItems) {

            Product product = productDao.findById(item.getProductId());

            OrderItem orderItem = new OrderItem();
            orderItem.setOrderId(orderId);
            orderItem.setProductId(item.getProductId());
            orderItem.setQuantity(item.getQuantity());

            orderItem.setPrice(product.getPrice());

            orderItemDao.addOrderItem(orderItem);
        }

        // очищаем корзину
        cartDao.clearCart(userId);

        return orderId;
    }
}