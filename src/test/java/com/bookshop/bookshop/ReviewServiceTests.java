package com.bookshop.bookshop;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bookshop.bookshop.dao.ProductDao;
import com.bookshop.bookshop.dao.ReviewDao;
import com.bookshop.bookshop.dao.UserDao;
import com.bookshop.bookshop.service.ProductService;
import com.bookshop.bookshop.service.ReviewService;
import com.bookshop.bookshop.service.UserService;
import com.bookshop.bookshop.entity.Product;
import com.bookshop.bookshop.entity.Review;
import com.bookshop.bookshop.entity.User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

@ExtendWith(SpringExtension.class)
public class ReviewServiceTests {
    
    @Mock
    private ProductDao productDao;
    @Mock
    private ReviewDao reviewDao;
    @Mock
    private UserDao userDao;

    @InjectMocks
    private ProductService productService;
    @InjectMocks
    private ReviewService reviewService;  
    @InjectMocks
    private UserService userService;  
/* 
    @Test
    public void listReviewsForProductTest(){
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30, (float) 30);
        Review review = new Review(4, "nice book");
        Review review2 = new Review(2, "tragedy!!!");
        product.setReviews(Arrays.asList(review, review2));
        when(productDao.findById(1L)).thenReturn(product);

        List<Review> reviews = reviewService.listReviewsForProduct(1L);
        
        assertEquals(4, reviews.get(0).getRating()); 
        assertEquals(2, reviews.get(1).getRating());

    }*/
 /* 
    @Test
    public void listReviewsForUserTest(){

        Authentication authentication = Mockito.mock(Authentication.class);
        // Mockito.whens() for your authorization object
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        User user = new User("harrypotter123", "haslo123", "Harry","Potter","harry_potter@email.com");
        String username = user.getUserName();
        Review review = new Review(4, "nice book");
        Review review2 = new Review(2, "tragedy!!!");
        user.setReviews(Arrays.asList(review, review2));
        when(userDao.findByUserName(username)).thenReturn(user);

        List<Review> reviews = reviewService.listReviewsForUser();
        
        assertEquals(4, reviews.get(0).getRating()); 
        assertEquals(2, reviews.get(1).getRating());
    }
*/
  /*   @Test
    public void getByReviewIdTest(){
        Review review = new Review(4, "nice book");
        when(reviewDao.findById(1L)).thenReturn(review);

        Review resultReview = reviewService.getByReviewId(1L);
        
        assertEquals(4, resultReview.getRating()); 
        assertEquals("nice book", resultReview.getDescription());
    }*/ 
/* 
    @Test
    public void saveTest(){
        Review review = new Review(4, "nice book");
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30);
        product.setReviews(Arrays.asList(review));
        product.setId(1L);

        when(reviewDao.findByProductId(1L)).thenReturn(Arrays.asList(review));
        when(productDao.findById(1L)).thenReturn(product);

        reviewService.save(review, 1L, 1L);

        verify(reviewDao, times(1)).save(review);
        verify(productDao, times(1)).save(product);
        
    }
  
    @Test
    public void updateReviewTest(){
        Review review = new Review(4, "nice book");
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30);
        product.setReviews(Arrays.asList(review));
        product.setId(1L);
        review.setUserId(1L);
        review.setProductId(1L);

        when(reviewDao.findByProductId(1L)).thenReturn(Arrays.asList(review));
        when(productDao.findById(1L)).thenReturn(product);
        when(reviewDao.findById(1L)).thenReturn(review);

        Review newReview = new Review(2, "tragedy!!!");
        Review resultReview = reviewService.updateReview(newReview, 1L, 1L);

        assertEquals(2, resultReview.getRating()); 
        assertEquals("tragedy!!!", resultReview.getDescription());
        
    }

    @Test
    public void deleteReviewTest(){
        Review review = new Review(4, "nice book");
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30);
        product.setReviews(Arrays.asList(review));
        product.setId(1L);
        review.setUserId(1L);
        review.setProductId(1L);

        when(reviewDao.findByProductId(1L)).thenReturn(Arrays.asList(review));
        when(productDao.findById(1L)).thenReturn(product);
        when(reviewDao.findById(1L)).thenReturn(review);

        reviewService.deleteReview(1L, 1L);

        verify(reviewDao, times(1)).deleteReview(review);        
        
    }
*//* 
    @Test
    public void deleteReviewByAdminTest(){
        Review review = new Review(4, "nice book");
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30, (float) 30);
        product.setReviews(Arrays.asList(review));
        product.setId(1L);
        review.setUserId(1L);
        review.setProductId(1L);

        when(reviewDao.findByProductId(1L)).thenReturn(Arrays.asList(review));
        when(productDao.findById(1L)).thenReturn(product);
        when(reviewDao.findById(1L)).thenReturn(review);

        reviewService.deleteReviewByAdmin(1L);

        verify(reviewDao, times(1)).deleteReview(review);        
        
    }
    
    @Test
    public void updateReviewByAdminTest(){
        Review review = new Review(4, "nice book");
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30, (float) 30);
        product.setReviews(Arrays.asList(review));
        product.setId(1L);
        review.setUserId(1L);
        review.setProductId(1L);

        when(reviewDao.findByProductId(1L)).thenReturn(Arrays.asList(review));
        when(productDao.findById(1L)).thenReturn(product);
        when(reviewDao.findById(1L)).thenReturn(review);

        Review newReview = new Review(2, "tragedy!!!");
        Review resultReview = reviewService.updateReviewByAdmin(newReview, 1L);

        assertEquals(2, resultReview.getRating()); 
        assertEquals("tragedy!!!", resultReview.getDescription());
        
    }*/
}
