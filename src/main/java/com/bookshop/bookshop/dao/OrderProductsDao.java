package com.bookshop.bookshop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.OrderProducts;
import com.bookshop.bookshop.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class OrderProductsDao implements OrderProductsDaoInterface {

    private EntityManager entityManager;

	@Autowired
	public OrderProductsDao(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

	@Override
	@Transactional
	public void save(OrderProducts details) {

		entityManager.merge(details);
	}

	@Override
    public List<OrderProducts> findDetailsForOrder(Order order) {

		TypedQuery<OrderProducts> theQuery = entityManager.createQuery("from OrderProducts where order=:oid ", OrderProducts.class);
		theQuery.setParameter("oid", order);	

		List<OrderProducts> ordersDetails = theQuery.getResultList();

		return ordersDetails;
	}

	@Override
    public List<OrderProducts> findDetailsForProduct(Product product) {

		TypedQuery<OrderProducts> theQuery = entityManager.createQuery("from OrderProducts where product=:pid ", OrderProducts.class);
		theQuery.setParameter("pid", product);	

		List<OrderProducts> ordersDetails = theQuery.getResultList();

		return ordersDetails;
	}

	@Override
	@Transactional
	public void deleteDetails(OrderProducts details) {

		entityManager.remove(details);
	}

}
