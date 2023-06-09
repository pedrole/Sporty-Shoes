



<!-- ABOUT THE PROJECT -->
# Sporty Shoes


![Product Logo](Screenshots/logo.jpg)

## Description

Sporty Shoes is a company that manufactures and sells sports shoes. They have a walk-in store, and now, they wish to launch their ecommerce portal sportyshoes.com.

The admin is able:
* Manage the products in the store including categorizing them
* Browse the list of users who have signed up and be able to search users
* See purchase reports filtered by date and category
* Change password

## Technologies Used

| Java | 1.8 |
| ------ | ------- |
| Spring Boot | 2.7.10 |
| Lombok  | --- |
| JSP | --- |
| MySql | 8 |
| JPA | --- |
| Spring Security Starter | --- | 

## File Structure

├───main  
│   ├───java  
│   │   └───com  
│   │       └───caltech  
│   │           └───sportyshoes  
│   │               │   ServletInitializer.java  
│   │               │   SportyShoesApplication.java  
│   │               │  
│   │               ├───config  
│   │               │       WebSecurityConfig.java  
│   │               │  
│   │               ├───controllers  
│   │               │       BrandController.java  
│   │               │       IndexController.java  
│   │               │       OrderController.java  
│   │               │       ProductController.java    
│   │               │       UserController.java  
│   │               │  
│   │               ├───dao  
│   │               │       BrandDAO.java  
│   │               │       OrderDAO.java  
│   │               │       ProductDAO.java  
│   │               │       UserDAO.java  
│   │               │       UserDetailsServiceImplementation.java  
│   │               │  
│   │               ├───pojo  
│   │               │       Brand.java  
│   │               │       MyUsersDetails.java<br>
│   │               │       Order.java  
│   │               │       Product.java  
│   │               │       User.java  
│   │               │  
│   │               └───repositories  
│   │                       BrandRepository.java  
│   │                       OrderRepossitory.java  
│   │                       ProductRepository.java  
│   │                       UserRepository.java  
│   │  
│   ├───resources  
│   │   │   application.properties  
│   │   │  
│   │   ├───static  
│   │   └───templates  
│   └───webapp  
│       │   addBrand.jsp  
│       │   addProduct.jsp  
│       │   brands.jsp  
│       │   editBrand.jsp  
│       │   editProduct.jsp  
│       │   header.jsp  
│       │   index.jsp  
│       │   orders.jsp  
│       │   products.jsp  
│       │   profile.jsp  
│       │   register.jsp  
│       │   users.jsp  
│       │  
│       └───css  
│               style.css  
│  
└───test  
    └───java  
        └───com  
            └───example  
                └───demo  
                        SportyShoesApplicationTests.java  

## Screenshots
![Sign In](Screenshots/sign_in.png)
![Register](Screenshots/register.png)
![Index](Screenshots/index_admin_logged_in.png)
![Product Listing](Screenshots/product_listing.png)
![List Orders](Screenshots/list_orders.png)
![List Users](Screenshots/search_signedup_users.png)
![List Users](Screenshots/Brand_Listing.png)

## Installation

You can clone this repository and use it localy:
```sh
$ git clone https://github.com/pedrole/Sporty-Shoes.git
```

**Using Maven plugin**

First you should do clean installation:
```sh
$ mvn clean install
```
You can start application using Spring Boot custom command:
```sh
$ mvn spring-boot:run
```

## Getting started

Initially it created the default admin with Login: ```admin``` Password: ```admin```.

## Roles
**ADMIN** can add, edit and delete products.
**CUSTOMER** can buy products

## Specification Document

[Specification Document](./Specification_Document.pdf)













