package com.bookshop.bookshop.service;

import java.util.List;

import com.bookshop.bookshop.entity.Product;

public interface ProductServiceInterface {
    public Product findByProductName(String productName);

    void save(Product product);
    public Product getByProductId(Long id);
    public Product loadProductByProductname(String productName);
    Product updateProduct(Product product, Long id);
    void deleteProduct(Long id);
    List<Product> listProducts();
}
