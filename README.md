# Catalog Management System

## Overview

This project is a Catalog Management System built using Spring Boot. It allows users to store and manage product data, including brand name, description, and other relevant information.

## Prerequisites

- Java 11 or higher
- Maven
- Spring Boot Version (3.3.1)
- An IDE like IntelliJ IDEA or Eclipse
- Use Postman to check the endpoint

## Dependencies

The following dependencies are used in this project:

- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- MySql Driver
- Spring Boot Starter Validation
- Spring Boot DevTools
- MySQL Connector
- Spring Boot Starter Test
- Lombok

## Setup Instructions

### 1. Clone the Repository

First, clone the repository from GitHub to your local machine:

```bash
git clone https://github.com/Loneasif88/Catalog-Management-System.git
```
### **2. Open the Project in Your IDE**
-Open your preferred IDE (e.g., IntelliJ IDEA, Eclipse).
-Select the option to open or import a project.
-Navigate to the directory where you cloned the repository (Catalog-Management-System).
-Select the project to open/import it into your IDE.

### 3. Build the Project
-Once the project is imported, locate the pom.xml file in the project directory.

Right-click on the pom.xml file and select the option to build or clean and build the project. This step will download the required dependencies and compile the project.

In IntelliJ IDEA: Right-click pom.xml > Maven > Reload Project.
In Eclipse: Right-click on the project > Run As > Maven clean and then Run As > Maven install.

### 4. Run the Application
After the build process is complete, locate the main application class CatalogManagementSystemApplication.java

Right-click on the main application class and select the option to run it as a Java application.

In IntelliJ IDEA: Right-click CatalogManagementSystemApplication > Run 'CatalogManagementSystemApplication'.
In Eclipse: Right-click on the main class > Run As > Java Application.

### 5. Access the MySQL Database
Ensure that you have MySQL installed and running on your local machine. If not, download and install it from the official MySQL website.
Use a MySQL client such as MySQL Workbench(Recommended), phpMyAdmin, or any other SQL client to connect to your MySQL database.
### MySQL Connection Details
JDBC URL: jdbc:mysql://localhost:3306/your_database_name (Replace your_database_name with the actual name of your database.)
Username: root (or your configured MySQL username)
Password: your_password (or your configured MySQL password)
Database Configuration in application.properties (if needed)
Make sure that your application.properties file in src/main/resources contains the correct database configuration for MySQL:
```
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```
### 6. Test the API Endpoints
Use Postman to test and interact with the API endpoints. You can send HTTP requests to your application's endpoints to verify that they are working as expected. For example:

- GET ```http://localhost:8080/api/products``` - Retrieves all products
- POST ```http://localhost:8080/api/products``` - Creates a new product
- PUT ```http://localhost:8080/api/products/{id}``` - Updates an existing product
- DELETE ```http://localhost:8080/api/products/{id}``` - Deletes a product
- GET ```http://localhost:8080/api/products/search``` - Searches for products based on parameters

### Fields added int the Product Entity
```` id, name, description, category, price, brand, dateAdded, discount, manufacturer, rating, quantity and createdDate, lastModifiedDate````

## API Endpoints

### Get All Products

- *URL:* localhost:8080/api/products
- *Method:* GET
- *Response:*
```
##### example:
json
    [
      {
        "id": 1,
        "name": "Product 1",
        "brand": "Brand A",
        "description": "Description of Product 1",
        "price": 100.0,
        "quantity": 10,
        "category": "Category A",
        "dateAdded": "2023-07-24T12:34:56",
        "discount": 10.0,
        "manufacturer": "Manufacturer A",
        "rating": 4.5
      },
      ...
    ]
   ``` 

### Get Product by ID

- *URL:* localhost:8080/api/products/{id}
- *Method:* GET
- *Response:*
```
    json
    {
      "createdDate": "2024-07-24T22:36:54.71948",
      "lastModifiedDate": null,
      "id": 1,
      "name": "Updated Product 1",
      "brand": "Updated Brand A",
      "description": "Updated Description of Product 1",
      "price": 150.0,
      "quantity": 15,
      "category": "Updated Category A",
      "dateAdded": null,
      "discount": 15.0,
      "manufacturer": "Updated Manufacturer A",
      "rating": 4.85
    }
   ``` 

