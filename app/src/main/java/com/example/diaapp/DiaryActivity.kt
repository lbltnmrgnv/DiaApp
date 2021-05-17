package com.example.diaapp
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.*
import kotlin.collections.HashMap


class DiaryActivity : AppCompatActivity(), UpdDelDiary {
    private lateinit var database: DatabaseReference
    var diaryList: MutableList<DiaryModel>? = null
    private lateinit var adapter: DiaryAdapter
    private var listVeiwItem :ListView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diary)

        val homeBtn: ImageButton = findViewById(R.id.home_btn)
        homeBtn.setOnClickListener {
            val intent = Intent(this@DiaryActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
        val fab : FloatingActionButton = findViewById(R.id.fab_btn)
        listVeiwItem= findViewById(R.id.item_listView)
        database = FirebaseDatabase.getInstance().reference


        fab.setOnClickListener {
            val alertEditText = LayoutInflater.from(this).inflate(R.layout.alert_edit_texts,null)
            val alertDialog = AlertDialog.Builder(this)

            val dishEdit = alertEditText.findViewById<EditText>(R.id.dish_edit)
            val dishEditXe = alertEditText.findViewById<EditText>(R.id.dish_xe_edit)

            alertDialog.setMessage("Введите Блюдо и количество ХЕ")
            .setTitle("Добавление блюда в дневник")
            .setView(alertEditText)



            alertDialog.setPositiveButton("Добавить"){ dialog, _ ->
                val diaryItemData = DiaryModel.createList()
                diaryItemData.itemDishDiary = dishEdit.text.toString()
                diaryItemData.itemXeDiary = dishEditXe.text.toString()
                val newItemData = database.child("Diary").push()
                diaryItemData.uid = newItemData.key

                newItemData.setValue(diaryItemData)
                dialog.dismiss()
                Toast.makeText(this,"Добавлено", Toast.LENGTH_LONG).show()
            }
            alertDialog.show()
        }

        diaryList = mutableListOf()
        adapter = DiaryAdapter(this, diaryList!!)
        listVeiwItem!!.adapter = adapter
        database.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(applicationContext,"Не было добавлено", Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(snapshot: DataSnapshot) {
            diaryList!!.clear()
                addItemToList(snapshot)
            }

        })
    }

    private fun addItemToList(snapshot: DataSnapshot) {
        val items = snapshot.children.iterator()
        //val diaryTime = SimpleDateFormat("yyyy/MM/dd HH:mm")
        //val cal = Calendar.getInstance()
        if (items.hasNext()){
            val toDoIndexedValue = items.next()
            val itemsIterator = toDoIndexedValue.children.iterator()

            while (itemsIterator.hasNext()){
                //time.text = diaryTime.format(cal.time).toString()
                val currentItem = itemsIterator.next()
                val diaryItemData = DiaryModel.createList()
                val map = currentItem.value as HashMap<*, *>
                diaryItemData.uid = currentItem.key
                diaryItemData.itemDishDiary = map["itemDishDiary"] as String?
                diaryItemData.itemXeDiary = map["itemXeDiary"] as String?
                diaryList!!.add(diaryItemData)

            }
        }
        adapter.notifyDataSetChanged()

    }


    override fun modifyItem(itemUID: String) {
        database.child("Diary").child(itemUID)

    }

    override fun onItemDelete(itemUID: String) {
        val itemReference = database.child("Diary").child(itemUID)
        itemReference.removeValue()
        adapter.notifyDataSetChanged()
    }


}