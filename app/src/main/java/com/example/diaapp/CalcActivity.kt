package com.example.diaapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.math.RoundingMode
import java.text.DecimalFormat


class CalcActivity : AppCompatActivity() {
    private lateinit var decisionBtn: Button
    private lateinit var productSearch: EditText
    private lateinit var gramSearch: EditText
    private lateinit var gramResult:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)
        val homeBtn: ImageButton = findViewById(R.id.home_btn)
        homeBtn.setOnClickListener {
            val intent = Intent(this@CalcActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val addProductBtn: ImageButton = findViewById(R.id.addProduct_btn)
        addProductBtn.setOnClickListener {
            val intent = Intent(this@CalcActivity, AddFoodActivity::class.java)
            startActivity(intent)
            finish()
        }
      productSearch = findViewById(R.id.product_search)
      gramSearch = findViewById(R.id.gram_search)
      decisionBtn = findViewById(R.id.decision_btn)
      gramResult = findViewById(R.id.gram_result)
      decisionBtn.setOnClickListener {
          retrieveWeightFromDB()
          productSearch.text.clear()
          gramSearch.text.clear()
      }

    }


    @SuppressLint("DefaultLocale")
    private fun retrieveWeightFromDB() {
        val prodSch = productSearch.text.toString().toLowerCase().trim()
        val gramSch = gramSearch.text.toString().toInt()
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING

        val ref = FirebaseDatabase.getInstance().getReference("Products")
        val query = ref.orderByChild("productName").equalTo(prodSch)
        query.addValueEventListener(object : ValueEventListener {
            @SuppressLint("SetTextI18n")
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (ds in snapshot.children) {
                        val weight = ds.child("weight").getValue(Int::class.java)!!
                        val xe = df.format(gramSch.toFloat() / weight.toFloat())
                        val xeResult = xe.toString()
                        gramResult.text = "Хлебных едениц в продукте: $xeResult"
                    }
                } else {
                    Toast.makeText(applicationContext, "Продукт не был найден. Можете добавить его в базу, нажав на +", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }




}