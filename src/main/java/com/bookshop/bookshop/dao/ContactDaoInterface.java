package com.bookshop.bookshop.dao;

import com.bookshop.bookshop.entity.Contact;

public interface ContactDaoInterface {

    void save(Contact contact);

    Contact findById(Long id);
  
}
