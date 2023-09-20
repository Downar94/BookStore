package com.bookshop.bookshop.dao;

import java.util.List;

import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.User;

public interface OrderDaoInterface {

    void save(Order order); 
    
    Order findById(Long id);

    List<Order> findOrdersForUser(User user);

}
