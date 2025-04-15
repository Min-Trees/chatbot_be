# 🤖 Chatbot Spring Boot

## 📝 Overview
Chatbot Spring Boot is a web application built with Spring Boot, integrated with artificial intelligence (AI) to provide a smart chatbot experience.

## 🛠️ Technologies Used
- **Spring AI**: Handles AI-related tasks.
- **PostgreSQL**: Used as the database.
- **Google OAuth2**: Handles user authentication.
- **FastText**: Supports natural language processing.

The application can run locally for development or be deployed on platforms like Railway.

## 📋 System Requirements
- **Java**: Version 17 or above
- **Maven**: Version 3.8.0 or above
- **PostgreSQL**: Version 13 or above (if running locally)

## 📂 Required Accounts
- **OpenAI Account** (for AI API)
- **Google Cloud Account** (for OAuth2 authentication)
- **Railway Account** (for deployment)

## 🚀 Installation

### 1. Clone Repository
```bash
git clone https://github.com/Min-Trees/chatbot_springboot.git
cd chatbot_springboot
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Configure FastText
Ensure `model.bin` and `training_data.txt` are placed in the correct location based on project configuration (typically in `src/main/resources` or the root directory).

Contact the development team for more information about the model files.

## 🧪 Run the Application

### 🔧 Run Locally
```bash
mvn spring-boot:run
```
The application will be available at: [http://localhost:8080](http://localhost:8080)

Login using Google OAuth2 to interact with the chatbot.

### ☁️ Deploy on Railway
- Push code to GitHub (ensure no sensitive information is included)
- Connect repository to Railway via dashboard
- Configure environment variables as needed
- Check the deployed URL to use the chatbot

## 📁 Project Structure
```
chatbot_springboot/
├── src/
│   └── main/
│       ├── java/com/ai/SpringAI/
│       │   └── SpringAiApplication.java
│       └── resources/
│           └── application.properties
├── .gitignore
├── pom.xml
└── README.md
```
- **SpringAiApplication.java**: Main entry point of the Spring Boot application
- **application.properties**: Configuration file
- **.gitignore**: Ensures sensitive files like API keys and local configs are not committed

## 🔐 Security
- **Do not commit sensitive information**: API keys, passwords, and OAuth secrets should be securely stored

### GitHub Push Protection
If push is blocked due to secrets, remove them using:
```bash
pip install git-filter-repo
git filter-repo --replace-text secrets.txt --force
```

- Contact the development team for safe secret configuration guidance

## 💡 Feedback & Contribution
- Open issues to report bugs or suggest features
- Submit pull requests with code or documentation improvements

## 📬 Contact
- **GitHub**: [Min-Trees](https://github.com/Min-Trees)
- **Support**: Via GitHub Issues on the repository

---

# 📘 ABC-ENGLISH (E-learning Platform)

## 🧾 Overview
ABC-ENGLISH is an e-learning platform developed using Spring Boot. It supports features such as user registration, course management, progress tracking, interactive messaging, and forums.

## 🏗️ Architecture
### Client-Server Model
- **Client**: Handles user interactions
- **Server (Spring Boot)**: Manages business logic, stores data, and provides REST APIs

## 🌟 Features
### 👤 User Registration and Login
- Two-step verification for secure sign-up
1. User submits registration form
2. System marks user as "unverified" and sends a verification email

### ✉️ Verification Email
- Email contains a verification link with a unique token

### 🧑‍💼 Profile Management
- Users can edit profile details and track learning progress

### 📚 Course Catalog
- Organized by Level: Beginner, Intermediate, Advanced
- Topics: Grammar, Vocabulary, Pronunciation

### 📖 Course Details
- Combines videos, text lessons, exercises, and quizzes
- Chapters cover specific topics with theory and practice

### 📝 Assignments & Tests
- Multiple choice and essay questions with automatic grammar evaluation

### 💬 Forum & Messaging
- Discussion space for teachers and students
- Direct messaging for queries and feedback

### 🔔 Notifications
- Alerts for new lessons
- Reminders for upcoming deadlines

## 🧰 Setup Instructions

### ⚙️ Prerequisites
- Java 17+
- Maven
- SQL Server

### 🛠️ Setup SQL Server Database
1. Open SSMS and run:
```sql
CREATE DATABASE EducationSystem1;
```

2. Configure `application.yaml` in `src/main/resources` to connect to the database

### ▶️ Run the Project
```bash
git clone https://github.com/Min-Trees/Backend_ABC_English.git
cd <project-folder>
mvn clean install
```

## 🖼️ Screenshots
- Login: `screenshot/login.jpg`
- Verify Email: `screenshot/verify_email.jpg`
- Profile: `screenshot/profile.jpg`
- Course List: `screenshot/course.jpg`
- Course Detail: `screenshot/detail_course1.jpg` to `detail_course3.jpg`
- Test & Auto-Grading: `screenshot/test_mutichoi
