package com.example.mlbstatsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.databinding.PlayerSearchResultsRowBinding

class PlayerSearchResultsAdapter: RecyclerView.Adapter<PlayerSearchResultsAdapter.PlayerSearchViewHolder>() {
    var playerSearchArray:ArrayList<PlayerApiModel> = ArrayList()
    lateinit var sharedViewModel:PlayerSearchIndividualPlayerSharedViewModel
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
        var playerId = playerSearchArray.get(position).player_id
        Toast.makeText(context, playerId, Toast.LENGTH_SHORT).show()
    }
}