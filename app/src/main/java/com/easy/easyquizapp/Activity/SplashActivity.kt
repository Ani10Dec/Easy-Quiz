package com.easy.easyquizapp.Activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.easy.easyquizapp.R
import com.google.android.material.textfield.TextInputEditText

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getSharedPreferences("UserInfo", MODE_PRIVATE)
        setContentView(R.layout.activity_splash)

        // Hiding Splash Screen
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        // HOOKS
        val btnStart = findViewById<Button>(R.id.btnStart)
        val etName = findViewById<TextInputEditText>(R.id.etName)

        btnStart.setOnClickListener {
            // Getting String from TextEditText
            val stName = etName.text.toString()

            // Adding Name in SharedPreferences
            sharedPreferences.edit().putString("name", stName).apply()

            // Checking for Name Empty
            if (stName.isNotEmpty()) {
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("name", stName)
                startActivity(intent)
                finish()
            } else
                Toast.makeText(this, "Name Required", Toast.LENGTH_SHORT).show()
        }
    }
}