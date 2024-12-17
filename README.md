# TymeX Mobile App

## Overview

**TymeX** là một ứng dụng **dành cho quản trị viên** để xem danh sách người dùng từ **GitHub API**. Ứng dụng hỗ trợ các tính năng chính sau:

- **Tải danh sách người dùng** theo từng trang (**20 mục mỗi lần**).
- **Lưu dữ liệu người dùng** bằng **SharedPreferences** để hiển thị ngay lập tức khi mở lại ứng dụng.
- **Xem chi tiết người dùng**, bao gồm:
  - Avatar
  - Tên
  - Vị trí
  - Blog
  - Số lượng followers và following.

### Công nghệ sử dụng

Ứng dụng được phát triển bằng **Kotlin** và sử dụng các công nghệ sau:

- **MVVM Architecture**: Kiến trúc giúp quản lý luồng dữ liệu rõ ràng và dễ bảo trì.
- **Retrofit**: Thư viện kết nối với REST API.
- **Coroutines**: Hỗ trợ xử lý các tác vụ bất đồng bộ.
- **Glide**: Thư viện tải và hiển thị hình ảnh.
- **Unit Testing**: Đảm bảo chất lượng code với **JUnit** và **mockk**.

---

## Features

1. **Hiển thị danh sách người dùng**
   - Người dùng có thể cuộn xuống để tải thêm dữ liệu (**Pagination**).

2. **Lưu dữ liệu vào bộ nhớ**
   - Sử dụng **SharedPreferences** để lưu trữ dữ liệu cũ và hiển thị ngay cả khi không có mạng.

3. **Xem chi tiết người dùng**
   - Thông tin chi tiết được hiển thị khi click vào một user trong danh sách:
     - Avatar, Tên, Vị trí, Blog, Số lượng followers và following.

4. **Kiểm thử đơn vị (Unit Testing)**
   - Sử dụng **JUnit** và **mockk** để kiểm thử các chức năng của **UserRepository**.

---

## Steps to Build and Run

### 1. Prerequisites

- **Android Studio** phiên bản mới nhất.
- **JDK 11** hoặc cao hơn.
- **Gradle** (được cấu hình tự động trong Android Studio).

### 2. Clone Project

Clone dự án về máy tính của bạn bằng lệnh sau:

```bash
git clone https://github.com/yourusername/TymeX.git
