package com.webmarket.service;

import com.webmarket.beans.Product;
import com.webmarket.dao.ProductDao;

import java.util.List;

public class ProductService {

    private ProductDao productDao = new ProductDao();

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public Product getById(int id) {
        return productDao.findById(id);
    }

    public List<Product> getByCategory(int categoryId) {
        return productDao.findByCategory(categoryId);
    }
}