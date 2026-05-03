package com.webmarket.servlets;

import com.webmarket.service.ProductService;
import com.webmarket.service.CategoryService;
import com.webmarket.beans.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductService productService = new ProductService();
    private CategoryService categoryService = new CategoryService();

    // просмотр товаров
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String categoryIdParam = request.getParameter("categoryId");
        String productIdParam = request.getParameter("id");
        // для фильтрации по категориям
        request.setAttribute("categories", categoryService.getAllCategories());

        //  отдельный товар
        if (productIdParam != null) {

            int productId = Integer.parseInt(productIdParam);

            Product product = productService.getById(productId);

            request.setAttribute("product", product);
            request.getRequestDispatcher("product.jsp").forward(request, response);
            return;
        }

        List<Product> products;

        // фильтр по категории
        if (categoryIdParam != null && !categoryIdParam.isEmpty()) {
            int categoryId = Integer.parseInt(categoryIdParam);
            products = productService.getByCategory(categoryId);
        }
        // все товары
        else {
            products = productService.getAllProducts();
        }

        request.setAttribute("products", products);
        request.getRequestDispatcher("products.jsp").forward(request, response);
    }
}