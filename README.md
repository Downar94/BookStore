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
