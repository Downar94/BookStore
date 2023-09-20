package com.bookshop.bookshop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;  
 
    @Column(name = "street_name", length = 40)
    @Size(min = 1, max = 40)
    private String streetName;

    @Column(name = "city", length = 30)
    @Size(min = 1, max = 30)
    private String city;    

    @Column(name = "country", length = 30)
    @Size(min = 1, max = 30)
    private String country;    

    @Column(name = "post_code", length = 20)
    @Size(min = 1, max = 20)
    private String postCode;   
    
    public Contact() {
    }

    public Contact(String streetName, String city, String country, String postCode) {
        this.streetName = streetName;
        this.city = city;
        this.country = country;
        this.postCode = postCode;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostCode() {
        return this.postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", streetName='" + getStreetName() + "'" +
            ", city='" + getCity() + "'" +
            ", country='" + getCountry() + "'" +
            ", postCode='" + getPostCode() + "'" +
            "}";
    }
}
