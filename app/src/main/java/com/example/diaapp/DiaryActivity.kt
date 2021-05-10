package com.example.diaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton

class DiaryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)
        val home_btn: ImageButton = findViewById(R.id.home_btn)
        home_btn.setOnClickListener {
            val intent = Intent(this@DiaryActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val fab : FloatingActionButton = findViewById(R.id.fab_btn)
        fab.setOnClickListener { view ->
            val alertEditText = layoutInflater.inflate(R.layout.alert_edit_texts,null)
            val alertDialog = AlertDialog.Builder(this)
            .setMessage("Введите Блюдо и количество ХЕ")
            .setTitle("Нажмите чтобы добавить")
            .setView(alertEditText)
            .setPositiveButton("Add"){dialog, i ->

            }
            alertDialog.show()
        }
    }
}