TymeX's Mobile App

Overview

o TymeX's Mobile App is an admin application to view a list of users from GitHub API. The application supports:

o Loading a list of users page by page (20 items at a time).

o Saving user data using SharedPreferences to display immediately when reopening the application.

o Viewing user details including avatar, name, location, blog, number of followers and following.

The application is developed in Kotlin and uses the following technologies:

o MVVM Architecture.

o Retrofit for REST API.

o Coroutines for asynchronous processing.

o Glide for loading images.

o Unit Testing with JUnit and mockk.

Features
1. Display user list:
  o Scroll down to load more data (pagination).
2. Save data to memory:
  o SharedPreferences is used to display old data when offline.
3. View user details:
  o Show detailed information when clicking on a user in the list.
4. Unit Testing:
  o Ensure code quality with JUnit and mockk.

