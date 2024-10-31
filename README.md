Libertapp Backend

Libertapp is a web platform designed to empower women facing domestic violence. It provides a safe space to register and find job opportunities, enabling them to escape vulnerable situations.
Features

    User Authentication and Role-Based Access Control (RBAC): The system differentiates between admins, victims, organizations, and companies, granting specific permissions for each role.
    User Management: Registration and management of users, victims, and organizations.
    Job Posting and Management: Companies can post and manage job opportunities.
    Secure Communication: Secure communication is ensured through JWT tokens.
    RESTful API built with Spring Boot: The backend leverages Spring Boot for a robust and efficient API.

Technology Stack

    Backend Framework: Spring Boot
    Database: MySQL
    Authentication: JWT
    Role-Based Access Control: Spring Security
    Database Interactions: Hibernate (Object-Relational Mapper)

Setup

    Clone the repository

    bash
    git clone https://github.com/yourusername/libertapp-backend.git

Navigate to the project directory:


cd libertapp-backend

Configure the database connection in application.properties.
Build and run the application:

bash

    ./mvnw spring-boot:run

API Endpoints

    /auth/login - User login with JWT token generation
    /auth/register - Register new users (victims, organizations, companies)
   
Additional Documentation:

Libertapp Backend offers comprehensive documentation for developers:

    Javadoc: Provides detailed comments within the source code for better understanding of functionalities.
    Swagger: Offers an interactive API documentation interface for exploring available endpoints and their functionalities.

Contributing

Contributions are welcome! Please open an issue or submit a pull request.
License

This project is licensed under the MIT License.
