package com.example.mlbstatsapp.RecycleAdapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.R
import com.example.mlbstatsapp.database.Team

class FavoriteTeamsAdapter (private val teamList: List<Team>) : RecyclerView.Adapter<FavoriteTeamsAdapter.TeamsViewHolder>() {

    private lateinit var context: Context
    //var fragmentActivity = activity

    class TeamsViewHolder(itemView: View, context: Context): RecyclerView.ViewHolder(itemView){

        var favoriteTeamNameView: TextView
        var favoriteTeamRecordView: TextView
        var favoriteTeamStreakView: TextView

        init {
            favoriteTeamNameView= itemView.findViewById(R.id.textViewFavoriteTeamName)
            favoriteTeamRecordView= itemView.findViewById(R.id.textViewFavoriteTeamRecord)
            favoriteTeamStreakView= itemView.findViewById(R.id.textViewFavoriteTeamStreak)
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteTeamsAdapter.TeamsViewHolder {
        context= parent.context
        val inflater= LayoutInflater.from(context)
        val teamsView= inflater.inflate(R.layout.favorite_teams_row, parent, false)
        return TeamsViewHolder(teamsView, context)

    }



    override fun getItemCount(): Int {
        return teamList.size
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
       val team= teamList[position]
        val favoriteTeamNameTextView= holder.favoriteTeamNameView
        val favoriteTeamRecordTextvView= holder.favoriteTeamRecordView
        val favoriteTeamStreakTextView= holder.favoriteTeamStreakView



        favoriteTeamNameTextView.text= team.location+" "+team.name
        favoriteTeamRecordTextvView.text= ""+team.wins +"-"+team.losses
        favoriteTeamStreakTextView.text= team.streak

    }
}