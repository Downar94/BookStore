package com.bookshop.bookshop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookshop.bookshop.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class UserDao implements UserDaoInterface {

    private EntityManager entityManager;

	@Autowired
	public UserDao(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

    @Override
	@Transactional
	public void save(User theUser) {

		entityManager.merge(theUser);
	}

	@Override
	public User findByUserName(String theUserName) {

		TypedQuery<User> theQuery = entityManager.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);

		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}
	@Override
    public User findById(Long id) {

		TypedQuery<User> theQuery = entityManager.createQuery("from User where id=:uid", User.class);
		theQuery.setParameter("uid", id);		
		User theUser = null;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	@Transactional
	public void deleteUser(User user) {

		this.entityManager.remove(user);
		
	}

	@Override
	public List<User> findAll() {

		TypedQuery<User> theQuery = entityManager.createQuery("from User", User.class);

		List<User> users = theQuery.getResultList();

		return users;
	}

	

}
