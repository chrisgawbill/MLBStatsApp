package com.example.mlbstatsapp.RecycleAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.R
import com.example.mlbstatsapp.database.Pitcher

class FavoritePitcherAdapter(pitcherListParam:List<Pitcher>):RecyclerView.Adapter<FavoritePitcherAdapter.FavoritePitcherViewHolder>() {
    val pitcherList:List<Pitcher> = pitcherListParam
    class FavoritePitcherViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val favoritePitcherName: TextView = itemView.findViewById(R.id.favoritePitcherName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePitcherViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.pitcher_recycler_row, parent, false)
        return FavoritePitcherViewHolder(layout)
    }

    override fun onBindViewHolder(holder: FavoritePitcherViewHolder, position: Int) {
        var pitcher:Pitcher = pitcherList.get(position)
        holder.favoritePitcherName.text = pitcher.firstName + " " + pitcher.lastName
    }

    override fun getItemCount(): Int {
        return pitcherList.size
    }
}