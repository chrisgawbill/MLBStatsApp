package com.example.mlbstatsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.databinding.PlayerSearchResultsRowBinding


//Class has errors
class PlayerSearchResultsAdapter: RecyclerView.Adapter<PlayerSearchResultsAdapter.PlayerSearchViewHolder>() {
    var playerSearchArray:ArrayList<PlayerApiModel> = ArrayList()
    class PlayerSearchViewHolder(val binding:PlayerSearchResultsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data:PlayerApiModel){
            binding.playerApiModel = data
            binding.executePendingBindings()
        }
    }
    fun setDataList(data:ArrayList<PlayerApiModel>){
        this.playerSearchArray = data
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerSearchViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var rowBinding = PlayerSearchResultsRowBinding.inflate(layoutInflater)
        return PlayerSearchViewHolder(rowBinding)
    }

    override fun onBindViewHolder(holder: PlayerSearchViewHolder, position: Int) {
        holder.bind(playerSearchArray.get(position))
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