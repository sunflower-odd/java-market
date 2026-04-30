package com.webmarket.beans;

import java.sql.Timestamp;

public class Delivery {
    private int id;
    private int orderId;
    private String deliveryAddress;
    private String city;
    private String postalCode;
    private String status;
    private Timestamp deliveryDate;
    private Timestamp createdAt;

    public Delivery() {}

    public Delivery(int id, int orderId, String deliveryAddress, String city,
                    String postalCode, String status,
                    Timestamp deliveryDate, Timestamp createdAt) {
        this.id = id;
        this.orderId = orderId;
        this.deliveryAddress = deliveryAddress;
        this.city = city;
        this.postalCode = postalCode;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.createdAt = createdAt;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public String getDeliveryAddress() { return deliveryAddress; }
    public void setDeliveryAddress(String deliveryAddress) { this.deliveryAddress = deliveryAddress; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPostalCode() { return postalCode; }
    public void setPostalCode(String postalCode) { this.postalCode = postalCode; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(Timestamp deliveryDate) { this.deliveryDate = deliveryDate; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}