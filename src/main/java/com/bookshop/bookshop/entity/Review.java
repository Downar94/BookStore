package com.bookshop.bookshop.entity;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"user_id", "product_id"})}, name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rating")
    @NotNull
    @Range(min= 1, max= 5)
    private Integer rating;

    @Column(name = "description", length = 120)
    @NotNull
    @Size(min = 1, max = 120)
    private String description;

    @Column(name = "user_id")
    Long userId;
    @Column(name = "product_id")
    Long productId;

    public Review() {
    }

    public Review(Integer rating, String description) {
        this.rating = rating;
        this.description = description;
    }

    public Review(Integer rating, String description, Long userId, Long productId) {
        this.rating = rating;
        this.description = description;
        this.userId = userId;
        this.productId = productId;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return this.productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getRating() {
        return this.rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", rating='" + getRating() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }

}
