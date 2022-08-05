package com.example.mlbstatsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView

class PlayerSearchResultsAdapter(playerArrayParam:ArrayList<PlayerApiModel>): RecyclerView.Adapter<PlayerSearchResultsAdapter.PlayerSearchViewHolder>() {
    var playerSearchArray:ArrayList<PlayerApiModel> = playerArrayParam
    class PlayerSearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var playerNameText:TextView = itemView.findViewById(R.id.playerSearchResultsNameText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerSearchViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.player_search_results_row, parent, false)
        return PlayerSearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayerSearchViewHolder, position: Int) {
        var playerName = playerSearchArray.get(position).name_display_first_last
        holder.playerNameText.text = playerName
        holder.itemView.setOnClickListener(View.OnClickListener {
            rowClick(position, holder.itemView.context)
        })
    }

    override fun getItemCount(): Int {
        return playerSearchArray.size
    }
    fun rowClick(position:Int, context: Context){
        var playerName = playerSearchArray.get(position).name_display_first_last
        Toast.makeText(context, playerName, Toast.LENGTH_SHORT).show()
    }
}