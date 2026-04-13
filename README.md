# Banking System (Spring Boot)

## Features
- Deposit
- Withdraw
- Transfer
- Transaction History (with Pagination, Sorting, Filtering)

## Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL

## APIs
- /api/deposit
- /api/withdraw
- /api/transfer
- /api/transactions/{accountId}

## Transaction API Supports
- Pagination (page, size)
- Sorting (asc/desc based on timestamp)
- Filter by Transaction Type (DEPOSIT / WITHDRAW)
- Filter by Date Range (fromDate, toDate)

## Improvements
- Initially used long repository query methods (not scalable ❌)
- Refactored using JPA Specification (Criteria API) ✅
- Cleaner, dynamic, and maintainable queries
- Added validation for invalid date ranges (fromDate > toDate)
- Implemented Global Exception Handling

## Author
Deepak