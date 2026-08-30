package com.example.nkrumahstudentattendanceapp

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private val TAG = "LOGIN"

    private lateinit var studentNumber: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var showPassword: TextView

    private var passwordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_login)

        studentNumber = findViewById(R.id.etStudentNumber)
        password = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.loginProgress)
        showPassword = findViewById(R.id.tvShowPassword)

        // Check existing login session
        val session = getSharedPreferences(
            "StudentSession",
            MODE_PRIVATE
        )

        if (session.getBoolean("loggedIn", false)) {
            openDashboard()
            return
        }

        // Show / hide password
        showPassword.setOnClickListener {

            passwordVisible = !passwordVisible

            if (passwordVisible) {

                password.inputType =
                    InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD

                showPassword.text = "HIDE"

            } else {

                password.inputType =
                    InputType.TYPE_CLASS_TEXT or
                            InputType.TYPE_TEXT_VARIATION_PASSWORD

                showPassword.text = "SHOW"
            }

            password.setSelection(password.text.length)
        }

        // Login button
        loginButton.setOnClickListener {
            loginStudent()
        }
    }

    private fun loginStudent() {

        val student = studentNumber.text.toString().trim()
        val pass = password.text.toString()

        // Validate student number
        if (student.isEmpty()) {

            studentNumber.error = "Enter your student number"
            studentNumber.requestFocus()

            return
        }

        // Validate password
        if (pass.isEmpty()) {

            password.error = "Enter your password"
            password.requestFocus()

            return
        }

        if (pass.length < 6) {

            password.error =
                "Password must contain at least 6 characters"

            password.requestFocus()

            return
        }

        // Start loading
        setLoading(true)

        /*
         * TEMPORARY LOGIN
         *
         * This is currently a local demonstration.
         * Later we can connect it to a database/API.
         */

        loginButton.postDelayed({

            setLoading(false)

            // Save student session
            getSharedPreferences(
                "StudentSession",
                MODE_PRIVATE
            )
                .edit()
                .putBoolean("loggedIn", true)
                .putString("studentNumber", student)
                .apply()

            Toast.makeText(
                this,
                "Welcome back!",
                Toast.LENGTH_SHORT
            ).show()

            openDashboard()

        }, 800)
    }

    private fun setLoading(loading: Boolean) {

        if (loading) {

            loginButton.isEnabled = false
            loginButton.text = "SIGNING IN..."
            progressBar.visibility = View.VISIBLE

        } else {

            loginButton.isEnabled = true
            loginButton.text = "SIGN IN"
            progressBar.visibility = View.GONE
        }
    }

    private fun openDashboard() {

        val intent = Intent(
            this,
            AttendanceDashboardActivity::class.java
        )

        startActivity(intent)
        finish()
    }

    // ==============================
    // LIFECYCLE LOGGING
    // ==============================

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }
}