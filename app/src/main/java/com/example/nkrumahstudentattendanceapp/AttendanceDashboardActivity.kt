package com.example.nkrumahstudentattendanceapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AttendanceDashboardActivity : AppCompatActivity() {

    private val TAG = "ATTENDANCE_DASHBOARD"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate")

        setContentView(R.layout.activity_attendance_dashboard)

        // Attendance History
        val historyButton =
            findViewById<TextView>(R.id.btnAttendanceHistory)

        historyButton.setOnClickListener {

            Log.d(TAG, "Opening Attendance History")

            val intent =
                Intent(this, AttendanceHistoryActivity::class.java)

            startActivity(intent)
        }

        // Profile
        val profileButton =
            findViewById<TextView>(R.id.btnProfile)

        profileButton.setOnClickListener {

            Log.d(TAG, "Opening Profile")

            val intent =
                Intent(this, StudentProfileActivity::class.java)

            startActivity(intent)
        }
    }

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