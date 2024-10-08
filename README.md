Libertapp Backend

Libertapp is a web platform designed to help women who are victims of domestic violence by providing them a space to register and find job opportunities, empowering them to escape vulnerable situations.
Features

    User authentication and role-based access (admin, victim, organizations, companies)
    Registration and management of users, victims, and organizations
    Job posting and management for companies
    Secure communication via JWT token
    RESTful API built with Spring Boot

Technology Stack

    Spring Boot: Backend framework
    MySQL: Database
    JWT: Authentication
    Spring Security: Role-based access control
    Hibernate: ORM for database interactions

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
   

Contributing

Contributions are welcome! Please open an issue or submit a pull request.
License

This project is licensed under the MIT License.
