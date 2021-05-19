package com.example.diaapp

class RecipeModel {
    var recipeName: String? = null
    var recipeInfo: String? = null
    var recipeImg: String? = null
    constructor(){}
    constructor(
        recipeName: String?,
        recipeInfo: String?,
        recipeImg: String?
    ){
        this.recipeName = recipeName
        this.recipeInfo = recipeInfo
        this.recipeImg = recipeImg
    }
}