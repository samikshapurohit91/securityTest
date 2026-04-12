SecurityTest - Spring Boot Security Project

A complete Authentication & Authorization System built using Spring Boot and Spring Security.

- Features
  JWT Authentication (Access Token)
  
  Refresh Token Implementation
  
  Secure Logout (Token Blacklisting)
  
  Role-Based Authorization (USER / ADMIN)
  
  Password Encryption (BCrypt)
  
  OAuth2 Login (Google)
  
  Swagger API Documentation
  
  Exception Handling
  

- Tech Stack 
  Java 17 
  Spring Boot 
  Spring Security 
  JWT (JSON Web Token) 
  Hibernate / JPA 
  MySQL 
  Maven 
  Swagger (OpenAPI) 

- Project Structure

src/
├── controller
├── service
├── repository
├── entity
├── security
└── config

- API Endpoints
  Authentication
Method	Endpoint       	Description
POST	 /auth/register  	Register user
POST	 /auth/login	    Login user
POST	 /auth/refresh	  Generate new access token
POST	 /auth/logout	    Logout (Blacklist token)

Authentication Flow
User registers/login
Server returns:
Access Token (JWT)
Refresh Token
Access Token used for API calls
When expired → use Refresh Token
Logout → token added to blacklist

- Security Implementation
JWT-based authentication
Role-based access control
Token validation filter (JwtFilter)
Password hashing using BCrypt
OAuth2 login integration


- Swagger UI

http://localhost:8080/swagger-ui/index.html

- Setup Instructions
Clone the repository:
git clone https://github.com/samikshapurohit91/securityTest.git
Configure MySQL in application.properties
Run the project:
mvn spring-boot
 Testing
Use Postman for API testing
Use Swagger UI for testing

- Future Enhancements
Email verification
Forgot password
Rate limiting
API logging


Author- 

Samiksha Purohit
