package com.example.diaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class AddFoodActivity : AppCompatActivity() {
    private lateinit var editProduct: EditText
    private lateinit var editWeight: EditText
    private lateinit var btnSave: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        editProduct = findViewById(R.id.editProduct)
        editWeight = findViewById(R.id.editWeight)
        btnSave = findViewById(R.id.btn_save)
        btnSave.setOnClickListener {
            saveProduct()
        }
        val homeBtn: ImageButton = findViewById(R.id.home_btn)
        homeBtn.setOnClickListener {
            val intent = Intent(this@AddFoodActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

     private fun saveProduct() {
        val productName = editProduct.text.toString().trim()
        val productWeight = editWeight.text.toString().toInt()
        if(productName.isEmpty()){
            editProduct.error = "Введите название продукта"
            return
        }

        val ref = FirebaseDatabase.getInstance().getReference("Products")
        val productId = ref.push().key
        val product = Product(productId,productName,productWeight)
        if (productId != null) {
            ref.child(productId).setValue(product).addOnCompleteListener {
                Toast.makeText(applicationContext, "Продукт был сохранен", Toast.LENGTH_SHORT).show()
            }
        }

    }

}