package com.bookshop.bookshop.service;

import com.bookshop.bookshop.entity.Contact;

public interface ContactServiceInterface {

    Contact updateContactForUser(Contact contact);

    Contact getContactForUser();

}
