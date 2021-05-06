package com.example.diaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class CalcActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        val home_btn: Button = findViewById(R.id.home_btn)
        home_btn.setOnClickListener {
            val intent = Intent(this@CalcActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val addProduct_btn: Button = findViewById(R.id.addProduct_btn)
        addProduct_btn.setOnClickListener {
            val intent = Intent(this@CalcActivity, AddFoodActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}