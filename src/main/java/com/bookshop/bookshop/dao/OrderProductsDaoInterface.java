package com.bookshop.bookshop.dao;

import java.util.List;

import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.OrderProducts;
import com.bookshop.bookshop.entity.Product;

public interface OrderProductsDaoInterface {

    List<OrderProducts> findDetailsForOrder(Order order);
    void save(OrderProducts details);
    void deleteDetails(OrderProducts details);
    List<OrderProducts> findDetailsForProduct(Product product);

}
