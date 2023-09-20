package com.bookshop.bookshop.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bookshop.bookshop.entity.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class ReviewDao implements ReviewDaoInterface {

    private EntityManager entityManager;

	@Autowired
	public ReviewDao(EntityManager theEntityManager) {
		this.entityManager = theEntityManager;
	}

    @Override
	@Transactional
	public void save(Review review) {

		entityManager.merge(review);
	}

	@Override
    public Review findById(Long id) {

		TypedQuery<Review> theQuery = entityManager.createQuery("from Review where id=:rid", Review.class);
		theQuery.setParameter("rid", id);		
		Review theReview = null;
		try {
			theReview = theQuery.getSingleResult();
		} catch (Exception e) {
			theReview = null;
		}

		return theReview;
	}

	@Override
	@Transactional
	public void deleteReview(Review review) {

		this.entityManager.remove(review);
	}

	@Override
	public List<Review> findAll() {

		TypedQuery<Review> theQuery = entityManager.createQuery("from Review ", Review.class);

		List<Review> reviews = theQuery.getResultList();

		return reviews;
	}
	@Override
	public List<Review> findByProductId(Long productId) {

		TypedQuery<Review> theQuery = entityManager.createQuery("from Review where productId=:pid", Review.class);
		theQuery.setParameter("pid", productId);		

		List<Review> reviews = theQuery.getResultList();

		return reviews;
	}

}
