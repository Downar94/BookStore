package com.bookshop.bookshop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookshop.bookshop.entity.Product;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ProductDao implements ProductDaoInterface {

    private EntityManager entityManager;

	@Autowired
	public ProductDao(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

    @Override
	@Transactional
	public void save(Product theProduct) {

		entityManager.merge(theProduct);
	}

	@Override
	public Product findByProductName(String productName) {

		TypedQuery<Product> theQuery = entityManager.createQuery("from Product where title=:pName", Product.class);
		theQuery.setParameter("pName", productName);

		Product theProduct = null;
		try {
			theProduct = theQuery.getSingleResult();
		} catch (Exception e) {
			theProduct = null;
		}

		return theProduct;
	}
	@Override
    public Product findById(Long id) {

		TypedQuery<Product> theQuery = entityManager.createQuery("from Product where id=:pid", Product.class);
		theQuery.setParameter("pid", id);		
		Product theProduct = null;
		try {
			theProduct = theQuery.getSingleResult();
		} catch (Exception e) {
			theProduct = null;
		}

		return theProduct;
	}

	@Override
	@Transactional
	public void deleteProduct(Product product) {

		this.entityManager.remove(product);
	}

	@Override
	public List<Product> findAll() {

		TypedQuery<Product> theQuery = entityManager.createQuery("from Product", Product.class);

		List<Product> products = theQuery.getResultList();

		return products;
	}

}
