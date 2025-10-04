Employee Management System

![Login Page](Screenshots/loginPage.png)
![Dashboard](Screenshots/dashboard.png)

A modern, secure, and feature-rich Employee Management System built with Spring Boot, Thymeleaf, and MongoDB. Manage employee records efficiently with role-based access control, advanced search, and an intuitive UI.

📋 Table of Contents

Features

Screenshots

Technology Stack

Prerequisites

Installation & Setup

Usage

API Endpoints

Security

Project Structure

Docker Deployment

Future Enhancements

Contributing

License

✨ Features
Core

✅ CRUD Operations: Create, Read, Update, Delete employees

✅ Advanced Search: By name, email, or role

✅ Role-Based Access: Admin & User permissions

✅ Dashboard Analytics: Employee stats & salary overview

✅ Input Validation & Unique Email Constraint

Security

🔒 Spring Security & BCrypt password hashing

🔒 CSRF protection & secure session management

🔒 Admin-only sensitive operations

UI/UX

🎨 Modern gradient design & responsive layout

🎨 Smooth animations, Font Awesome icons, and alert notifications

🎨 Intuitive navigation for all users

📸 Screenshots

Login Page: Secure authentication

Dashboard: Employee management and analytics

🛠 Technology Stack

Backend: Spring Boot, Spring Security, MongoDB, Thymeleaf, Bean Validation
Frontend: Bootstrap, Font Awesome, Custom CSS, JS
Database: MongoDB
Build & Deployment: Maven, Docker, Docker Compose

📋 Prerequisites

JDK 17+

Maven 3.6+

MongoDB 7.0+ (or Docker)

Git

🚀 Installation & Setup
Local

git clone https://github.com/yourusername/employee-management-system.git

Start MongoDB (Docker or local)

Configure application.properties

Build & run:

./mvnw clean install
./mvnw spring-boot:run


Access: http://localhost:8080

Docker
docker-compose up --build
http://localhost:8080
docker-compose down

👥 Usage

Default Credentials

Role	Username	Password	Access
Admin	admin	admin123	Full CRUD
User	user	user123	Read-only

Admin Features: Add, Search, Update, Delete employees; View stats
User Features: View & search employees; Access dashboard only

🔌 API Endpoints
Method	Endpoint	Access
GET	/	Authenticated
POST	/login	Public
POST	/logout	Authenticated
POST	/create	Admin
POST	/update	Admin
POST	/remove	Admin
GET	/?search={keyword}	Authenticated
🔐 Security

Authentication via Spring Security with BCrypt

Admin: Full CRUD, User: Read-only

CSRF, XSS protection & input validation

📁 Project Structure
employee-management-system/
├── src/main/java/com/ems/
│   ├── config/ SecurityConfig.java
│   ├── controller/ EmployeeController.java, AuthController.java
│   ├── service/ EmployeeService.java
│   ├── pojo/ Employee.java, ConfirmationForm.java
│   ├── repo/ EmployeeRepo.java
│   └── EmployeeManagementSystemApplication.java
├── src/main/resources/templates/ index.html, update.html, login.html
├── application.properties
├── Dockerfile, docker-compose.yml
├── pom.xml
└── README.md

🐳 Docker Deployment
docker build -t employee-management-system .
docker run -p 8080:8080 employee-management-system


Deploy on Render, Railway, or Heroku with MONGODB_URI environment variable.

🎯 Future Enhancements

Export to Excel/PDF

Email notifications

Profile picture upload

Advanced filtering & pagination

Dark mode & audit logs

REST API for mobile

Multi-language support & attendance tracking

🤝 Contributing

Fork repository

Create feature branch

Commit changes

Push & open PR

📄 License

MIT License

👨‍💻 Author

Your Name
GitHub: @yourusername | LinkedIn: Your LinkedIn | Email: your.email@example.com

🙏 Acknowledgments

Spring Boot, MongoDB, Bootstrap, Font Awesome

📞 Support

Open an Issue or email: your.email@example.com
