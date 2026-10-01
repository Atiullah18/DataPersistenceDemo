package com.example.datapersistencedemo

import android.graphics.Typeface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment

class DatabaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_database,
            container,
            false
        )

        val tvRecords = view.findViewById<TextView>(
            R.id.tvRecords
        )

        val btnClearAll = view.findViewById<Button>(
            R.id.btnClearAll
        )

        val btnLogout = view.findViewById<Button>(
            R.id.btnLogout
        )

        tvRecords.typeface = Typeface.MONOSPACE

        val databaseHelper = DatabaseHelper(
            requireContext()
        )

        fun loadRecords() {
            val students = databaseHelper.getAllStudents()

            if (students.isNotEmpty()) {
                val records = StringBuilder()

                records.append(
                    String.format(
                        "%-7s %-15s %-10s",
                        "ID",
                        "Name",
                        "Course"
                    )
                )

                records.append("\n")
                records.append("----------------------------------")
                records.append("\n")

                for (student in students) {
                    records.append(student)
                    records.append("\n")
                }

                tvRecords.text = records.toString()

            } else {
                tvRecords.text = "No records found"
            }
        }

        loadRecords()

        // CLEAR ALL STUDENTS
        btnClearAll.setOnClickListener {
            databaseHelper.deleteAllStudents()
            loadRecords()
        }

        // LOGOUT
        // Do NOT clear SharedPreferences
        btnLogout.setOnClickListener {

            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    LoginFragment()
                )
                .commit()
        }

        return view
    }
}