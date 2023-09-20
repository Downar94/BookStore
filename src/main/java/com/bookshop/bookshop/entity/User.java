package com.bookshop.bookshop.entity;

import java.util.Collection;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 15)
    @NotNull
    @Size(min = 3, max = 15, message = "username length must be between 3 and 15")
    private String userName;

    @Column(name = "password")
    @NotNull
    @Size(min = 1, message = "password must be longer")
    private String password;

    @Column(name = "first_name", length = 25)
    @NotNull
    @Size(min = 1, max = 25, message = "first name length must be between 1 and 25")
    private String firstName;

    @Column(name = "last_name", length = 25)
    @NotNull
    @Size(min = 1, max = 25, message = "last name length must be between 1 and 25")
    private String lastName;

    @Column(name = "email", nullable=false, length = 50, unique = true)
    @Pattern(regexp = "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
        + "[A-Za-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
        + "(?:[A-Za-z0-9](?:[A-Za-z0-9-]*[A-Za-z0-9])?\\.)+[A-Za-z0-9]"
        + "(?:[A-Za-z0-9-]*[A-Za-z0-9])?", message = "wrong email pattern")
    @Size(min = 5, max = 50, message = "email length must be between 5 and 50")
    private String email;

    @Column(name = "account_status", length = 20)
    private String accountStatus;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.MERGE, CascadeType.DETACH})
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Collection<Review> reviews;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private Contact contact;

    //@OneToMany(mappedBy = "user")
    //private Collection<Order> orders;


    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public User(String userName, String password, Collection<Role> roles) {
        this.userName = userName;
        this.password = password;
        this.roles = roles;
    }

    public User(String userName, String password, String firstName, String lastName, String email) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Collection<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Collection<Review> reviews) {
        this.reviews = reviews;
    }

    /*public Collection<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }*/

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", userName='" + getUserName() + "'" +
            ", password='" + getPassword() + "'" +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", email='" + getEmail() + "'" +
            ", roles='" + getRoles() + "'" +
            ", reviews='" + getReviews() + "'" +
            ", contact='" + getContact() + "'" +
            "}";
    }
    
}
