package com.example.mlbstatsapp.RecycleAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.Fragments.IndividualPlayerArgs
import com.example.mlbstatsapp.Fragments.PlayerSearchResultsFragmentDirections
import com.example.mlbstatsapp.PlayerApiModel
import com.example.mlbstatsapp.databinding.PlayerSearchResultsRowBinding

class PlayerSearchResultsAdapter: RecyclerView.Adapter<PlayerSearchResultsAdapter.PlayerSearchViewHolder>() {
    var playerSearchArray:ArrayList<PlayerApiModel> = ArrayList()
    class PlayerSearchViewHolder(val binding: com.example.mlbstatsapp.databinding.PlayerSearchResultsRowBinding) : RecyclerView.ViewHolder(binding.root) {
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
        val safeArgs: IndividualPlayerArgs
        var player = playerSearchArray.get(position)as PlayerApiModel
        val action =
            PlayerSearchResultsFragmentDirections.actionPlayerSearchResultsFragmentToIndividualPlayer()
        action.playerId = player.player_id.toInt()
        action.playerFirstName = player.name_first
        action.playerLastName = player.name_last
        action.playerPosition = player.position
        action.playerHeight = player.height_feet
        action.playerWeight = player.weight
        action.playerBats = player.bats
        action.playerThrows = player.throws
        action.playerHometown = player.birth_city + ", " +  player.birth_country
        action.playerCurrentTeam = player.team_full
        Navigation.findNavController(view).navigate(action)
    }
}