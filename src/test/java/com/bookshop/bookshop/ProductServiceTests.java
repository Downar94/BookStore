package com.bookshop.bookshop;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.bookshop.bookshop.dao.ProductDao;
import com.bookshop.bookshop.service.ProductService;
import com.bookshop.bookshop.entity.Product;

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

@ExtendWith(SpringExtension.class)
public class ProductServiceTests {
    
   @Mock
    private ProductDao productDao;

    @InjectMocks
    private ProductService productService;

/*   @Test
    public void findAllFromDaoTest(){
        when(productDao.findAll()).thenReturn(Arrays.asList(
            new Product("Harry Potter", 3, (float) 30),
            new Product("The Little Prince", "The story about boy, who...", "032552562", 12, (float) 30)
        ));

        List<Product> products = productService.listProducts();

        assertEquals("Harry Potter", products.get(0).getTitle());
        assertEquals(12, products.get(1).getAmount());
    }

    @Test
    public void getByProductIdTest(){
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30, (float) 30);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        when(productDao.findById(1L)).thenReturn(product);

        Product productServiceResult = productService.getByProductId(1L);
        assertEquals(product.getTitle(), productServiceResult.getTitle());
    }*/
    /* 
    @Test
    public void saveTest(){
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        when(productDao.findById(1L)).thenReturn(product);

        Product newProduct = new Product("The Little Prince", "The story about boy, who...", "032552562", 12);
        productService.save(newProduct);

        verify(productDao, times(1)).save(newProduct);
    }*/

/*   @Test
    public void updateProductTest(){
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30, (float) 30);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        when(productDao.findById(1L)).thenReturn(product);

        Product newProduct = new Product("The Little Prince", "The story about boy, who...", "032552562", 12, (float) 30);
        productService.updateProduct(newProduct, 1L);

        assertEquals(productDao.findById(1L).getTitle(), newProduct.getTitle());
    }

    @Test
    public void deleteProductTest(){
        Product product = new Product("Harry Potter", "The story of the teenager wizard", "032552562", 30, (float) 30);
        when(productDao.findAll()).thenReturn(Arrays.asList(product));
        when(productDao.findById(1L)).thenReturn(product);

        productService.deleteProduct(1L);

        verify(productDao, times(1)).deleteProduct(product);
    }*/ 



    
}
