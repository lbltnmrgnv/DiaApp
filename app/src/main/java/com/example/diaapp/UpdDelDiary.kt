package com.example.diaapp

interface UpdDelDiary {
    fun modifyItem(itemUID: String)
    fun onItemDelete(itemUID: String)
}