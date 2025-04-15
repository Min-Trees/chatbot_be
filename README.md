# 🤖 Chatbot Spring Boot

## Giới thiệu
Chatbot Spring Boot là một ứng dụng web được xây dựng bằng Spring Boot, tích hợp trí tuệ nhân tạo (AI) để tạo ra một chatbot thông minh. Dự án sử dụng các công nghệ sau:

- **Spring AI** để xử lý các tác vụ AI.
- **PostgreSQL** làm cơ sở dữ liệu.
- **Google OAuth2** để xác thực người dùng.
- **FastText** để hỗ trợ xử lý ngôn ngữ tự nhiên.

Ứng dụng có thể chạy cục bộ để phát triển hoặc được triển khai trên nền tảng như Railway.

## Yêu cầu
Để chạy dự án, bạn cần:

- **Java**: Phiên bản 17 hoặc cao hơn
- **Maven**: Phiên bản 3.8.0 hoặc cao hơn
- **PostgreSQL**: Phiên bản 13 hoặc cao hơn (nếu chạy cục bộ)

### Tài khoản cần thiết
- **Tài khoản OpenAI** (cho API AI)
- **Tài khoản Google Cloud** (cho xác thực OAuth2)
- **Tài khoản Railway** (cho triển khai, nếu cần)

## 🚀 Cài đặt

### 1. Clone repository
```bash
git clone https://github.com/Min-Trees/chatbot_springboot.git
cd chatbot_springboot
```

### 2. Cài đặt dependencies
```bash
mvn clean install
```

### 3. Cấu hình FastText
- Đảm bảo các file `model.bin` và `training_data.txt` được đặt đúng vị trí theo cấu hình FastText của dự án (thường trong thư mục `src/main/resources` hoặc thư mục gốc).
- Liên hệ với nhóm phát triển nếu cần thêm thông tin về file mô hình.

## 🧪 Chạy ứng dụng

### 🔧 Chạy cục bộ
```bash
mvn spring-boot:run
```
- Ứng dụng sẽ hoạt động tại: [http://localhost:8080](http://localhost:8080)
- Sử dụng Google OAuth2 để đăng nhập và tương tác với chatbot

### ☁️ Triển khai trên Railway
- Đẩy code lên GitHub sau khi đảm bảo không chứa thông tin nhạy cảm
- Kết nối repository với Railway qua dashboard
- Cấu hình ứng dụng theo hướng dẫn của Railway
- Kiểm tra URL triển khai để truy cập chatbot

## 📁 Cấu trúc dự án
```
chatbot_springboot/
├── src/
│   ├── main/
│   │   ├── java/com/ai/SpringAI/
│   │   │   └── SpringAiApplication.java
│   │   └── resources/
│   │       └── application.properties
├── .gitignore
├── pom.xml
└── README.md
```

- `SpringAiApplication.java`: Điểm khởi chạy của ứng dụng Spring Boot
- `application.properties`: File cấu hình chính cho Spring Boot
- `.gitignore`: Đảm bảo các file nhạy cảm (như file môi trường) không được commit

## 🔐 Bảo mật

- Không commit thông tin nhạy cảm: Các thông tin như API key, mật khẩu, hoặc OAuth secrets phải được quản lý an toàn và không được đẩy lên repository

- **GitHub Push Protection**: Nếu gặp lỗi đẩy code do chứa secrets, sử dụng công cụ như git filter-repo để xóa thông tin nhạy cảm khỏi lịch sử Git:
```bash
pip install git-filter-repo
git filter-repo --replace-text secrets.txt --force
```

- **Quản lý secrets**: Liên hệ nhóm phát triển để biết cách cấu hình an toàn trên môi trường cục bộ hoặc triển khai

## 💡 Góp ý

Nếu bạn muốn đóng góp vào dự án:
- Mở issue để báo lỗi hoặc đề xuất tính năng
- Gửi pull request với các cải tiến code

## 📬 Liên hệ
- **GitHub**: [Min-Trees](https://github.com/Min-Trees)
- **Hỗ trợ**: Liên hệ qua GitHub Issues của repository

---
## 🧰 Cài đặt & Cấu hình

### Yêu cầu
- Java JDK 17 hoặc cao hơn
- Maven
- PostgreSQL đã cài đặt

### Bước 1: Tạo cơ sở dữ liệu
Dùng công cụ PostgreSQL như pgAdmin và chạy:
```sql
CREATE DATABASE education_system;
```

### Bước 2: Cấu hình file `application.properties`
Chỉnh sửa file `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/education_system
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
```

## 🚀 Chạy dự án

### 1. Clone repository
```bash
git clone https://github.com/Min-Trees/Backend_ABC_English.git
cd Backend_ABC_English
```

### 2. Cài đặt dependencies
```bash
mvn clean install
```

### 3. Chạy dự án
```bash
mvn spring-boot:run
```

Ứng dụng sẽ hoạt động tại: [http://localhost:8080](http://localhost:8080)

---

> Tài liệu này gộp hai dự án sử dụng Spring Boot: một chatbot sử dụng Spring AI và một nền tảng học tiếng Anh trực tuyến. Nếu bạn muốn chuyển nó thành PDF hoặc slide trình chiếu, mình có thể hỗ trợ.

