# Project Overview

## API

This project includes a set of API components built with **Java** and **Spring Boot** to manage user authentication and product-related operations. It uses **JWT (JSON Web Token)** for securing endpoints, role-based access control for different users, and an in-memory database via **Hibernate** that resets every time the project is stopped.

---

## Technologies Used

- **Java 21**
- **Spring Boot**
- **Maven**
- **Hibernate**

---

## Running the Project

To run the project, use the following Maven command:

```shell
mvn spring-boot:run
```

---

## Example Endpoints

Server runs on `http://localhost:8080`.

### User Authentication (Unsecured for Simplicity)

#### Login
**Endpoint**: `/api/auth/login`  
**Method**: `POST`

**Request Body**:
```json
{
  "username": "user",
  "password": "password"
}
```

**Response**:
```json
{
  "token": "jwt-token"
}
```

#### Register
**Endpoint**: `/api/auth/register`  
**Method**: `POST`

**Request Body**:
```json
{
  "username": "newUser",
  "password": "securePassword",
  "role": "USER"
}
```

**Response**:
```json
{
  "token": "jwt-token"
}
```

---

### Product Management (Secured with JWT)

#### Get All Products
**Endpoint**: `/api/products`  
**Method**: `GET`

**Headers**:
```json
{
  "Authorization": "Bearer jwt-token"
}
```

**Response**:
```json
[
  {
    "id": 1,
    "name": "Product 1",
    "price": 100
  },
  {
    "id": 2,
    "name": "Product 2",
    "price": 200
  }
]
```

#### Get Product by ID
**Endpoint**: `/api/products/{id}`  
**Method**: `GET`

**Headers**:
```json
{
  "Authorization": "Bearer jwt-token"
}
```

**Response**:
```json
{
  "id": 1,
  "name": "Product 1",
  "price": 100
}
```

#### Create Product (Admin Only)
**Endpoint**: `/api/products`  
**Method**: `POST`

**Headers**:
```json
{
  "Authorization": "Bearer jwt-token"
}
```

**Request Body**:
```json
{
  "name": "New Product",
  "price": 150
}
```

**Response**:
```json
{
  "id": 3,
  "name": "New Product",
  "price": 150
}
```

---

## Role-Based Access

1. **Admin**:
    - Can access all product-related APIs (GET, POST, PUT, DELETE).
2. **User**:
    - Limited to retrieving products (GET `/api/products` or `/api/products/{id}`).

---

## Database Configuration

- **Database**: Configured with Hibernate and is stored in-memory.
- **Persistence**: The database resets every time the project stops, so all data is erased.

---
