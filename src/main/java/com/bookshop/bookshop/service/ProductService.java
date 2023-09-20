package com.bookshop.bookshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookshop.bookshop.dao.OrderDaoInterface;
import com.bookshop.bookshop.dao.OrderProductsDaoInterface;
import com.bookshop.bookshop.dao.ProductDaoInterface;
import com.bookshop.bookshop.exception.EntityNotFoundException;
import com.bookshop.bookshop.exception.ProductNotFoundException;
import com.bookshop.bookshop.entity.Order;
import com.bookshop.bookshop.entity.OrderProducts;
import com.bookshop.bookshop.entity.Product;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements ProductServiceInterface {

	private ProductDaoInterface productDao;
	private OrderDaoInterface orderDao;
	private OrderProductsDaoInterface orderDetails;

	@Autowired
	public ProductService(ProductDaoInterface productDao, OrderDaoInterface orderDao, OrderProductsDaoInterface orderDetails) {
		this.productDao = productDao;
		this.orderDao = orderDao;
		this.orderDetails = orderDetails;
	}

	@Override
	public Product findByProductName(String productName) {
		return productDao.findByProductName(productName);
	}

	@Override
    public List<Product> listProducts() {
		return productDao.findAll();
	}

	@Override
    public Product getByProductId(Long id) {
		if (productDao.findById(id) == null) {
			throw new ProductNotFoundException(id);
		} 
        return productDao.findById(id);
    }

	@Override
	public void save(Product product) {
		Product newProduct = new Product();

		newProduct.setTitle(product.getTitle());
		newProduct.setDescription(product.getDescription());
		newProduct.setPicture(product.getPicture());
		newProduct.setAmount(product.getAmount());
		newProduct.setPrice(product.getPrice());
		productDao.save(newProduct);
	}

	@Override
	public Product loadProductByProductname(String productName){
		Product product = productDao.findByProductName(productName);
		if (product == null) {
			throw new EntityNotFoundException(Product.class);
		}
		return product;
	}

	@Override
    public Product updateProduct(Product product, Long id) {
		Product updated = productDao.findById(id);
		if (product == null) {
			throw new ProductNotFoundException(id);
		} 
		if(product.getTitle() != null) updated.setTitle(product.getTitle());
		if(product.getDescription() != null) updated.setDescription(product.getDescription());
		if(product.getPicture() != null) updated.setPicture(product.getPicture());
		if(product.getAmount() != null) updated.setAmount(product.getAmount());
		if(product.getPrice() != null) updated.setPrice(product.getPrice());
		productDao.save(updated);
		return updated;
	}

	@Override
    public void deleteProduct(Long id) {

		Product deleted = productDao.findById(id);

		if (deleted == null) {
			throw new ProductNotFoundException(id);
		} 

		List<OrderProducts> detailsForProduct = orderDetails.findDetailsForProduct(deleted);
		Order order = new Order();
		List<OrderProducts> detailsForOrder = new ArrayList<OrderProducts>();

		if(detailsForProduct != null) {
			for (OrderProducts detail : detailsForProduct) 
			{
				order = detail.getOrder();
				orderDetails.deleteDetails(detail);
				detailsForOrder = orderDetails.findDetailsForOrder(order);

				if(detailsForOrder!=null)
				{			
				order.setTotalPrice(countTotalPrice(detailsForOrder));
				orderDao.save(order);
				}
				else {
					order.setTotalPrice((float) 0);
					orderDao.save(order);
				}

			}
		}

		productDao.deleteProduct(deleted);
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


}
