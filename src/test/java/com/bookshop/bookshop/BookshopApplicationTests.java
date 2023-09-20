package com.bookshop.bookshop;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.bookshop.bookshop.dao.ProductDao;
import com.bookshop.bookshop.dao.ReviewDao;
import com.bookshop.bookshop.dao.UserDao;
import com.bookshop.bookshop.entity.Product;
import com.bookshop.bookshop.entity.Review;
import com.bookshop.bookshop.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BookshopApplicationTests {

	@Autowired
	private WebApplicationContext applicationContext;
	private MockMvc mockMvc;

	@Autowired
	ProductDao productDao;

	@Autowired
	UserDao userDao;

	@Autowired
	ReviewDao reviewDao;

	private Long userId;
	private Long reviewId;
	private Long productId;
	private Long productTestId;

	/*public static String asJsonString(final Object obj) {
    try {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
}  

	@BeforeAll
	public void init(){
		this.mockMvc = MockMvcBuilders
            .webAppContextSetup(applicationContext)
            .build();

		this.productId = setUpProduct();
		this.userId = setUpUser();
		this.reviewId = setUpReview();
	}

	@AfterAll
	public void teardown() {

	}

	private Long setUpReview(){
		Review review = new Review(4, "nice book");
		review.setProductId(this.productId);
		review.setUserId(this.userId);
		reviewDao.save(review);		
		return review.getId();
	}


	private Long setUpUser(){
		User user = new User("harrypotter", "haslo123", "Harry","Potter","harry_potter@email.com");
		userDao.save(user);
		User savedUser = userDao.findByUserName(user.getUserName());
		return savedUser.getId();
	}

	private Long setUpProduct(){
		Product product = new Product("Czara Ognia", "The story about boy, who...", "032552523", 12, (float) 30);
		productDao.save(product);
		Product savedProduct = productDao.findByProductName(product.getTitle());
		return savedProduct.getId();
	}
	private void deleteProduct(){
		productDao.deleteProduct(productDao.findById(this.productId));
	}
	@Test
	void contextLoads() {
		assertNotNull(mockMvc);
	}


	//Test product requests provided by UserController
	@Test
	public void showProductTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/user/product/" + this.productId);

		mockMvc.perform(request)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(this.productId))
		.andReturn();

	}


	//Test product requests provided by AdminController
	@Test
	public void showProductAdminTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/product/" + this.productId);

		mockMvc.perform(request)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(this.productId));


	}

	@Test
	public void listProductsAdminTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/admin/product/all");
		mockMvc.perform(request)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.products").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

	}

	@Test
	public void saveProductTest() throws Exception {
		Product newProduct = new Product("Zakona aFeniksaas", "The story of the teenager wizard", "032552562", 30, (float) 30);
		RequestBuilder request = MockMvcRequestBuilders.post("/admin/product/addproduct").contentType(MediaType.APPLICATION_JSON).content(asJsonString(newProduct));
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isCreated());

		this.productTestId = productDao.findByProductName(newProduct.getTitle()).getId();

	}
 
	@Test
	public void updateProductTest() throws Exception {
		Product newProduct = new Product("Wiezien azkabanu", "The story of the teenager wizard", "032552562", 30, (float) 30);
		RequestBuilder request = MockMvcRequestBuilders.put("/admin/product/" + this.productId).contentType(MediaType.APPLICATION_JSON).content(asJsonString(newProduct));
		mockMvc.perform(request)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.title").value(newProduct.getTitle()));
	}
  
	@Test
	@AfterAll
	public void deleteProductTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.delete("/admin/product/deleteproduct/" + this.productId);
		mockMvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isNoContent());

		RequestBuilder request1 = MockMvcRequestBuilders.delete("/admin/product/deleteproduct/" + this.productTestId);

		mockMvc.perform(request1)
		.andExpect(MockMvcResultMatchers.status().isNoContent());

	}*/

	//Test review requests provided by AdminController
/* 
	@Test
	public void listReviewsForProductTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("admin/product/"+this.productId+"/reviews");
		mockMvc.perform(request)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.reviews").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

	}

	@Test
	public void listReviewsForUserTest() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("admin/"+this.userId+"/reviews");
		mockMvc.perform(request)
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.reviews").exists())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").isNotEmpty());

	}
	*/
}
