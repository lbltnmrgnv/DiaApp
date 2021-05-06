package com.example.diaapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.FirebaseDatabase

class AddFoodActivity : AppCompatActivity() {
    lateinit var editProduct: EditText
    lateinit var editWeight: EditText
    lateinit var btn_save: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        editProduct = findViewById(R.id.editProduct)
        editWeight = findViewById(R.id.editWeight)
        btn_save = findViewById(R.id.btn_save)
        btn_save.setOnClickListener {
            saveProduct()
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
        val productId = ref.push() .key
        val product = Product(productId,productName,productWeight)
        if (productId != null) {
            ref.child(productId).setValue(product).addOnCompleteListener {
                Toast.makeText(applicationContext, "Продукт был сохранен", Toast.LENGTH_SHORT).show()
            }
        }
    }
}