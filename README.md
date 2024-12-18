# DhruvZon E-commerce Backend

## Overview
**DhruvZon** is a production-grade backend implementation for an e-commerce platform inspired by Amazon. Built with Spring Boot and a PostgreSQL database, this project adheres to industry best practices and conventions to ensure scalability, security, and high performance.

This backend application supports essential and advanced features required in a real-world e-commerce system, such as user management, product catalog management, role-based access control (RBAC), and more.

## Features
### Core Features
1. **User Management:**
   - Registration, Login, and Authentication with JWT.
   - Role-based access control (Admin, User, Guest).

2. **Product Management:**
   - CRUD operations for products.
   - Categories and subcategories with hierarchical structures.
   - Product variants, inventory tracking, and media (images/videos).

3. **Reviews and Ratings:**
   - User reviews with ratings (1-5 scale).
   - Moderation of reviews.

4. **Security:**
   - Production-grade security using Spring Security.
   - Password hashing with BCrypt.

### Advanced Features (Planned)
- Shopping Cart and Checkout.
- Payment integration.
- Full-text search and filtering with pagination.
- Dynamic pricing and discount systems.
- Admin dashboard for analytics and management.

## Tech Stack
- **Backend Framework:** Spring Boot (3.4.0)
- **Programming Language:** Java (21)
- **Database:** PostgreSQL
- **Security:** Spring Security, JWT (jjwt-api)
- **ORM:** Hibernate with Spring Data JPA
- **Validation:** Spring Boot Starter Validation
- **Testing:** JUnit, Spring Boot Starter Test, Spring Security Test
- **API Documentation:** Swagger (springdoc-openapi)
- **Build Tool:** Maven

## Installation
1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/dhruvzon-backend.git
   cd dhruvzon-backend
   ```

2. **Set up the database:**
   - Install PostgreSQL and create a database (e.g., `dhruvzon_db`).

3. **Configure the application:**
   - Update the `application.yml` file with your database credentials:
     ```yaml
     spring:
       datasource:
         url: jdbc:postgresql://localhost:5432/dhruvzon_db
         username: your_username
         password: your_password
       jpa:
         hibernate:
           ddl-auto: update
     ```

4. **Build and run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

5. **Access the API:**
   - API Base URL: `http://localhost:8080`
   - Swagger UI: `http://localhost:8080/swagger-ui.html`

## Project Structure
```
src/main/java/com/Ecommerce/dhruvzon/
├── config/       # Security and application configuration
├── controller/   # REST API controllers
├── dto/          # Data Transfer Objects (Request and Response)
├── exception/    # Global exception handling
├── mapper/       # MapStruct mappers for DTOs
├── model/        # Entity classes (e.g., User, Product, Category)
├── repository/   # Spring Data JPA repositories
├── service/      # Business logic services
├── util/         # Utility classes
```

## Current Progress
1. **User Management:**
   - User entity, DTOs, and repository finalized.
   - Role-based access control implemented.

2. **Product Management:**
   - Product and category entities with hierarchical relationships implemented.
   - Review and image management added.

3. **Security:**
   - JWT authentication is in place.

4. **Configurations:**
   - `application.yml` and `pom.xml` files finalized for a production-grade setup.

## Roadmap
1. Complete product management (categories, subcategories, inventory).
2. Implement shopping cart and checkout.
3. Add payment integration.
4. Build admin dashboard for analytics and control.
5. Optimize performance with caching and indexing.
6. Deploy the application on a cloud platform.

## Contributing
Contributions are welcome! Please follow these steps:
1. Fork the repository.
2. Create a new branch for your feature (`git checkout -b feature-branch-name`).
3. Commit and push your changes.
4. Create a pull request describing your changes.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Contact
For any queries or suggestions, please reach out to the project maintainer:
- **Name:** Paritosh Pal
- **Email:** your_email@example.com

---

Thank you for checking out DhruvZon E-commerce Backend! Together, let's build a robust and scalable application.

