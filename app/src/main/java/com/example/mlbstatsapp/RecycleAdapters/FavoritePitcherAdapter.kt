package com.example.mlbstatsapp.RecycleAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.Fragments.IndividualPlayerArgs
import com.example.mlbstatsapp.HomeFragmentDirections
import com.example.mlbstatsapp.R
import com.example.mlbstatsapp.database.Batter
import com.example.mlbstatsapp.database.Pitcher

class FavoritePitcherAdapter(pitcherListParam:List<Pitcher>):RecyclerView.Adapter<FavoritePitcherAdapter.FavoritePitcherViewHolder>() {
    val pitcherList:List<Pitcher> = pitcherListParam

    /**
     * We get the pitcherNameTextView from pitcher_recycler_row
     */
    class FavoritePitcherViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val favoritePitcherName: TextView = itemView.findViewById(R.id.favoritePitcherName)
    }

    /**
     * We inflate pitcher_recycler_row
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritePitcherViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.pitcher_recycler_row, parent, false)
        return FavoritePitcherViewHolder(layout)
    }

    /**
     * We set the appropriate favorite pitcher name to pitcherNameTextView in a pitcher_recycler_row
     * We also set on onClickListener to this pitcher_recycler_row
     */
    override fun onBindViewHolder(holder: FavoritePitcherViewHolder, position: Int) {
        var pitcher:Pitcher = pitcherList.get(position)
        holder.favoritePitcherName.text = pitcher.firstName + " " + pitcher.lastName
        holder.itemView.setOnClickListener(View.OnClickListener {
            favoritePitcherOnClick(position, holder.itemView.context, holder.itemView)
        })
    }

    /**
     * We return the size of pitcherList
     */
    override fun getItemCount(): Int {
        return pitcherList.size
    }

    /**
     * We set the safeArgs and then navigate to individualPlayerPage
     */
    fun favoritePitcherOnClick(position: Int, context: Context, view: View){
        val safeArgs: IndividualPlayerArgs
        var player = pitcherList.get(position)as Pitcher
        val action =
            HomeFragmentDirections.actionHomeFragmentToIndividualPlayer()
        action.playerId = player.playerId.toInt()
        action.playerFirstName = player.firstName
        action.playerLastName = player.lastName
        action.playerPosition = "P"
        action.playerHeight = player.height
        action.playerWeight = player.weight
        action.playerBats = player.bats
        action.playerThrows = player.handThrows
        action.playerHometown = player.hometown
        action.playerCurrentTeam = player.team
        Navigation.findNavController(view).navigate(action)
    }
}