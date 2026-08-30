package com.example.nkrumahstudentattendanceapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AttendanceHistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_attendance_history)

        val backButton = findViewById<TextView>(R.id.btnBack)

        backButton.setOnClickListener {
            finish()
        }
    }
}