package com.bookshop.bookshop.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "orders_products")
public class OrderProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "order_id")
    private Order order;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "product_id")
    private Product product;
    
    @Column(name = "quantity")
    @NotNull
	private Integer quantity;


    public OrderProducts() {
    }

    public OrderProducts(Order order, Product product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return this.order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return this.quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof OrderProducts)) {
            return false;
        }
        OrderProducts orderProducts = (OrderProducts) o;
        return Objects.equals(id, orderProducts.id) && Objects.equals(order, orderProducts.order) && Objects.equals(product, orderProducts.product) && Objects.equals(quantity, orderProducts.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, order, product, quantity);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", order='" + getOrder() + "'" +
            ", product='" + getProduct() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }

}
