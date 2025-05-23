<h1 align="center">📘 Hệ Thống Quản Lý Câu Hỏi Trắc Nghiệm</h1>

<p align="center">
  Một nền tảng hiện đại dành cho giáo viên và quản trị viên để quản lý câu hỏi, đề thi, và học sinh thi thử trực tuyến.  
</p>

<p align="center">
  <img src="https://img.shields.io/badge/react-18-blue?logo=react" />
  <img src="https://img.shields.io/badge/vite-5-purple?logo=vite" />
  <img src="https://img.shields.io/badge/springboot-JWT-green?logo=springboot" />
  <img src="https://img.shields.io/badge/Oracle-DB-F80000?logo=oracle" />

</p>
## ✨ <h3>Tính năng nổi bật</h3>

- 🔐 **Đăng nhập bảo mật bằng JWT**
- 🗃️ **Quản lý lớp học, môn học, chương, mức độ**
- 📤 **Import đề từ Word/Excel (hỗ trợ đề thi có hình ảnh)**
- 💻 **Livestream chữa đề trực tiếp**
- 📝 **Tạo - sửa - xoá câu hỏi trắc nghiệm nhanh chóng**
- 📊 **Thống kê & biểu đồ kết quả học tập**
- 💻 **Giao diện học sinh thi thử**
- 🎯 **Tối ưu hiệu năng với Vite + React + REST API**
- 💸 **Sinh viên có thể nạp tiền để mua đề thi**
- 🏦 **Giáo viên có thể rút tiền thu được từ đề thi của mình**

  
 <h3>⚙️Công nghệ sử dụng </h3></br>
🛠️ Backend (Spring Boot)
<pre>      Kiến trúc: MCV 
      CSDL: Oracle
      Phiên bản: Spring Boot 3
      Bảo mật: Spring Security (JWT),OAuth2 (gmail)
      Làm việc CSDL: JPA / Hibernate
      Giao tiếp: RESTfull API
      Phân Quyền:Role-based Authorization
      File: MultipartFile    
      RealTime(live): WebSocket</pre>
🖥️ Frontend (React + Vite)
<pre>      Phiên bản: React 18
                 Vite 5
                 Bootstrap 5
                 React Router v6
      Thông báo: React Toastify
      Biểu mẫu: Recharts
      Import: Xử lý Word/Excel với mammoth, xlsx 
      Live: Xử lí livestream với WebRTC + WebSocket + MediaStream API</pre>


📦 Cài đặt
1.  Cài đặt Backend
Clone dự án: 
<pre>Clone dự án: git clone https://github.com/hoangpd2001/TNOL_BE.git

Truy cập vào thư mục dự án

Vào file porm.xml để cài đặt các dependency

Vào file GlobalCorsConfig để cấu hình lại CORS 
  
Chạy dự án
</pre>
3. Cài đặt Frontend
<pre>Clone dự án: git clone https://github.com/hoangpd2001/TNOL_FE_U.git
                       https://github.com/hoangpd2001/TNOL_FE_.git

Truy cập vào thư mục dự án

Cài đặt các package: npm install

Chạy dự án: npm run start
</pre>

💡 Ghi chú
Cần có Java 17+ và Node.js v18+

Đảm bảo backend đã bật CORS nếu gọi từ React

