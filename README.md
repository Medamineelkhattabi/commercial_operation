# HSpring Exam Projects

This repository contains three Spring Boot microservices designed for managing commercial operations, stock, and sales. Each project is structured independently but can interact with each other to provide a complete business solution.

## Projects Overview

### 1. Gestion Commercial
- **Description**: Handles product management and global orders.
- **Key Features**:
  - Add new products.
  - Manage global orders.
- **Technologies**:
  - Spring Boot (Web, Data JPA).
  - MySQL.

### 2. Gestion Stock
- **Description**: Manages product stock levels.
- **Key Features**:
  - Retrieve stock information.
  - Update stock levels after sales.
- **Technologies**:
  - Spring Boot (Web, Data JPA).
  - MySQL.

### 3. Gestion Vente
- **Description**: Manages product catalog and customer orders.
- **Key Features**:
  - Display product catalog.
  - Handle customer orders.
  - Generate invoices.
- **Technologies**:
  - Spring Boot (Web, Thymeleaf, Security).
  - MySQL.

## Prerequisites
- Java 17 or higher.
- Maven 4.0.0.
- MySQL database.

## Setup Instructions

### Step 1: Clone the Repository
```bash
# Clone the repository to your local machine
git clone <repository-url>
```

### Step 2: Configure Databases
- Update the `application.properties` files in each project with your MySQL credentials.
  - Example:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/<database_name>
    spring.datasource.username=<your_username>
    spring.datasource.password=<your_password>
    ```

### Step 3: Build and Run Each Project
- Navigate to each project directory and run the following commands:
```bash
# Build the project
mvn clean install

# Run the project
mvn spring-boot:run
```

### Step 4: Access the Applications
- **Gestion Commercial**: [http://localhost:8081](http://localhost:8081)
- **Gestion Stock**: [http://localhost:8082](http://localhost:8082)
- **Gestion Vente**: [http://localhost:8083](http://localhost:8083)

## Project Structure
Each project follows the standard Spring Boot structure:
```
project_name/
├── src/
│   ├── main/
│   │   ├── java/       # Java source code
│   │   └── resources/  # Configuration files and templates
│   └── test/           # Test cases
├── pom.xml             # Maven configuration
```

## Key Endpoints

### Gestion Commercial
- `GET /api/commercial/produits`: Retrieve all products.
- `POST /api/commercial/produits`: Add a new product.
- `POST /api/commercial/commandes`: Save a global order.

### Gestion Stock
- `GET /api/stock/produits`: Retrieve all stock items.
- `PUT /api/stock/produits-stock/reduire/{codeProduit}/{quantite}`: Reduce stock for a product.

### Gestion Vente
- `GET /produits`: Display product catalog.
- `GET /commande/nouvelle/{codeProduit}`: Display order form for a product.

## Contribution
Feel free to fork this repository and submit pull requests. For major changes, please open an issue first to discuss what you would like to change.

## License
This project is licensed under the MIT License. See the LICENSE file for details.