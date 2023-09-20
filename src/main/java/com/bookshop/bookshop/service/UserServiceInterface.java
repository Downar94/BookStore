package com.bookshop.bookshop.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.entity.guest.WebGuest;

public interface UserServiceInterface extends UserDetailsService {

    public User findByUserName(String userName);
	void save(WebGuest webGuest);
    public User getByUserId(Long id);
    User updateUser(User user);
    void blockUser(Long id);
    void suspendAccount();
    List<User> listUsers();
    User updateUserByAdmin(User user, Long id);

}
