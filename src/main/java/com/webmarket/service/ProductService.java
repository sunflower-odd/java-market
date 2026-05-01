package com.webmarket.service;

import com.webmarket.beans.Product;
import com.webmarket.dao.ProductDao;
import java.math.BigDecimal;
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

    // создать товар
    public boolean createProduct(String name, String description, BigDecimal price, int categoryId) {

        Product p = new Product();
        p.setName(name);
        p.setDescription(description);
        p.setPrice(price);
        p.setCategoryId(categoryId);

        return productDao.save(p);
    }

    // удалить товар
    public boolean deleteProduct(int id) {
        return productDao.delete(id);
    }
}

