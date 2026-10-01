package com.example.datapersistencedemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "StudentDB", null, 2) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(
            """
            CREATE TABLE students (
                id INTEGER PRIMARY KEY,
                name TEXT NOT NULL,
                course TEXT NOT NULL
            )
            """.trimIndent()
        )

        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS login (
                username TEXT,
                password TEXT
            )
            """.trimIndent()
        )
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        db.execSQL("DROP TABLE IF EXISTS students")
        db.execSQL("DROP TABLE IF EXISTS login")
        onCreate(db)
    }

    fun getLoginCount(): Int {
        val db = readableDatabase
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS login (
                username TEXT,
                password TEXT
            )
            """.trimIndent()
        )
        val cursor = db.rawQuery("SELECT COUNT(*) FROM login", null)
        var count = 0
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return count
    }

    fun getAllLoginDetails(): List<Pair<String, String>> {
        val list = ArrayList<Pair<String, String>>()
        val db = readableDatabase
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS login (
                username TEXT,
                password TEXT
            )
            """.trimIndent()
        )
        val cursor = db.rawQuery("SELECT username, password FROM login", null)
        if (cursor.moveToFirst()) {
            do {
                val uIndex = cursor.getColumnIndex("username")
                val pIndex = cursor.getColumnIndex("password")
                val u = if (uIndex != -1) cursor.getString(uIndex) ?: "" else ""
                val p = if (pIndex != -1) cursor.getString(pIndex) ?: "" else ""
                list.add(Pair(u, p))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return list
    }

    fun saveLoginDetails(
        username: String,
        password: String
    ): Boolean {
        val db = writableDatabase
        db.execSQL(
            """
            CREATE TABLE IF NOT EXISTS login (
                username TEXT,
                password TEXT
            )
            """.trimIndent()
        )

        // Check if username already exists in login table
        val cursor = db.rawQuery(
            "SELECT rowid FROM login WHERE username = ?",
            arrayOf(username)
        )
        val exists = cursor.moveToFirst()
        cursor.close()

        val values = ContentValues().apply {
            put("username", username)
            put("password", password)
        }

        if (exists) {
            db.update("login", values, "username = ?", arrayOf(username))
        } else {
            db.insert("login", null, values)
        }
        db.close()
        return true
    }

    fun clearAllLoginData(): Int {
        val db = writableDatabase
        val rowsDeleted = db.delete("login", null, null)
        db.close()
        return rowsDeleted
    }

    fun insertStudent(
        id: Int,
        name: String,
        course: String
    ): Boolean {

        val db = writableDatabase

        val values = ContentValues()
        values.put("id", id)
        values.put("name", name)
        values.put("course", course)

        val result = db.insert(
            "students",
            null,
            values
        )

        db.close()

        return result != -1L
    }

    fun getAllStudents(): ArrayList<String> {

        val students = ArrayList<String>()

        val db = readableDatabase

        val cursor = db.rawQuery(
            "SELECT id, name, course FROM students ORDER BY id",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
                )

                val name = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
                )

                val course = cursor.getString(
                    cursor.getColumnIndexOrThrow("course")
                )

                val formattedName = if (name.length > 14) name.substring(0, 14) else name

                // Fixed spacing for proper table alignment
                val record = String.format(
                    "%-7s %-15s %-10s",
                    id,
                    formattedName,
                    course
                )

                students.add(record)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return students
    }

    fun deleteAllStudents(): Int {
        val db = writableDatabase
        val rowsDeleted = db.delete("students", null, null)
        db.close()
        return rowsDeleted
    }
}