### Create a Product

- *URL:* localhost:8080/api/products
- *Method:* POST
- *Request Body:*
```
    json
    {
      "name": "Product 1",
      "brand": "Brand A",
      "description": "Description of Product 1",
      "price": 100.0,
      "quantity": 10,
      "category": "Category A",
      "discount": 10.0,
      "manufacturer": "Manufacturer A",
      "rating": 4.5
    }
    
- *Response:*

    json
    {
    "createdDate": "2024-07-25T01:03:52.4185229",
    "lastModifiedDate": null,
    "id": 4,
    "name": "Product 1",
    "brand": "Brand A",
    "description": "Description of Product 1",
    "price": 100.0,
    "quantity": 10,
    "category": "Category A",
    "dateAdded": null,
    "discount": 10.0,
    "manufacturer": "Manufacturer A",
    "rating": 4.5
}
   ``` 

### Update a Product

- *URL:* localhost:8080/api/products/{id}
- *Method:* PUT
- *Request Body:*
```
    json
    {
      "name": "Updated Product 1",
      "brand": "Updated Brand A",
      "description": "Updated Description of Product 1",
      "price": 150.0,
      "quantity": 15,
      "category": "Updated Category A",
      "discount": 15.0,
      "manufacturer": "Updated Manufacturer A",
      "rating": 4.8
    }
    
- *Response:*

    json
    {
      "createdDate": "2024-07-24T22:36:54.71948",
    "lastModifiedDate": null,
    "id": 1,
    "name": "Updated Product 1",
    "brand": "Updated Brand A",
    "description": "Updated Description of Product 1",
    "price": 150.0,
    "quantity": 15,
    "category": "Updated Category A",
    "dateAdded": null,
    "discount": 15.0,
    "manufacturer": "Updated Manufacturer A",
    "rating": 4.8
    }
    
```
### Delete a Product

- *URL:* localhost:8080/api/products/{id}
- *Method:* DELETE
- *Response:* 204 No Content

### Search Products

- *URL:* localhost:8080/api/products/search
- ```http://localhost:8080/api/products/search?maxPrice=150&minPrice=140 ```
- *Method:* GET
- *Query Parameters:* (name=Shoes&maxPrice=150&minRating=4&maxRating=5&minPrice=150) search by anything you want
- *Response:*
```
    json
    [
      {
        "createdDate": "2024-07-24T22:36:54.71948",
        "lastModifiedDate": null,
        "id": 1,
        "name": "Updated Product 1",
        "brand": "Updated Brand A",
        "description": "Updated Description of Product 1",
        "price": 150.0,
        "quantity": 15,
        "category": "Updated Category A",
        "dateAdded": null,
        "discount": 15.0,
        "manufacturer": "Updated Manufacturer A",
        "rating": 4.8
    }
      ...
    ]
   ``` 

## Data Validation

The following validation annotations are used in the Product entity to ensure data integrity:

- @NotBlank: Ensures that the field is not null or empty.
- @NotNull: Ensures that the field is not null.
- @Positive: Ensures that the field value is greater than zero.
- @PositiveOrZero: Ensures that the field value is zero or greater.
- @Min and @Max: Ensure that the field value is within a specified range.

## Logging and Auditing

- *Logging:* Uses SLF4J for logging.
- *Auditing:* Extends the Auditable class to automatically manage createdDate, lastModifiedDate fields.

## Project Structure
```
CatalogManagementApplication.java
│ │ ├── model
│ │ │ ├── Auditable.java
│ │ │ ├── Product.java
│ │ ├── repository
│ │ │ ├── ProductRepository.java
│ │ ├── service
│ │ │ ├── ProductService.java
│ │ ├── controller
│ │ │ ├── ProductController.java
│ └── resources
│ ├── application.properties
```
