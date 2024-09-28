# Car Sales Backend

This repository contains the backend for a car sales website. The backend was developed in Java, using Spring Boot to provide RESTful APIs that support features such as vehicle registration, vehicle search, user management, among others.

## Table of Contents
- [Technologies Used](#technologies-used)
- [Features](#features)
- [Environment Setup](#environment-setup)

## Technologies Used
- **Java 17**: Main language of the project.
- **Spring Boot**: Framework for building Java applications, used to create the REST APIs.
- **Spring Data JPA**: Used for data persistence and communication with the database.
- **Hibernate**: ORM tool to manage communication with the database.
- **H2 Database**: In-memory database for testing, which can be configured to use PostgreSQL or MySQL.
- **Maven**: Dependency management tool.
- **JUnit**: Used for unit testing.

## Features
- **Car Management**: Register, update, remove, and list available vehicles.
- **User Authentication**: User registration and login, with password hashing support.
- **Vehicle Search**: Filter vehicles by different criteria like brand, model, year, and price.
- **User Management**: Password change and user information management.

## Environment Setup
1. **Prerequisites**:
   - Java 17 or higher installed.
   - Maven installed.
   - Database PostgreSQL configured.

2. **Clone the repository**:
   ```sh
   git clone https://github.com/JoseVictorMarques/sales.git
   cd sales
