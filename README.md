# DataPersistenceDemo

## Experiment: Data Persistence Using SQLite in Android

### Aim

To develop an Android application that demonstrates data persistence using SQLite database for storing, retrieving, and managing student information.

---

## Experiment Overview

This experiment demonstrates how data can be stored permanently in an Android application using a local SQLite database.

The application contains a login screen and a student data management system. The user can enter login credentials, add student details, view saved student records, and clear the stored student records.

The application uses `SQLiteOpenHelper` to create and manage the SQLite database.

---

## Concept / Technology Used

### SQLite Database

SQLite is a lightweight, local relational database provided by Android. It allows applications to store structured data permanently on the device.

In this application, SQLite is used to store student information and login credentials.

### SQLiteOpenHelper

`SQLiteOpenHelper` is used to:

- Create the SQLite database
- Create database tables
- Upgrade the database
- Insert records
- Retrieve records
- Delete records

### Data Persistence

Data persistence means that data remains stored even after navigating between screens or closing and reopening the application.

---

## Scenario

A student management application is developed for storing student details.

The application follows these steps:

1. The user enters a username and password on the Login screen.
2. After successful login, the user is taken to the Add Student Details screen.
3. The user enters Student ID, Name, and Course.
4. The student information is stored in the SQLite database.
5. The saved records are displayed in the Student Database screen.
6. The user can clear all student records using the Clear All button.
7. The user can logout and return to the Login screen.

---

## Technologies Used

- Android Studio
- Kotlin
- XML
- SQLite
- SQLiteOpenHelper
- Android SDK

---

# Application Features

- User Login
- Username and Password handling
- Add Student Details
- SQLite data storage
- Retrieve saved student records
- Display student records
- Clear all student records
- Logout functionality
- SQLite Database inspection

---

# Database Structure

The application uses a SQLite database named:

`StudentDB`

### Students Table

| Column | Data Type | Description |
|---|---|---|
| id | INTEGER | Student ID |
| name | TEXT | Student Name |
| course | TEXT | Student Course |

### Login Table

| Column | Data Type | Description |
|---|---|---|
| username | TEXT | Login username |
| password | TEXT | Login password |

The login details are stored in SQLite through `DatabaseHelper.kt`.

---

# Project Folder and File Structure

```text
DataPersistenceDemo
│
├── app
│   ├── src
│   │   └── main
│   │       ├── java
│   │       │   └── com.example.datapersistencedemo
│   │       │       ├── MainActivity.kt
│   │       │       ├── LoginFragment.kt
│   │       │       ├── AddDataFragment.kt
│   │       │       ├── DatabaseFragment.kt
│   │       │       └── DatabaseHelper.kt
│   │       │
│   │       └── res
│   │           ├── drawable
│   │           ├── layout
│   │           │   ├── activity_main.xml
│   │           │   ├── fragment_login.xml
│   │           │   ├── fragment_add_data.xml
│   │           │   └── fragment_database.xml
│   │           └── values
│   │
│   └── build.gradle.kts
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
