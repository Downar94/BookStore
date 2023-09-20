package com.bookshop.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bookshop.bookshop.dao.ContactDaoInterface;
import com.bookshop.bookshop.dao.UserDaoInterface;
import com.bookshop.bookshop.entity.Contact;
import com.bookshop.bookshop.entity.User;

@Service
public class ContactService implements ContactServiceInterface {
	private ContactDaoInterface contactDao;
	private UserDaoInterface userDao;

	@Autowired
	public ContactService(ContactDaoInterface contactDao, UserDaoInterface userDao) {
		this.contactDao = contactDao;
		this.userDao = userDao;
	}

	@Override
    public Contact getContactForUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		Contact contact = user.getContact();
        return contact;
    }

	@Override
    public Contact updateContactForUser(Contact contact) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		Contact updated = user.getContact();

		if(contact.getStreetName() != null) updated.setStreetName(contact.getStreetName());
		if(contact.getCity() != null) updated.setCity(contact.getCity());
		if(contact.getCountry() != null) updated.setCountry(contact.getCountry());
		if (contact.getPostCode() != null) updated.setPostCode(contact.getPostCode());
		
		contactDao.save(updated);

		return updated;
	}

}
