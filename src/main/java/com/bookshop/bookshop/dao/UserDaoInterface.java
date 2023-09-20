package com.bookshop.bookshop.dao;

import java.util.List;

import com.bookshop.bookshop.entity.User;

public interface UserDaoInterface {

    void save(User theUser);

    User findByUserName(String userName);

    User findById(Long id);

    void deleteUser(User user);

    List<User> findAll();
    
}
