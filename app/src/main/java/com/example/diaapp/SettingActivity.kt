package com.example.diaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        val homeBtn: ImageButton = findViewById(R.id.home_btn)
        homeBtn.setOnClickListener {
            val intent = Intent(this@SettingActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}