package com.example.mlbstatsapp.RecycleAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.IndividualPlayerArgs
import com.example.mlbstatsapp.PlayerApiModel
import com.example.mlbstatsapp.PlayerSearchResultsFragmentDirections
import com.example.mlbstatsapp.databinding.PlayerSearchResultsRowBinding

class PlayerSearchResultsAdapter: RecyclerView.Adapter<PlayerSearchResultsAdapter.PlayerSearchViewHolder>() {
    var playerSearchArray:ArrayList<PlayerApiModel> = ArrayList()
    class PlayerSearchViewHolder(val binding:PlayerSearchResultsRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: PlayerApiModel){
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
            rowClick(position, holder.itemView.context, holder.itemView)
        })
    }

    override fun getItemCount(): Int {
        return playerSearchArray.size
    }
    fun rowClick(position:Int, context: Context, view:View){
        var playerId = playerSearchArray.get(position).player_id
        val safeArgs: IndividualPlayerArgs
        val action =
            PlayerSearchResultsFragmentDirections.actionPlayerSearchResultsFragmentToIndividualPlayer()
        action.playerId = playerId.toInt()
        Navigation.findNavController(view).navigate(action)
    }
}