package com.bookshop.bookshop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookshop.bookshop.entity.Contact;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ContactDao implements ContactDaoInterface {

    private EntityManager entityManager;

	@Autowired
	public ContactDao(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

    @Override
	@Transactional
	public void save(Contact contact) {

		entityManager.merge(contact);
	}

	@Override
    public Contact findById(Long id) {
		
		TypedQuery<Contact> theQuery = entityManager.createQuery("from Contact where id=:cid", Contact.class);
		theQuery.setParameter("cid", id);		
		Contact contact = null;
		try {
			contact = theQuery.getSingleResult();
		} catch (Exception e) {
			contact = null;
		}

		return contact;
	}


}
