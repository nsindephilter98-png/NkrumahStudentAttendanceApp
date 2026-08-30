package com.example.nkrumahstudentattendanceapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MarkAttendanceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mark_attendance)

        val backButton = findViewById<TextView>(R.id.btnBack)
        val markButton = findViewById<Button>(R.id.btnMarkAttendance)

        backButton.setOnClickListener {
            finish()
        }

        markButton.setOnClickListener {

            markButton.isEnabled = false
            markButton.text = "ATTENDANCE RECORDED"

            Toast.makeText(
                this,
                "Attendance marked successfully",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}