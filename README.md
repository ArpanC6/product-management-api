# Product Management REST API

A RESTful API built with Spring Boot for managing product data,
featuring proper MVC architecture and global exception handling.

## Tech Stack

- Java 24
- Spring Boot 4.0.6
- Spring Web (REST API)
- Maven

## Project Structure

```
src/
└── main/
    └── java/
        └── com/
            └── arpan/
                └── product_management_api/
                    ├── controller/
                    │   └── ProductController.java       # Handles HTTP requests
                    ├── model/
                    │   └── Product.java                 # Data model (id, name, category, price, stock)
                    ├── service/
                    │   └── ProductService.java          # Business logic
                    ├── exception/
                    │   ├── GlobalExceptionHandler.java  # Catches all exceptions globally
                    │   ├── ProductNotFoundException.java # Custom exception
                    │   └── ErrorResponse.java           # Clean JSON error response
                    └── ProductManagementApiApplication.java # Entry point
```

## API Endpoints

| Method | Endpoint | Description | Response |
|--------|----------|-------------|----------|
| GET | /api/products | Get all products | 200 OK |
| GET | /api/products/{id} | Get product by ID | 200 OK / 404 Not Found |
| GET | /api/products/category/{category} | Get products by category | 200 OK |
| GET | /api/products/filter?maxPrice={price} | Get products under max price | 200 OK |
| POST | /api/products | Add new product | 201 Created |
| PUT | /api/products/{id} | Update product | 200 OK |
| DELETE | /api/products/{id} | Delete product | 200 OK |
| PATCH | /api/products/{id}/stock?quantity={qty} | Update stock | 200 OK |

## Error Handling

Instead of returning a generic error page, invalid requests return
a clean JSON error response.

```json
{
  "status": 404,
  "message": "Product not found with id: 99"
}
```

## Architecture

```
HTTP Request
     |
     v
ProductController      (Receives request, sends response)
     |
     v
ProductService         (Business logic, data processing)
     |
     v
Product Model          (Data: id, name, category, price, stock)
     |
     v (if error)
GlobalExceptionHandler (Catches exception, returns clean JSON)
```
## How to Run

1. Clone the repository

```bash
git clone https://github.com/ArpanC6/product-management-api.git
```

2. Navigate to project folder

```bash
cd product-management-api/product-management-api
```

3. Run the application

```bash
./mvnw spring-boot:run
```

4. API will start at http://localhost:8080

## Sample Responses

GET /api/products

```json
[
  { "id": 1, "name": "Laptop", "category": "ELECTRONICS", "price": 55000.0, "stock": 10 },
  { "id": 2, "name": "Phone", "category": "ELECTRONICS", "price": 20000.0, "stock": 25 },
  { "id": 3, "name": "T-Shirt", "category": "CLOTHING", "price": 499.0, "stock": 100 }
]
```

GET /api/products/1

```json
{
  "id": 1,
  "name": "Laptop",
  "category": "ELECTRONICS",
  "price": 55000.0,
  "stock": 10
}
```