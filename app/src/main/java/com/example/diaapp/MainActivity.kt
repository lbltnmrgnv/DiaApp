package com.example.diaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calc_btn : Button = findViewById(R.id.calc_btn)
        calc_btn.setOnClickListener {
            val intent = Intent(this@MainActivity, CalcActivity::class.java)
            startActivity(intent)
            finish()
        }
        val recipes_bnt : Button = findViewById(R.id.recipes_btn)
        recipes_bnt.setOnClickListener {
            val intent = Intent(this@MainActivity, RecipesActivity::class.java)
            startActivity(intent)
            finish()
        }
        val diary_bnt : Button = findViewById(R.id.diary_btn)
        diary_bnt.setOnClickListener {
            val intent = Intent(this@MainActivity, DiaryActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}