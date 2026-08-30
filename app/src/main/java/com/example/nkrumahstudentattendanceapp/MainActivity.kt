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

class MainActivity : AppCompatActivity() {

    private val TAG = "LOGIN_LIFECYCLE"

    private lateinit var studentNumber: EditText
    private lateinit var password: EditText
    private lateinit var loginButton: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var showPassword: TextView

    private var passwordVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "MainActivity - onCreate")

        setContentView(R.layout.activity_main)

        studentNumber = findViewById(R.id.etStudentNumber)
        password = findViewById(R.id.etPassword)
        loginButton = findViewById(R.id.btnLogin)
        progressBar = findViewById(R.id.loginProgress)
        showPassword = findViewById(R.id.tvShowPassword)

        // ==========================================
        // CHECK EXISTING LOGIN
        // ==========================================

        val preferences =
            getSharedPreferences("StudentSession", MODE_PRIVATE)

        val loggedIn =
            preferences.getBoolean("loggedIn", false)

        if (loggedIn) {

            openDashboard()

            return
        }

        // ==========================================
        // SHOW / HIDE PASSWORD
        // ==========================================

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

        // ==========================================
        // LOGIN
        // ==========================================

        loginButton.setOnClickListener {

            performLogin()
        }
    }

    private fun performLogin() {

        val student =
            studentNumber.text.toString().trim()

        val pass =
            password.text.toString()

        // ==========================================
        // STUDENT NUMBER VALIDATION
        // ==========================================

        if (student.isEmpty()) {

            studentNumber.error =
                "Student number is required"

            studentNumber.requestFocus()

            return
        }

        if (student.length < 6) {

            studentNumber.error =
                "Enter a valid student number"

            studentNumber.requestFocus()

            return
        }

        // ==========================================
        // PASSWORD VALIDATION
        // ==========================================

        if (pass.isEmpty()) {

            password.error =
                "Password is required"

            password.requestFocus()

            return
        }

        if (pass.length < 6) {

            password.error =
                "Password must contain at least 6 characters"

            password.requestFocus()

            return
        }

        // ==========================================
        // START LOGIN
        // ==========================================

        setLoginLoading(true)

        /*
         * DEMO AUTHENTICATION
         *
         * In a real application this information
         * would be checked by a secure university
         * server.
         */

        loginButton.postDelayed({

            setLoginLoading(false)

            // Store session
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

    private fun setLoginLoading(loading: Boolean) {

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

        val intent =
            Intent(
                this,
                AttendanceDashboardActivity::class.java
            )

        startActivity(intent)

        finish()
    }

    // ==========================================
    // LIFECYCLE
    // ==========================================

    override fun onStart() {
        super.onStart()

        Log.d(TAG, "MainActivity - onStart")
    }

    override fun onResume() {
        super.onResume()

        Log.d(TAG, "MainActivity - onResume")
    }

    override fun onPause() {
        super.onPause()

        Log.d(TAG, "MainActivity - onPause")
    }

    override fun onStop() {
        super.onStop()

        Log.d(TAG, "MainActivity - onStop")
    }

    override fun onDestroy() {
        super.onDestroy()

        Log.d(TAG, "MainActivity - onDestroy")
    }
}