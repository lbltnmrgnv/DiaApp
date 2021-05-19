package com.example.diaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_recipes_view.*
import org.w3c.dom.Text

class RecipesViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes_view)

        val recipesIntent = intent
        val recName = recipesIntent.getStringExtra("name")
        val recInfo = recipesIntent.getStringExtra("info")
        val recImg = recipesIntent.getStringExtra("img")

        recipeViewName.text = recName
        recipeViewInfo.text = recInfo
        recipeViewImg.loadImage(recImg, getDrawble(c = this))

    }
}