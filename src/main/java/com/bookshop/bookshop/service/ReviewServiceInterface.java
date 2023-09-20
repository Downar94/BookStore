package com.bookshop.bookshop.service;

import java.util.List;

import com.bookshop.bookshop.entity.Review;

public interface ReviewServiceInterface {
    Review getByReviewId(Long id);
    List<Review> listReviewsForProduct(Long id);
    List<Review> listReviewsForUser();
    Review updateReview(Review newReview, Long reviewId);
    void save(Review review, Long productId);
    void deleteReview(Long reviewId);
    void deleteReviewByAdmin(Long reviewId);
    Review updateReviewByAdmin(Review newReview, Long reviewId);
    List<Review> listReviewsForUserByAdmin(Long id);
}
