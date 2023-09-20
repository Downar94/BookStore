package com.bookshop.bookshop.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bookshop.bookshop.dao.ProductDaoInterface;
import com.bookshop.bookshop.dao.ReviewDaoInterface;
import com.bookshop.bookshop.dao.UserDaoInterface;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.exception.NotAllowedResourcesException;
import com.bookshop.bookshop.exception.ProductNotFoundException;
import com.bookshop.bookshop.exception.ReviewNotFoundException;
import com.bookshop.bookshop.entity.Product;
import com.bookshop.bookshop.entity.Review;

import java.util.List;

@Service
public class ReviewService implements ReviewServiceInterface {
	private ReviewDaoInterface reviewDao;
	private UserDaoInterface userDao;
	private ProductDaoInterface productDao;
	
	@Autowired
	public ReviewService(ReviewDaoInterface reviewDao, UserDaoInterface userDao, ProductDaoInterface productDao) {
		this.reviewDao = reviewDao;
		this.userDao = userDao;
		this.productDao = productDao;
	}

	@Override
    public Review getByReviewId(Long id) {
		Review review = reviewDao.findById(id);
		if (review == null) {
			throw new ReviewNotFoundException(id);
		}
        return review;
    }
	@Override
    public List<Review> listReviewsForProduct(Long id) {
		Product product = productDao.findById(id);
		if (product == null) {
			throw new ProductNotFoundException(id);
		} 
		return (List<Review>) product.getReviews();
	}
	@Override
    public List<Review> listReviewsForUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		return (List<Review>) user.getReviews();
	}

	@Override
    public Review updateReview(Review newReview, Long reviewId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		Long userId = user.getId();
		Review updated = reviewDao.findById(reviewId);
		List<Integer> ratings = new ArrayList<Integer>();
		Integer averageRating;

		if(updated == null) {
			throw new ReviewNotFoundException(reviewId);
		}
		else if (updated.getUserId() != userId) {
			throw new NotAllowedResourcesException();
		} 
		Product product = productDao.findById(updated.getProductId()); 

		updated.setRating(newReview.getRating());
		updated.setDescription(newReview.getDescription());
		reviewDao.save(updated);

		reviewDao.findByProductId(product.getId()).forEach((rev) -> ratings.add(rev.getRating()));
		averageRating = ratings.stream().mapToInt(Integer::intValue).sum() / ratings.size();
		product.setRating(averageRating);
		productDao.save(product);

		return updated;
	}

	@Override
	public void save(Review review, Long productId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		Long userId = user.getId();
		Review newReview = new Review();
		List<Integer> ratings = new ArrayList<Integer>();
		Integer averageRating;
		Product product = productDao.findById(productId);
		if (product == null) {
			throw new ProductNotFoundException(productId);
		} 
		newReview.setProductId(productId);
		newReview.setUserId(userId);
		newReview.setRating(review.getRating());
		newReview.setDescription(review.getDescription());
		reviewDao.save(newReview);

		reviewDao.findByProductId(productId).forEach((rev) -> ratings.add(rev.getRating()));
		averageRating = ratings.stream().mapToInt(Integer::intValue).sum() / ratings.size();
		product.setRating(averageRating);
		productDao.save(product);

	}

	@Override
    public void deleteReview(Long reviewId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		Long userId = user.getId();
		Review deleted = reviewDao.findById(reviewId);
		List<Integer> ratings = new ArrayList<Integer>();
		Integer averageRating;

		if (deleted == null) {
			throw new ReviewNotFoundException(reviewId);
		} else if (deleted.getUserId() != userId) {
			throw new NotAllowedResourcesException();
		}
		
		Product product = productDao.findById(deleted.getProductId()); 

		Collection<Review> productReviews = product.getReviews();
		Collection<Review> userReviews = user.getReviews();

		Collection<Review> newProductReviews = new ArrayList<>();
		Collection<Review> newUserReviews = new ArrayList<>();
			
		for (Review rev : productReviews) 
		{
			if (rev.getId() != reviewId)
			{

				newProductReviews.add(rev);
			}
		}

		for (Review rev : userReviews) 
		{
			if (rev.getId() != reviewId)
			{
				newUserReviews.add(rev);
			}
		}

		product.setReviews(newProductReviews);
		user.setReviews(newUserReviews);
		productDao.save(product);
		userDao.save(user);
		reviewDao.deleteReview(deleted);

		reviewDao.findByProductId(product.getId()).forEach((rev) -> ratings.add(rev.getRating()));

		if(ratings.size() == 0)
		{
			product.setRating(0);
		}
		else
		{
			averageRating = ratings.stream().mapToInt(Integer::intValue).sum() / ratings.size();
			product.setRating(averageRating);
		}
		
		productDao.save(product);
	}

	@Override
    public void deleteReviewByAdmin(Long reviewId) {
		Review deleted = reviewDao.findById(reviewId);
		List<Integer> ratings = new ArrayList<Integer>();
		Integer averageRating;

		if (deleted == null) {
			throw new ReviewNotFoundException(reviewId);
		} 
		Product product = productDao.findById(deleted.getProductId());

		Collection<Review> productReviews = product.getReviews();
		User user = userDao.findById(deleted.getUserId());
		Collection<Review> userReviews = user.getReviews();

		Collection<Review> newProductReviews = new ArrayList<>();
		Collection<Review> newUserReviews = new ArrayList<>();
			
		for (Review rev : productReviews) 
		{
			if (rev.getId() != reviewId)
			{

				newProductReviews.add(rev);
			}
		}

		for (Review rev : userReviews) 
		{
			if (rev.getId() != reviewId)
			{
				newUserReviews.add(rev);
			}
		}

		product.setReviews(newProductReviews);
		user.setReviews(newUserReviews);
		productDao.save(product);
		userDao.save(user);
		reviewDao.deleteReview(deleted);

		reviewDao.findByProductId(product.getId()).forEach((rev) -> ratings.add(rev.getRating()));

		if(ratings.size() == 0)
		{
			product.setRating(0);
		}
		else
		{
			averageRating = ratings.stream().mapToInt(Integer::intValue).sum() / ratings.size();
			product.setRating(averageRating);
		}

		productDao.save(product);
	}

	@Override
    public Review updateReviewByAdmin(Review newReview, Long reviewId) {
		Review updated = reviewDao.findById(reviewId);
		List<Integer> ratings = new ArrayList<Integer>();
		Integer averageRating;

		if(updated == null) {
			throw new ReviewNotFoundException(reviewId);
		}
		Product product = productDao.findById(updated.getProductId()); 

		updated.setRating(newReview.getRating());
		updated.setDescription(newReview.getDescription());
		reviewDao.save(updated);

		reviewDao.findByProductId(product.getId()).forEach((rev) -> ratings.add(rev.getRating()));
		averageRating = ratings.stream().mapToInt(Integer::intValue).sum() / ratings.size();
		product.setRating(averageRating);
		productDao.save(product);

		return updated;
	}

	@Override
    public List<Review> listReviewsForUserByAdmin(Long id) {
		User user = userDao.findById(id);
		return (List<Review>) user.getReviews();
	}

	

}
