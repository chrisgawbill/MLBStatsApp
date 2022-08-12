package com.example.mlbstatsapp.RecycleAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.R
import com.example.mlbstatsapp.database.Batter

class FavoriteBattersAdapter(battersListParam: List<Batter>):RecyclerView.Adapter<FavoriteBattersAdapter.FavoriteBatterViewHolder>() {
    val batterList:List<Batter> = battersListParam
    class FavoriteBatterViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val favoriteBatterName:TextView = itemView.findViewById(R.id.favorite_batter_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBatterViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.batter_recycler_row, parent, false)
        return FavoriteBatterViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FavoriteBatterViewHolder, position: Int) {
        var batter:Batter = batterList.get(position)
        holder.favoriteBatterName.text = batter.firstName + " " + batter.lastName
    }

    override fun getItemCount(): Int {
        return batterList.size
    }
}