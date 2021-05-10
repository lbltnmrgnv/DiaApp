package com.example.diaapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val calcBtn : ImageButton = findViewById(R.id.calc_btn)
        calcBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, CalcActivity::class.java)
            startActivity(intent)
            finish()
        }
        val recipesBnt : ImageButton = findViewById(R.id.recipes_btn)
        recipesBnt.setOnClickListener {
            val intent = Intent(this@MainActivity, RecipesActivity::class.java)
            startActivity(intent)
            finish()
        }
        val diaryBnt : ImageButton = findViewById(R.id.diary_btn)
        diaryBnt.setOnClickListener {
            val intent = Intent(this@MainActivity, DiaryActivity::class.java)
            startActivity(intent)
            finish()
        }
        val settingsBnt : ImageButton = findViewById(R.id.settings_btn)
        settingsBnt.setOnClickListener {
            val intent = Intent(this@MainActivity, SettingActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}