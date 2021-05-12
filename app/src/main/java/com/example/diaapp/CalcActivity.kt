package com.example.diaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class CalcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        val homeBtn: ImageButton = findViewById(R.id.home_btn)
        homeBtn.setOnClickListener {
            val intent = Intent(this@CalcActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val addProductBtn: Button = findViewById(R.id.addProduct_btn)
        addProductBtn.setOnClickListener {
            val intent = Intent(this@CalcActivity, AddFoodActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}