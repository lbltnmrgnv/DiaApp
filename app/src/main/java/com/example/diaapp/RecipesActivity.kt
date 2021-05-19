package com.example.diaapp

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_recipes.*


class RecipesActivity : AppCompatActivity() {
    lateinit var mDatabase: DatabaseReference
    private lateinit var recipesList: ArrayList<RecipeModel>
    private lateinit var mAdapter: RecipesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipes)
        val homeBtn: ImageButton = findViewById(R.id.home_btn)
        homeBtn.setOnClickListener {
            val intent = Intent(this@RecipesActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        recipesList = ArrayList()
        mAdapter = RecipesAdapter(this, recipesList)
        recyclerRecipes.layoutManager = LinearLayoutManager(this)
        recyclerRecipes.setHasFixedSize(true)
        recyclerRecipes.adapter = mAdapter
        getRecipesData()

    }

    private fun getRecipesData() {
        mDatabase = FirebaseDatabase.getInstance().getReference("Recipes")
        mDatabase.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (recipesSnapshot in snapshot.children){
                        val recipe = recipesSnapshot.getValue(RecipeModel::class.java)
                        recipesList.add(recipe!!)
                    }
                    recyclerRecipes.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@RecipesActivity, error.message, Toast.LENGTH_SHORT ).show()
            }

        })
    }


}