package com.webmarket.service;

import com.webmarket.beans.Delivery;
import com.webmarket.dao.DeliveryDao;

public class DeliveryService {

    private DeliveryDao deliveryDao = new DeliveryDao();

    // создание доставки после заказа
    public boolean createDelivery(int orderId, String address, String city, String postalCode) {

        Delivery delivery = new Delivery();

        delivery.setOrderId(orderId);
        delivery.setDeliveryAddress(address);
        delivery.setCity(city);
        delivery.setPostalCode(postalCode);

        // начальный статус
        delivery.setStatus("PENDING");

        // дата доставки пока не известна
        delivery.setDeliveryDate(null);

        return deliveryDao.createDelivery(delivery);
    }

    // обновление статуса
    public boolean updateStatus(int deliveryId, String status) {
        return deliveryDao.updateStatus(deliveryId, status);
    }

    // получить доставку по заказу
    public Delivery getByOrderId(int orderId) {
        return deliveryDao.getByOrderId(orderId);
    }
}