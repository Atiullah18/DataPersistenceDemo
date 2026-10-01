# DataPersistenceDemo

## Experiment: Data Persistence Using SQLite in Android

### Student Details

- **Name:** Md Atiullah Ansari
- **USN:** YOUR_USN
- **Course:** Master of Computer Applications (MCA)
- **University:** Jain (Deemed-to-be University), Bangalore

---

## Aim

To develop an Android application that demonstrates data persistence using SQLite database for storing, retrieving, and managing student information.

---

## Experiment Overview

This experiment demonstrates data persistence in Android using a local SQLite database.

The application allows the user to login, enter student details, store the details in SQLite, retrieve saved records, display the records, clear the records, and logout.

---

## Concept / Technology Used

### SQLite Database

SQLite is a lightweight relational database used in Android applications to store structured data locally on the device.

### SQLiteOpenHelper

`SQLiteOpenHelper` is used to create and manage the SQLite database. It is responsible for database creation, table creation, database upgrades, and database operations.

### Data Persistence

Data persistence means that data remains stored even after navigating between screens or restarting the application.

---

## Scenario

A Student Data Management application is developed using Android Studio, Kotlin, XML, and SQLite.

The application starts with a Login screen where the user enters a username and password.

After login, the user enters Student ID, Student Name, and Course.

The student information is stored in the local SQLite database and can be retrieved and displayed in the Student Database screen.

The user can also clear all student records and logout from the application.

---

## Technologies Used

- Android Studio
- Kotlin
- XML
- SQLite Database
- SQLiteOpenHelper
- Android SDK

---

## Application Features

- User Login
- Username and Password handling
- Add Student Details
- Store student information in SQLite
- Retrieve student records
- Display saved records
- Clear all student records
- Logout functionality
- SQLite database verification
- Data persistence

---

# Database Structure

The application uses a SQLite database named **StudentDB**.

### Students Table

| Column | Data Type | Description |
|---|---|---|
| id | INTEGER | Student ID |
| name | TEXT | Student Name |
| course | TEXT | Student Course |

### Login Table

| Column | Data Type | Description |
|---|---|---|
| username | TEXT | Login Username |
| password | TEXT | Login Password |

The login details are stored in SQLite using `DatabaseHelper.kt`.

---

# Project Folder and File Structure

```text
DataPersistenceDemo
│
├── app
│   └── src
│       └── main
│           ├── java
│           │   └── com.example.datapersistencedemo
│           │       ├── MainActivity.kt
│           │       ├── LoginFragment.kt
│           │       ├── AddDataFragment.kt
│           │       ├── DatabaseFragment.kt
│           │       └── DatabaseHelper.kt
│           │
│           └── res
│               ├── drawable
│               ├── layout
│               │   ├── activity_main.xml
│               │   ├── fragment_login.xml
│               │   ├── fragment_add_data.xml
│               │   └── fragment_database.xml
│               └── values
│
├── Screenshots
│   ├── LoginScreen.png
│   ├── AddStudentData.png
│   ├── StudentDatabase.png
│   ├── SQLiteDatabase.png
│   └── DatabaseRecords.png
│
├── .gitignore
├── build.gradle.kts
├── gradle.properties
├── gradlew
├── gradlew.bat
└── settings.gradle.kts
