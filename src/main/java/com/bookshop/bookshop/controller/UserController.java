package com.bookshop.bookshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bookshop.bookshop.entity.Contact;
import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.Product;
import com.bookshop.bookshop.entity.Review;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.service.ContactServiceInterface;
import com.bookshop.bookshop.service.OrderServiceInterface;
import com.bookshop.bookshop.service.ProductServiceInterface;
import com.bookshop.bookshop.service.ReviewServiceInterface;
import com.bookshop.bookshop.service.UserServiceInterface;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserServiceInterface userService;
	private ProductServiceInterface productService;
	private ReviewServiceInterface reviewService;
    private ContactServiceInterface contactService;
    private OrderServiceInterface orderService;

	@Autowired
	public UserController(UserServiceInterface userService, ProductServiceInterface productService, ReviewServiceInterface reviewService, ContactServiceInterface contactService, OrderServiceInterface orderService) {
		this.userService = userService;
		this.productService = productService;
		this.reviewService = reviewService;
        this.contactService = contactService;
        this.orderService = orderService;
	}

	//update user account
	@PutMapping("/update")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
    } 

    @PutMapping("/suspendaccount")
    public ResponseEntity<User> suspendUser() {
		userService.suspendAccount();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //update user contact
	@PutMapping("/contact")
    public ResponseEntity<Contact> updateContactForUser(@Valid @RequestBody Contact contact) {
        return new ResponseEntity<>(contactService.updateContactForUser(contact), HttpStatus.OK);
    }

    @GetMapping("/contact")
	public ResponseEntity<Contact> showContact() {
		return new ResponseEntity<>(contactService.getContactForUser(), HttpStatus.OK);
	}

	//product requests
	@GetMapping("/product/all")
	public ResponseEntity<List<Product>> listProducts() {
		return new ResponseEntity<>(productService.listProducts(), HttpStatus.OK);
	}

	@GetMapping("/product/{id}")
	public ResponseEntity<Product> showProduct(@PathVariable Long id) {
		return new ResponseEntity<>(productService.getByProductId(id), HttpStatus.OK);
	}

	//product review requests
	@GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getByReviewId(reviewId), HttpStatus.OK);
    }
	@GetMapping("/product/{productId}/reviews")
    public ResponseEntity<List<Review>> listReviewsForProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(reviewService.listReviewsForProduct(productId), HttpStatus.OK);
    }

    // user reviews requests
	@GetMapping("/reviews")
    public ResponseEntity<List<Review>> listReviewsForUser() {
        return new ResponseEntity<>(reviewService.listReviewsForUser(), HttpStatus.OK);
    }

	@PostMapping("/product/{productId}/addreview")
    public ResponseEntity<Review> addReview(@Valid @RequestBody Review review, @PathVariable Long productId) {
        reviewService.save(review, productId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

	@PutMapping("/updatereview/{reviewId}")
    public ResponseEntity<Review> updateReview(@Valid @RequestBody Review review, @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.updateReview(review, reviewId), HttpStatus.OK);
    } 

	@DeleteMapping("/deletereview/{reviewId}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //order/cart requests
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> listOrdersForUser() {
        return new ResponseEntity<>(orderService.listOrdersForUser(), HttpStatus.OK);
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

  	@PutMapping("/product/{productId}/addToCart")
    public ResponseEntity<Order> addToCart(@PathVariable Long productId, @RequestParam Integer quantity) {
        orderService.addOrderForUser();
        orderService.addToCart(productId, quantity);
        return new ResponseEntity<>(HttpStatus.OK);
    }  

    @DeleteMapping("/clearCart")
    public ResponseEntity<Order> clearCart() {
        orderService.clearCart();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }  

    @PutMapping("/makeOrder/{orderId}")
    public ResponseEntity<Order> makeOrder(@PathVariable Long orderId) {
        orderService.makeOrder(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }  

}
