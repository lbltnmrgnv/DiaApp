package com.example.diaapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageButton
import android.widget.TextView

class DiaryAdapter(context: Context, diaryList:MutableList<DiaryModel>) :BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val itemList = diaryList
    private var updDelDiary:UpdDelDiary = context as UpdDelDiary

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val uid: String = itemList[position].uid as String
        val itemDishDiary: String? = itemList[position].itemDishDiary
        val itemXeDiary: String? = itemList[position].itemXeDiary

        val view: View
        val viewHolder: ListViewHolder
        if (convertView == null){
            view = inflater.inflate(R.layout.row_itemslayout, parent, false)
            viewHolder = ListViewHolder(view)
            view.tag = viewHolder
        }   else {
            view = convertView
            viewHolder= view.tag as ListViewHolder
        }
        viewHolder.textDish.text = itemDishDiary.toString()
        viewHolder.textXe.text = itemXeDiary.toString()

        viewHolder.isDeleted.setOnClickListener {
            updDelDiary.onItemDelete(uid)
        }

        return view
    }

    private class ListViewHolder(row: View?) {
        val textDish: TextView = row!!.findViewById(R.id.dish_textView) as TextView
        val textXe: TextView = row!!.findViewById(R.id.xe_textView) as TextView
        val isDeleted: ImageButton = row!!.findViewById(R.id.diary_delete) as ImageButton
    }

    override fun getItem(position: Int): Any {
        return itemList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return  itemList.size
    }

}