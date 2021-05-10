package com.example.diaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class RecipesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        val home_btn: ImageButton = findViewById(R.id.home_btn)
        home_btn.setOnClickListener {
            val intent = Intent(this@RecipesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}