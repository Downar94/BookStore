# BookStore
## Short Description
Bookstore CRUD application written using spring boot. The application endpoints are divided between Admin Controller - for the users with the role of administrator, User Controller for the basic users. It contains also Registration and Login Controllers. Authorization is based on the Json Web Token (JWT). The database used in the application is MySQL. The application consists of the User, Contact, Role, Review, Product, Order and OrderProduct entities and allows such actions as:
* register, login, get, add, update, block/suspend user/user account
* get and update contact for user
* operating on the set of products (get, update, delete)
* create, update, get reviews about products
* making orders, clearing orders, get informations about orders
  
Detailed information about available endpoints can be found [here](#endpoints).
## Used technologies:
* Spring Boot
* Spring Security
* JPA / Hibernate
* Maven
* Java
* MySQL
* Lombok
## How to run
#### 1. Create Database:
```
CREATE DATABASE bookstore;
```
#### 2. Clone github repository
#### 3. Create initial tables based on the attached `database_preparation.sql` script
#### 4. Set DB username and password in the `application.properties` file
#### 5. Open command prompt and navigate to the root path
#### 6. Use the following commands:
```
mvn install
```
```
mvn clean install
```
#### 7. Navigate to the target folder
#### 8. Invoke:
```
java -jar bookshop-0.0.1-SNAPSHOT.jar
```
#### 8. Use Postman to run initial Post requests:
* `http://localhost:8080/register/registration` - for register new user
* `http://localhost:8080/authentication/login` - for authenticate as a created user or as a user created based on the `database_preparation.sql` script

It is also possible to run BookStore application using Docker.

<a name="endpoints"></a>
## Endpoints
There are 2 different basic controllers - one for `admin` role and another for `user` role
### Admin Controller
#### 1. User requests:
#### Display the list of users

![listing_users](https://github.com/Downar94/BookStore/assets/44844566/93ec864b-4ad7-441c-971c-84b38b407486)

#### Display the individual user by id

![user_by_id](https://github.com/Downar94/BookStore/assets/44844566/3d9f7cb4-c7e7-4622-9d9f-aa6fc6064f7d)

#### Create new user

![creating_user](https://github.com/Downar94/BookStore/assets/44844566/27fdc382-7cd4-482e-89ec-4f61274297d6)

#### Update individual user

![updating_user](https://github.com/Downar94/BookStore/assets/44844566/6b52f39f-2e05-42bb-bd75-ead982556060)

#### Block user account

![block_user](https://github.com/Downar94/BookStore/assets/44844566/5ac78e72-7bca-40e1-be9b-52e26235f44b)

#### 2. Product requests:
#### Display the list of products

![listing_product](https://github.com/Downar94/BookStore/assets/44844566/630d93c8-eb78-4335-ae9c-e9989f739a13)

#### Display the individual product by id

![get_by_id](https://github.com/Downar94/BookStore/assets/44844566/416077db-2a4a-4205-87a1-13e17d8ef2f8)

#### Create new product

![add_product](https://github.com/Downar94/BookStore/assets/44844566/3a1436a7-675f-4a38-a75e-47427b338c13)

#### Update individual product

![update_product](https://github.com/Downar94/BookStore/assets/44844566/972ffaf0-0578-4f30-a285-c0dd2e5e3f39)

#### Delete product

![delete_product](https://github.com/Downar94/BookStore/assets/44844566/8cf6cd46-5848-4307-bf4a-f5cd3166e5dc)

#### 3. Review requests:
#### Display the list of reviews for user

![list_reviews_for_user](https://github.com/Downar94/BookStore/assets/44844566/bc1d73b3-b6e9-43b0-9165-ad590885b6e7)

#### Display the list of reviews for product

![get_reviews_for_product](https://github.com/Downar94/BookStore/assets/44844566/51a4f7f9-29c8-4552-9673-dd2f582e1faa)

#### Display the individual review by id

![get_by_id](https://github.com/Downar94/BookStore/assets/44844566/96838057-0aa1-4b1f-9d40-7166a2161823)

#### Update review

![update_review_with_id](https://github.com/Downar94/BookStore/assets/44844566/d12df0d9-5f5f-405e-854a-60b20efa805f)

#### Delete review

![delete_review_with_id](https://github.com/Downar94/BookStore/assets/44844566/b9b7a079-2e48-44c2-bd74-e1447b315683)

### User Controller
#### 1. User requests:
#### Update and suspend current user account

![update_or_suspend_user_account](https://github.com/Downar94/BookStore/assets/44844566/9675118f-f3e5-4d07-923d-f4a0d2d2b342)

#### Display and update current user contact data

![update_and_get_user_contact_informations](https://github.com/Downar94/BookStore/assets/44844566/9272aa7f-acea-4e87-b7ad-b6c09d093b52)

#### 2. Product requests:
#### Display the list of products and get the individual product by id

![list_all_products_or_get_by_id](https://github.com/Downar94/BookStore/assets/44844566/29623569-0376-4731-9df9-9646068a56a0)

#### 3. Review requests:
#### Display the list of reviews for current user

![list_all_reviews](https://github.com/Downar94/BookStore/assets/44844566/9380bd27-b79a-4cd4-82ae-5d4b95e6c993)

#### Get individual review by id (if belongs to current user) and display list of reviews for product

![get_review_by_id_and_get_reviews_for_product_id](https://github.com/Downar94/BookStore/assets/44844566/a3c394b0-a78a-4f14-a8fa-d416dedd4cb3)

#### Create review for product, update and delete current user review

![add_review_for_product_update_delete](https://github.com/Downar94/BookStore/assets/44844566/cbee9480-c621-45fc-8694-bcd00e5d6467)

#### Display the list of current user orders and get the individual order by id

![get_orders_all_or_by_id](https://github.com/Downar94/BookStore/assets/44844566/3984a532-d592-4459-bc18-8a039e97e953)

#### Add individual product to cart and clear cart from products

![add_to_cart_and_clear_the_cart](https://github.com/Downar94/BookStore/assets/44844566/36d49ec7-8f78-4d1e-9ea9-f3bd884f4928)

