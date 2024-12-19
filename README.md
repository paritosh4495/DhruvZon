# Dhruvzon E-commerce Application

Dhruvzon is a production-grade e-commerce application inspired by platforms like Amazon. It is designed to manage product listings, user accounts, order processing, and more. Built using **Java** and **Spring Boot**, the application emphasizes scalability and security.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Features
- **User Registration and Authentication**: Secure user management.
- **Product Management**: Full CRUD operations for products.
- **Filtering Options**: Category and brand-based filtering for easy navigation.
- **JWT-Based Authentication**: Secure token-based user authentication.
- **Role-Based Access Control**: Different access levels for users and admins.
- **Exception Handling**: Robust error management.
- **Data Initialization**: Pre-load data for testing and development.

## Project Structure
The project follows a standard structure, allowing for easy navigation and understanding of its components. 
.idea/
dhruvzon/
    .mvn/
    src/
        main/
            java/
                com/
                    Ecommerce/
                        dhruvzon/
                            controller/
                            dto/
                            enums/
                            exception/
                            initializer/
                            mapper/
                            model/
                            repository/
                            response/
                            security/
                            service/
                            DhruvzonApplication.java
        test/
    target/

## Installation

### Prerequisites
To run this application, ensure you have the following installed:
- **Java 21 or higher**
- **Maven**
- **Postgres** or any other relational database

### Steps
1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd <repository-name>
   ```

2. **Configure the database**:
   - Update the `application.properties` file with your database configuration.

3. **Build the project**:
   ```bash
   mvn clean install
   ```

4. **Run the application**:
   ```bash
   mvn spring-boot:run
   ```

## Usage
Once the application is running, you can access the API endpoints using tools like Postman or through your frontend application.

## API Endpoints

### ProductController
| Method | Endpoint                                   | Description                              |
|--------|-------------------------------------------|------------------------------------------|
| POST   | `/api/products/add`                       | Create a new product                     |
| PUT    | `/api/products/update/{id}`              | Update an existing product               |
| GET    | `/api/products/{id}`                      | Get a product by ID                      |
| POST   | `/api/products/add/bulk`                 | Create multiple products in bulk         |
| GET    | `/api/products/all`                       | Get all products                         |
| GET    | `/api/products/search`                    | Search for products                      |
| GET    | `/api/products/category/{category}`       | Get products by category                 |
| GET    | `/api/products/brand/{brand}`             | Get products by brand                    |
| GET    | `/api/products/status/{status}`           | Get products by status                   |
| DELETE | `/api/products/{id}`                      | Delete a product by ID                   |
| PATCH  | `/api/products/{id}/status`               | Update the status of a product           |

### UserController
| Method | Endpoint                                   | Description                              |
|--------|-------------------------------------------|------------------------------------------|
| POST   | `/api/users/register`                     | Register a new user                     |
| PUT    | `/api/users/update/{id}`                  | Update an existing user                  |
| GET    | `/api/users/{id}`                         | Get a user by ID                        |
| GET    | `/api/users/email`                        | Get a user by email                     |
| GET    | `/api/users/active`                       | Get all active users                    |

### AdminController
| Method | Endpoint                                   | Description                              |
|--------|-------------------------------------------|------------------------------------------|
| GET    | `/api/admin/all`                          | Get all users (admin functionality)     |

### AuthController
| Method | Endpoint                                   | Description                              |
|--------|-------------------------------------------|------------------------------------------|
| POST   | `/api/auth/login`                         | User login                               |

## Contributing
Contributions are welcome! Please fork the repository and create a pull request with your changes. Ensure you follow coding standards and include relevant tests.

1. Fork the repository.
2. Create a new branch:
   ```bash
   git checkout -b feature/your-feature
   ```
3. Commit your changes:
   ```bash
   git commit -am 'Add some feature'
   ```
4. Push to the branch:
   ```bash
   git push origin feature/your-feature
   ```
5. Create a new Pull Request.

## License
This project is licensed under the MIT License. See the LICENSE file for details.


