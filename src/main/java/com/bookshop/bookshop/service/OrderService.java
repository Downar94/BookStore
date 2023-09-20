package com.bookshop.bookshop.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.bookshop.bookshop.dao.OrderDaoInterface;
import com.bookshop.bookshop.dao.OrderProductsDaoInterface;
import com.bookshop.bookshop.dao.ProductDaoInterface;
import com.bookshop.bookshop.dao.UserDaoInterface;
import com.bookshop.bookshop.exception.OrderForUserNotFoundException;
import com.bookshop.bookshop.exception.ProductNotFoundException;
import com.bookshop.bookshop.exception.ProductOrderAlreadyExistException;
import com.bookshop.bookshop.exception.QuantityExceededException;
import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.OrderProducts;
import com.bookshop.bookshop.entity.Product;
import com.bookshop.bookshop.entity.User;

@Service
public class OrderService implements OrderServiceInterface {

	private OrderDaoInterface orderDao;
	private UserDaoInterface userDao;
	private ProductDaoInterface productDao;
	private OrderProductsDaoInterface orderDetails;

	@Autowired
	public OrderService(OrderDaoInterface orderDao, UserDaoInterface userDao, ProductDaoInterface productDao, OrderProductsDaoInterface orderDetails) {
		this.orderDao = orderDao;
		this.userDao = userDao;
		this.productDao = productDao;
		this.orderDetails = orderDetails;
	}

	@Override
    public List<Order> listOrdersForUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		List<Order> orders = orderDao.findOrdersForUser(user);
		return orders;
	}
 
	@Override
    public Order getOrderById(Long orderId) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		Long userId = user.getId();
		Boolean exist = false;
		List<Order> orders = orderDao.findOrdersForUser(user);
		Order searched = new Order();

		for (Order order : orders) 
		{
			if (order.getId() == orderId)
			{
				exist = true;
				searched = order;
			}
		}

		if (exist == false) {
			throw new OrderForUserNotFoundException(orderId, userId);
		}

		return searched;
	}

	@Override
    public void addOrderForUser() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		Boolean exist = false;
		List<Order> orders = orderDao.findOrdersForUser(user);
		Order updated = new Order();

		for (Order order : orders) 
		{
			String status  = order.getStatus();
			if (status.equals("not_placed"))
			{
				exist = true;
				updated = order;
			}
		}
		if (exist == false) {
			updated.setStatus("not_placed");
			updated.setUser(user);
		}

		orderDao.save(updated);
	}
	
	@Override
    public void addToCart(Long productId, Integer quantity) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		List<Order> orders = orderDao.findOrdersForUser(user);
		Order updated = new Order();
		Product product = productDao.findById(productId);

		if (product == null) {
			throw new ProductNotFoundException(productId);
		} 

		Integer productAmount = product.getAmount();
		if (quantity > productAmount)
		{
			throw new QuantityExceededException();
		}

		for (Order order : orders) 
		{
			if (order.getStatus().equals("not_placed"))
			{
				updated = order;
			}
		}
		
		Long updatedId = updated.getId();

		List<OrderProducts> details = orderDetails.findDetailsForOrder(updated);

		if(details != null) {
			for (OrderProducts detail : details) 
			{
				if (detail.getProduct().getId() == productId)
				{
					throw new ProductOrderAlreadyExistException(updatedId, productId);
				}
			}
		}
		OrderProducts newDetails = new OrderProducts(updated, product, quantity);
		
		orderDetails.save(newDetails);

		details.add(newDetails);
		updated.setTotalPrice(countTotalPrice(details));
		updated.setDate(LocalDateTime.now());
		orderDao.save(updated);	

		product.setAmount((productAmount - quantity));
		productDao.save(product);
	}

	public Float countTotalPrice(List<OrderProducts> details)
	{
		Float totalPrice = (float) 0;
		for (OrderProducts detail : details) 
		{
			Product product = detail.getProduct();
			totalPrice += product.getPrice() * detail.getQuantity();
		}

		return totalPrice;
	}

	@Override
	public void makeOrder(Long orderId){
		Order order = orderDao.findById(orderId);
		if(order.getStatus().equals("not_placed"))
		{
			order.setStatus("placed");
		}
		orderDao.save(order);
	}

	@Override
    public void clearCart() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userDao.findByUserName(username);
		List<Order> orders = orderDao.findOrdersForUser(user);
		Order cleared = new Order();
		Float totalPrice = (float) 0;
		Integer quantity = 0;

		for (Order order : orders) 
		{
			if (order.getStatus().equals("not_placed"))
			{
				cleared = order;
			}
		}

		List<OrderProducts> details = orderDetails.findDetailsForOrder(cleared);
		Product product = new Product();

		if(details != null) {
			for (OrderProducts detail : details) 
			{
				product = productDao.findById(detail.getProduct().getId());
				quantity = detail.getQuantity();
				product.setAmount(product.getAmount() + quantity);
				productDao.save(product);
				orderDetails.deleteDetails(detail);
			}
		}

		cleared.setTotalPrice(totalPrice);
		cleared.setDate(LocalDateTime.now());
		orderDao.save(cleared);			
	}
}
