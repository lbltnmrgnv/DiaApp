package com.example.diaapp

class DiaryModel {
    companion object Factory {
        fun createList(): DiaryModel= DiaryModel()
    }
    var uid: String? = null
    var itemDishDiary: String? = null
    var itemXeDiary: String? = null
}