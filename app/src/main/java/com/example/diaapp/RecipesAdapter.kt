package com.example.diaapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diaapp.databinding.ItemListBinding

class RecipesAdapter(var c: Context, var recipeList: ArrayList<RecipeModel>): RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>()
{
    inner class RecipesViewHolder(var v: ItemListBinding): RecyclerView.ViewHolder(v.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemListBinding>(inflater,R.layout.item_list,parent,false)
        return RecipesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val viewList = recipeList[position]
        holder.v.isRecipes = recipeList[position]
        holder.v.root.setOnClickListener {
            val img = viewList.recipeImg
            val name = viewList.recipeName
            val info = viewList.recipeInfo

            val intent = Intent(c, RecipesViewActivity::class.java)
            intent.putExtra("img", img)
            intent.putExtra("name", name)
            intent.putExtra("info", info)
            c.startActivity(intent)
        }

    }
}