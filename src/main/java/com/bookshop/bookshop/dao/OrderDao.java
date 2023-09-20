package com.bookshop.bookshop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.User;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class OrderDao implements OrderDaoInterface {

    private EntityManager entityManager;

	@Autowired
	public OrderDao(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

    @Override
	@Transactional
	public void save(Order order) {

		entityManager.merge(order);
	}

	@Override
    public Order findById(Long id) {

		TypedQuery<Order> theQuery = entityManager.createQuery("from Order where id=:oid", Order.class);
		theQuery.setParameter("oid", id);		
		Order order = null;
		try {
			order = theQuery.getSingleResult();
		} catch (Exception e) {
			order = null;
		}

		return order;
	}

	@Override
    public List<Order> findOrdersForUser(User user) {

		TypedQuery<Order> theQuery = entityManager.createQuery("from Order where user=:uid ", Order.class);
		theQuery.setParameter("uid", user);	

		List<Order> orders = theQuery.getResultList();

		return orders;
	}

}
