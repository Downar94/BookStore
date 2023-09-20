package com.bookshop.bookshop.entity;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private String status;

    @Column(name = "total_price")
    private Float totalPrice;
    
    //@OneToMany(mappedBy = "order")
    //private Collection<OrderProducts> orderDetails;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "user_id")
    private User user;

    public Order() {
    }

    public Order(LocalDateTime date, String status, Float totalPrice) {
        this.date = date;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return this.date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    //public Collection<OrderProducts> getOrderDetails() {
    //    return this.orderDetails;
    //}

    //public void setOrderDetails(Collection<OrderProducts> orderDetails) {
    //    this.orderDetails = orderDetails;
    //}

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", date='" + getDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", totalPrice='" + getTotalPrice() + "'" +
            ", orderDetails='" + 
            ", user='" + getUser() + "'" +
            "}";
    }

}
