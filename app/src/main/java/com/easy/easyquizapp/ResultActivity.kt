package com.easy.easyquizapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.easy.easyquizapp.Activity.SplashActivity

class ResultActivity : AppCompatActivity() {
    lateinit var tvName: TextView
    lateinit var tvResult: TextView
    lateinit var btnStartAgain: Button
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        // Hiding Status Bar
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // Initialize Shared Prefernces
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)

        // HOOKS
        tvName = findViewById(R.id.name)
        tvResult = findViewById(R.id.score)
        btnStartAgain = findViewById(R.id.btn_start_again)

        // Getting Data To Show
        tvName.text = sharedPreferences.getString("name", "Unknown")
        tvResult.text = "${intent.getStringExtra("count")} Out Of 10"

        // Start Challenge Again
        btnStartAgain.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            startActivity(Intent(this, SplashActivity::class.java))
            finish()
        }
    }

    override fun onDestroy() {
        sharedPreferences.edit().clear().apply()
        super.onDestroy()
    }
}