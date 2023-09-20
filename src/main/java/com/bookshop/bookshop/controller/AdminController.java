package com.bookshop.bookshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookshop.bookshop.entity.Product;
import com.bookshop.bookshop.entity.Review;
import com.bookshop.bookshop.entity.User;
import com.bookshop.bookshop.entity.guest.WebGuest;
import com.bookshop.bookshop.service.ProductServiceInterface;
import com.bookshop.bookshop.service.ReviewServiceInterface;
import com.bookshop.bookshop.service.UserServiceInterface;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserServiceInterface userService;
	private ProductServiceInterface productService;
	private ReviewServiceInterface reviewService;

	@Autowired
	public AdminController(UserServiceInterface userService, ProductServiceInterface productService, ReviewServiceInterface reviewService) {
		this.userService = userService;
		this.productService = productService;
		this.reviewService = reviewService;
	}

	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}	

	//user requests
	@GetMapping("/user/all")
	public ResponseEntity<List<User>> listUsers() {
		return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<User> showUser(@PathVariable Long id) {
		return new ResponseEntity<>(userService.getByUserId(id), HttpStatus.OK);
	}

	@PostMapping("/user/adduser")
    public ResponseEntity<User> saveUser(@Valid @RequestBody WebGuest webguest, BindingResult theBindingResult,	HttpSession session) {
		String userName = webguest.getUserName();
		if (theBindingResult.hasErrors()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		User existing = userService.findByUserName(userName);
		if (existing != null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		userService.save(webguest);
		return new ResponseEntity<>(HttpStatus.CREATED);
    }

	@PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user, @PathVariable Long id) {
        return new ResponseEntity<>(userService.updateUserByAdmin(user, id), HttpStatus.OK);
    } 

	@PutMapping("/user/block/{id}")
    public ResponseEntity<User> blockUser(@PathVariable Long id) {
		userService.blockUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
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

	@PostMapping("/product/addproduct")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product, BindingResult theBindingResult,	HttpSession session) {
		String productName = product.getTitle();
		if (theBindingResult.hasErrors()){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		Product existing = productService.findByProductName(productName);
		if (existing != null){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		productService.save(product);
		return new ResponseEntity<>(HttpStatus.CREATED);
    }

	@PutMapping("/product/{id}")
    public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product, @PathVariable Long id) {
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }

    @DeleteMapping("/product/deleteproduct/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
		productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

	//review requests
	@GetMapping("/review/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.getByReviewId(reviewId), HttpStatus.OK);
    }
	@GetMapping("/product/{productId}/reviews")
    public ResponseEntity<List<Review>> listReviewsForProduct(@PathVariable Long productId) {
        return new ResponseEntity<>(reviewService.listReviewsForProduct(productId), HttpStatus.OK);
    }
	@GetMapping("/user/{userId}/reviews")
    public ResponseEntity<List<Review>> listReviewsForUser(@PathVariable Long userId) {
        return new ResponseEntity<>(reviewService.listReviewsForUserByAdmin(userId), HttpStatus.OK);
    }

	@PutMapping("/updatereview/{reviewId}")
    public ResponseEntity<Review> updateReview(@Valid @RequestBody Review review, @PathVariable Long reviewId) {
        return new ResponseEntity<>(reviewService.updateReviewByAdmin(review, reviewId), HttpStatus.OK);
    } 

	@DeleteMapping("/deletereview/{reviewId}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long reviewId) {
		reviewService.deleteReviewByAdmin(reviewId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 

}
