package com.bookshop.bookshop.dao;

import java.util.List;

import com.bookshop.bookshop.entity.Review;

public interface ReviewDaoInterface {

    void save(Review review);

    Review findById(Long id);

    void deleteReview(Review review);

    List<Review> findAll();

    List<Review> findByProductId(Long productId);
  
}
