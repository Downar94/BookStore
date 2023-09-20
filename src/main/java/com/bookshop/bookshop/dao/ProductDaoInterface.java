package com.bookshop.bookshop.dao;

import java.util.List;

import com.bookshop.bookshop.entity.Product;

public interface ProductDaoInterface {

    void save(Product theProduct);

    Product findByProductName(String productName);

    Product findById(Long id);

    void deleteProduct(Product product);

    List<Product> findAll();

    
}
