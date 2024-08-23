package com.example.broadcasttest.ui

import android.Manifest
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.broadcasttest.R
import com.example.broadcasttest.broadcast.MyBroadCast

class MainActivity : AppCompatActivity() {
    val intentFilter = IntentFilter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        checkAirPlaneMode(intentFilter)
        requestPermission()

     findViewById<Button>(R.id.btn_notification).setOnClickListener {
         startActivity(Intent(this, NotificationActivity::class.java))
     }
    }

    //broadcast test
    private fun checkAirPlaneMode(intentFilter: IntentFilter) {
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        registerReceiver(MyBroadCast(), intentFilter)
    }

    //permission test
    private fun requestPermission() {
        if (ContextCompat
                .checkSelfPermission(
                    this, Manifest.permission.CAMERA
                ) == PackageManager.PERMISSION_GRANTED
        ) {
            anyCodePermission()
        } else {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1)
        }
    }

    //permission test result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                anyCodePermission()
            } else {
                finish()
            }
        }
    }

    //permission code
    private fun anyCodePermission() {
        Toast.makeText(this, "hello world", Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(MyBroadCast())
    }
}
