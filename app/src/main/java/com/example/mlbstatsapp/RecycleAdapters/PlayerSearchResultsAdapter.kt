package com.example.mlbstatsapp.RecycleAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.Fragments.IndividualPlayerArgs
import com.example.mlbstatsapp.Fragments.PlayerSearchResultsFragmentDirections
import com.example.mlbstatsapp.PlayerApiModel
import com.example.mlbstatsapp.databinding.PlayerSearchResultsRowBinding

class PlayerSearchResultsAdapter: RecyclerView.Adapter<PlayerSearchResultsAdapter.PlayerSearchViewHolder>() {
    var playerSearchArray:ArrayList<PlayerApiModel> = ArrayList()

    /**
     * We get rowBinding for this player_search_results_row and then create a method to bind data passed in
     */
    class PlayerSearchViewHolder(val binding: PlayerSearchResultsRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PlayerApiModel){
            binding.playerApiModel = data
            binding.executePendingBindings()
        }
    }

    /**
     * We set passed in data to the playerSearchArray
     */
    fun setDataList(data:ArrayList<PlayerApiModel>){
        this.playerSearchArray = data
    }

    /**
     * We inflate the player_search_results_row in such a way to allow dataBinding
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerSearchViewHolder {
        var layoutInflater = LayoutInflater.from(parent.context)
        var rowBinding = com.example.mlbstatsapp.databinding.PlayerSearchResultsRowBinding.inflate(layoutInflater,parent, false)
        return PlayerSearchViewHolder(rowBinding)
    }

    /**
     * We call the bind() method in the ViewHolder class and pass in data to be binded
     * We also set an onClickListener for this player_search_results_row
     */
    override fun onBindViewHolder(holder: PlayerSearchViewHolder, position: Int) {
        holder.bind(playerSearchArray.get(position))
        holder.itemView.setOnClickListener(View.OnClickListener {
            rowClick(position, holder.itemView.context, holder.itemView)
        })
    }

    /**
     * We get the size of plauerSearchArray and return it
     */
    override fun getItemCount(): Int {
        return playerSearchArray.size
    }

    /**
     * We set the safeArgs and then navigate to individualPlayerPage
     */
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