package com.bookshop.bookshop.service;

import java.util.List;

import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.OrderProducts;

public interface OrderServiceInterface {

    List<Order> listOrdersForUser();
    Order getOrderById(Long orderId);
    void addToCart(Long productId, Integer quantity);
    Float countTotalPrice(List<OrderProducts> details);
    void addOrderForUser();
    void makeOrder(Long orderId);
    void clearCart();
}
