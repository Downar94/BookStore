package com.bookshop.bookshop.entity;

import java.util.Collection;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title", length = 40)
    @NotNull
    @Size(min = 1, max = 40)
    private String title;

    @Column(name = "description", length = 300)
    @NotNull
    @Size(min = 1, max = 300)
    private String description;

    @Column(name = "picture")
    private String picture;

    @Column(name = "amount")
    @NotNull
    private Integer amount;

    @Column(name = "price")
    @NotNull
    private Float price;

    @Column(name = "rating")
    private Integer rating;  

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Collection<Review> reviews;

    //@OneToMany(mappedBy = "product")
    //private Collection<OrderProducts> orderDetails;

    public Product() {
    }

    public Product(String title, Integer amount, Float price) {
        this.title = title;
        this.amount = amount;
        this.price = price;
    }

    public Product(String title, Integer amount, String description, Float price) {
        this.title = title;
        this.description = description;
        this.amount = amount;
        this.price = price;
    }

     public Product(String title, String picture, Integer amount, Float price) {
        this.title = title;
        this.picture = picture;
        this.amount = amount;
        this.price = price;
    }   

    public Product(String title, String description, String picture, Integer amount, Float price) {
        this.title = title;
        this.description = description;
        this.picture = picture;
        this.amount = amount;
        this.price = price;
    }   

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Integer getAmount() {
        return this.amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Float getPrice() {
        return this.price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", picture='" + getPicture() + "'" +
            ", amount='" + getAmount() + "'" +
            ", rating='" + getRating() + "'" +
            "}";
    }

    
}
