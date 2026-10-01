package com.example.datapersistencedemo

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val view = inflater.inflate(
            R.layout.fragment_login,
            container,
            false
        )

        val username = view.findViewById<EditText>(R.id.etUsername)
        val password = view.findViewById<EditText>(R.id.etPassword)
        val loginButton = view.findViewById<Button>(R.id.btnLogin)

        val preferences = requireActivity().getSharedPreferences(
            "LoginData",
            Context.MODE_PRIVATE
        )

        val databaseHelper = DatabaseHelper(requireContext())

        // Load saved username
        val savedUsername = preferences.getString("username", "")

        // Load saved password
        val savedPassword = preferences.getString("password", "")

        if (!savedUsername.isNullOrEmpty()) {
            username.setText(savedUsername)
        }

        if (!savedPassword.isNullOrEmpty()) {
            password.setText(savedPassword)
        }

        fun autoSaveCredentials() {
            val u = username.text.toString().trim()
            val p = password.text.toString().trim()
            if (u.isNotEmpty() && p.isNotEmpty()) {
                preferences.edit()
                    .putString("username", u)
                    .putString("password", p)
                    .apply()
                databaseHelper.saveLoginDetails(u, p)
            }
        }

        // Save username automatically as user types
        username.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                autoSaveCredentials()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Save password automatically as user types
        password.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                autoSaveCredentials()
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        loginButton.setOnClickListener {

            val enteredUsername = username.text.toString().trim()
            val enteredPassword = password.text.toString().trim()

            if (enteredUsername.isEmpty() || enteredPassword.isEmpty()) {

                Toast.makeText(
                    requireContext(),
                    "Please enter username and password",
                    Toast.LENGTH_SHORT
                ).show()

            } else {

                // SAVE USERNAME AND PASSWORD TO PREFS & SQLITE
                preferences.edit()
                    .putString("username", enteredUsername)
                    .putString("password", enteredPassword)
                    .commit()

                databaseHelper.saveLoginDetails(enteredUsername, enteredPassword)

                Toast.makeText(
                    requireContext(),
                    "Login Successful",
                    Toast.LENGTH_SHORT
                ).show()

                parentFragmentManager.beginTransaction()
                    .replace(
                        R.id.fragment_container,
                        AddDataFragment()
                    )
                    .addToBackStack(null)
                    .commit()
            }
        }

        return view
    }
}