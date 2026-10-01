package com.example.datapersistencedemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class AddDataFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_add_data,
            container,
            false
        )

        val etId = view.findViewById<EditText>(R.id.etId)
        val etName = view.findViewById<EditText>(R.id.etName)
        val etCourse = view.findViewById<EditText>(R.id.etCourse)
        val btnAddData = view.findViewById<Button>(R.id.btnAddData)

        btnAddData.setOnClickListener {

            val id = etId.text.toString().trim()
            val name = etName.text.toString().trim()
            val course = etCourse.text.toString().trim()

            if (id.isEmpty() || name.isEmpty() || course.isEmpty()) {

                Toast.makeText(
                    requireContext(),
                    "Please enter all details",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                val databaseHelper = DatabaseHelper(requireContext())

                val db = databaseHelper.writableDatabase

                val values = android.content.ContentValues()

                values.put("id", id.toInt())
                values.put("name", name)
                values.put("course", course)

                val result = db.insert(
                    "students",
                    null,
                    values
                )

                db.close()

                if (result != -1L) {

                    Toast.makeText(
                        requireContext(),
                        "Student data inserted successfully!",
                        Toast.LENGTH_SHORT
                    ).show()

                    etId.text.clear()
                    etName.text.clear()
                    etCourse.text.clear()

                    // Open Fragment 3
                    parentFragmentManager.beginTransaction()
                        .replace(
                            R.id.fragment_container,
                            DatabaseFragment()
                        )
                        .addToBackStack(null)
                        .commit()

                } else {

                    Toast.makeText(
                        requireContext(),
                        "Failed to insert data",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        return view
    }
}