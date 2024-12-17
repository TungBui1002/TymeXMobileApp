# TymeX's Mobile App

## Overview

**TymeX's Mobile App** is an **admin application** for viewing user lists from **GitHub API**. The application supports the following main features:

- **Load user list** page by page (**20 items per**).
- **Save user data** with **SharedPreferences** to display immediately when reopening the app.
- **View user details**, including:
  - Avatar
  - Name
  - Location
  - Blog
  - Number of followers and following.

### Technology used

The application is developed in **Kotlin** and uses the following technologies:

- **MVVM Architecture**: Architecture that helps manage data flow clearly and easily maintained.
- **Retrofit**: Library that connects to REST API.
- **Coroutines**: Support for handling asynchronous tasks.
- **Glide**: Library for loading and displaying images.
- **Unit Testing**: Ensure code quality with **JUnit** and **mockk**.

---

## Features

1. **Display user list**
- User can scroll down to load more data (**Pagination**).

2. **Save data to memory**
- Use **SharedPreferences** to store old data and display even when there is no network.

3. **View user details**
- Details are displayed when clicking on a user in the list:

- Avatar, Name, Location, Blog, Number of followers and following.

4. **Unit Testing**
- Use **JUnit** and **mockk** to test the functions of **UserRepository**.

---

## Steps to Build and Run

### 1. Prerequisites

- **Android Studio** latest version.

- **JDK 11** or higher.

- **Gradle** (automatically configured in Android Studio).

### 2. Clone Project

Clone the project to your computer using the following command:

```bash
git clone https://github.com/yourusername/TymeX.git
```

### 3. Open Project in Android Studio 
1. Open **Android Studio**.
2. Select **Open an existing project** and select the project directory.

---

### 4. Build and Run
- Select **Build > Rebuild Project** to ensure all dependencies are downloaded.

- Run the app on a real device or emulator. (I used Pixel 7 Pro API 35)

---

### Challenges and Notes

#### 1. Pagination:
- The `since` variable is managed by increasing by 20 for each new data fetch.

#### 2. API Rate Limit:
- Since you use **GitHub API**, you need to pay attention to limit the number of API calls per hour.

*(**Personal Access Token** is recommended if needed).*

### 3. Unit Testing:
- Use **mockk** to mock data from **UserRepository**.

### 4. SharedPreferences:
- Data is stored locally for quick re-display when the application is opened next time.

---

## Dependencies

The project uses the following libraries:

- **Retrofit**: Connect to API.

- **Glide**: Load and display images.

- **Coroutines**: Handle asynchronous tasks.

- **mockk**: Mock library for Unit Test.

- **JUnit**: Testing framework.

```gradle
dependencies {
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2"
    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-gson:2.9.0"
    implementation "com.github.bumptech.glide:glide:4.16.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1"
    testImplementation "io.mockk:mockk:1.13.3"
    testImplementation "junit:junit:4.13.2"
    testImplementation "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.1"
}
```
---

### Demo Video (Optional)
[https://youtu.be/8KcAx5BA1NU](https://youtu.be/8KcAx5BA1NU)

[![Demo on YouTube](https://img.youtube.com/vi/8KcAx5BA1NU/0.jpg)](https://youtu.be/8KcAx5BA1NU)

---

### Contact

If you have any questions, please contact:

- **Email**: tungb100203@gmail.com
- **GitHub**: [github.com/TungBui1002](https://github.com/TungBui1002)
