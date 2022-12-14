package com.example.mlbstatsapp.RecycleAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.Fragments.IndividualPlayerArgs
import com.example.mlbstatsapp.Fragments.PlayerSearchResultsFragmentDirections
import com.example.mlbstatsapp.HomeFragmentDirections
import com.example.mlbstatsapp.PlayerApiModel
import com.example.mlbstatsapp.R
import com.example.mlbstatsapp.database.Batter

class FavoriteBattersAdapter(battersListParam: List<Batter>):RecyclerView.Adapter<FavoriteBattersAdapter.FavoriteBatterViewHolder>() {
    val batterList:List<Batter> = battersListParam

    /**
     * We find the batterNameTextView in the batter_recycler_row
     */
    class FavoriteBatterViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val favoriteBatterName:TextView = itemView.findViewById(R.id.favorite_batter_name)
    }

    /**
     * We inflate the batter_recycler_row
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteBatterViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.batter_recycler_row, parent, false)
        return FavoriteBatterViewHolder(layout)
    }

    /**
     * We bind the appropriate favorite batter to a batterNameTextView in a batter_recycler_row
     * We also set an on click listener
     */
    override fun onBindViewHolder(holder: FavoriteBatterViewHolder, position: Int) {
        var batter:Batter = batterList.get(position)
        holder.favoriteBatterName.text = batter.firstName + " " + batter.lastName
        holder.itemView.setOnClickListener(View.OnClickListener {
            favoriteBatterOnClick(position, holder.itemView.context, holder.itemView)
        })
    }

    /**
     * Get the size of batterList and return it
     */
    override fun getItemCount(): Int {
        return batterList.size
    }

    /**
     * On the on click we set the safeArgs and then traverse to individualPlayerPage
     */
    fun favoriteBatterOnClick(position: Int, context: Context, view: View){
        val safeArgs: IndividualPlayerArgs
        var player = batterList.get(position)as Batter
        val action =
            HomeFragmentDirections.actionHomeFragmentToIndividualPlayer()
        action.playerId = player.playerId.toInt()
        action.playerFirstName = player.firstName
        action.playerLastName = player.lastName
        action.playerPosition = player.position
        action.playerHeight = player.height
        action.playerWeight = player.weight
        action.playerBats = player.bats
        action.playerThrows = player.handThrows
        action.playerHometown = player.hometown
        action.playerCurrentTeam = player.team
        Navigation.findNavController(view).navigate(action)
    }
}