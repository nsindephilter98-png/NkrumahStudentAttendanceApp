package com.example.nkrumahstudentattendanceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class StudentProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_student_profile)

        val backButton = findViewById<TextView>(R.id.btnBack)
        val logoutButton = findViewById<Button>(R.id.btnLogout)

        backButton.setOnClickListener {
            finish()
        }

        logoutButton.setOnClickListener {

            getSharedPreferences(
                "StudentSession",
                MODE_PRIVATE
            )
                .edit()
                .clear()
                .apply()

            val intent = Intent(
                this,
                LoginActivity::class.java
            )

            intent.flags =
                Intent.FLAG_ACTIVITY_NEW_TASK or
                        Intent.FLAG_ACTIVITY_CLEAR_TASK

            startActivity(intent)
            finish()
        }
    }
}