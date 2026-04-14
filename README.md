# Banking System (Spring Boot)

## Features
- Deposit
- Withdraw
- Transfer
- Transaction History (with Pagination, Sorting & Filtering)
- Dynamic Filtering using JPA Specifications
- Global Exception Handling
- Swagger (OpenAPI) API Documentation
- JWT Authentication (Stateless Security)
- Role-Based Authorization (ADMIN / USER)
- Password Encryption using BCrypt

---

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- MySQL
- Swagger (OpenAPI)

---

## APIs
- /api/users (Create User)
- /api/users/{id} (Get User)
- /api/accounts (Create Account)
- /api/accounts/deposit
- /api/accounts/withdraw
- /api/accounts/transfer
- /api/accounts/{id} (Get Account)
- /api/transactions/{accountId} (Transaction History with filters)
- /api/auth/login (User Login - JWT Token Generation)

---

## Security Features
- Implemented stateless authentication using JWT
- Secure APIs using Spring Security filter chain
- Role-based access control using `@PreAuthorize`
- Password hashing using BCryptPasswordEncoder
- JWT token contains user email and role for authorization
- Custom JWT filter validates token for every request

---

## Advanced Concepts Implemented
- Pagination using Pageable
- Sorting (ASC/DESC based on timestamp)
- Filtering by Transaction Type (DEPOSIT / WITHDRAW / TRANSFER)
- Date Range Filtering (fromDate, toDate)
- Dynamic Query Building using JPA Specification API
- Custom Exception Handling (Invalid Date Range, Insufficient Balance, Invalid Credentials, etc.)

---

## Why Specification?
Earlier, query methods in repository became complex and hard to maintain when combining multiple filters (type + date + account).  
To solve this, JPA Specifications were used to build dynamic and scalable queries.

---

## Swagger UI
Access API documentation here:  
http://localhost:8080/swagger-ui/index.html

👉 Supports JWT Authorization via **Authorize 🔒 button**

---

## Author
Deepak