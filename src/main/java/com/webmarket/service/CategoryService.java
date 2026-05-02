package com.webmarket.service;

import com.webmarket.beans.Category;
import com.webmarket.dao.CategoryDao;

import java.util.List;

public class CategoryService {

    private final CategoryDao categoryDao = new CategoryDao();

    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }
}