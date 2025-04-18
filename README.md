Chatbot Spring Boot
Giới thiệu
Dự án Chatbot Spring Boot là một ứng dụng web sử dụng Spring Boot tích hợp trí tuệ nhân tạo (AI) để xây dựng chatbot thông minh. Ứng dụng sử dụng:

Spring AI để kết nối với OpenAI API.
PostgreSQL làm cơ sở dữ liệu.
Google OAuth2 để xác thực người dùng.
FastText cho xử lý ngôn ngữ tự nhiên.

Dự án được triển khai trên Railway và hỗ trợ chạy cục bộ để phát triển.
Yêu cầu

Java: 17 hoặc cao hơn
Maven: 3.8.0 hoặc cao hơn
PostgreSQL: 13 hoặc cao hơn (nếu chạy cục bộ)
Python (cho git filter-repo nếu cần chỉnh sửa lịch sử Git)
Tài khoản:
OpenAI (cho API key)
Google Cloud (cho OAuth2 Client ID/Secret)
Railway (cho triển khai)



Cài đặt
1. Clone repository
   git clone https://github.com/Min-Trees/chatbot_springboot.git
   cd chatbot_springboot

2. Cấu hình biến môi trường
   Tạo file .env trong thư mục gốc dự án với các biến sau:
   SPRING_APPLICATION_NAME=SpringAI
   SPRING_AI_OPENAI_API_KEY=<your-openai-api-key>
   SPRING_DATASOURCE_URL=jdbc:postgresql://<host>:<port>/<database>
   SPRING_DATASOURCE_USERNAME=<your-username>
   SPRING_DATASOURCE_PASSWORD=<your-password>
   SPRING_DATASOURCE_DRIVER_CLASS_NAME=org.postgresql.Driver
   SPRING_JPA_DATABASE_PLATFORM=org.hibernate.dialect.PostgreSQLDialect
   SPRING_JPA_HIBERNATE_DDL_AUTO=update
   SPRING_JPA_SHOW_SQL=true
   SPRING_CACHE_TYPE=simple
   FASTTEXT_MODEL_PATH=model.bin
   FASTTEXT_TRAINING_DATA_PATH=training_data.txt
   SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_ID=<your-google-client-id>
   SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_SECRET=<your-google-client-secret>
   SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_SCOPE=openid,profile,email
   SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_REDIRECT_URI=http://localhost:8080/login/oauth2/code/google

Lưu ý:

Không commit file .env lên Git. File này đã được thêm vào .gitignore.
Thay <your-...> bằng giá trị thực tế từ OpenAI, Google Cloud, hoặc database của bạn.

3. Cài đặt dependencies
   Chạy lệnh để cài đặt các thư viện Maven:
   mvn clean install

4. Đặt file FastText

Đặt file model.bin và training_data.txt vào thư mục được chỉ định trong FASTTEXT_MODEL_PATH và FASTTEXT_TRAINING_DATA_PATH.
Nếu dùng đường dẫn tương đối, đặt chúng trong thư mục gốc hoặc src/main/resources.

Chạy ứng dụng
Chạy cục bộ
mvn spring-boot:run


Ứng dụng sẽ chạy tại http://localhost:8080.
Đăng nhập bằng Google OAuth2 để truy cập các tính năng chatbot.

Triển khai trên Railway

Đẩy code lên GitHub (đã xóa secrets khỏi lịch sử).
Kết nối repository với Railway.
Thêm các biến môi trường từ file .env vào phần Variables trên Railway dashboard.
Triển khai ứng dụng và kiểm tra URL được cung cấp.

Cấu trúc dự án
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


SpringAiApplication.java: Điểm khởi chạy ứng dụng, load biến môi trường từ .env.
application.properties: Cấu hình Spring Boot với placeholder cho biến môi trường.
.gitignore: Bỏ qua .env, target/, và các file nhạy cảm khác.

Bảo mật

Không commit secrets: File .env chứa API key, mật khẩu, và OAuth secrets không được commit.
GitHub Push Protection: Nếu gặp lỗi push do secrets, xóa chúng khỏi lịch sử Git bằng git filter-repo:pip install git-filter-repo
git filter-repo --path .env --invert-paths --force
git filter-repo --replace-text secrets.txt --force


Tạo lại secrets: Nếu secrets bị lộ, tạo lại key trên OpenAI, Google Cloud, và đổi mật khẩu database.

Góp ý
Nếu bạn có ý tưởng cải thiện dự án, hãy mở issue hoặc gửi pull request trên GitHub.
Liên hệ

GitHub: Min-Trees
Email: Liên hệ qua GitHub issues.

