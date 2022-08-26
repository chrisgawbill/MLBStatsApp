package com.example.mlbstatsapp.RecycleAdapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.mlbstatsapp.RecycleAdapters.TeamsListAdapter.TeamsViewHolder
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.example.mlbstatsapp.R
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.mlbstatsapp.Fragments.IndividualPlayer
import com.example.mlbstatsapp.Fragments.IndividualTeamFragment
import com.example.mlbstatsapp.Fragments.PlayerSearchResultsFragmentDirections
import com.example.mlbstatsapp.Fragments.TeamsListFragmentDirections
import com.example.mlbstatsapp.TeamsListViewModel
import com.example.mlbstatsapp.database.Team
import kotlinx.coroutines.NonDisposableHandle.parent

private lateinit var context: Context;

class TeamsListAdapter(private val teamList: List<Team>) :
    RecyclerView.Adapter<TeamsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
         context = parent.context
        val inflater = LayoutInflater.from(context)
        val teamsView =
            inflater.inflate(R.layout.teams_list_row, parent, false)
        return TeamsViewHolder(teamsView, context)
    }

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {

        val team= teamList[position]
        Log.i("Team: ", team.name+" "+team.isTeamFavorite)
       // val favoriteTeams= TeamsListViewModel.getUserFavorites();

        val teamNameTextView = holder.teamNameTextView
        val detailsImageView= holder.detailsImageView
        teamNameTextView.text = teamList[position].location+" "+teamList[position].name

        val drawable=  if (team.isTeamFavorite) (context.resources.getDrawable(android.R.drawable.ic_delete, null) ) else context.resources.getDrawable(android.R.drawable.ic_input_add, null)
        val plusImageView= holder.plusImageView

        plusImageView.setImageDrawable(drawable)


        plusImageView.setOnClickListener{

            val isFavorite= team.isTeamFavorite


            val teamsListViewModel= TeamsListViewModel()

            if(team.isTeamFavorite) {
                teamsListViewModel.deleteFavoriteRelationship(team)
            }
            else{
                teamsListViewModel.addFavoriteRelationship(team)
            }


            //team.isTeamFavorite= if (isFavorite) false else true
            notifyDataSetChanged()

        }








        detailsImageView.setOnClickListener {

            Log.i("Team: ", teamList[position].name)


            val team= teamList[position]

            //Following logs created due to problems with values being null
            Log.i("Team Details: ", teamList[position].toString())

//            Log.i("Team Streak: ", team.streak)
//            Log.i("Team Games Back: ", team.gamesBack.toString())
//            Log.i("Team Last Ten: ", team.lastTen)
            //---------------------------------------------------------------

            val action= TeamsListFragmentDirections.actionTeamsListFragmentToIndividualTeam()
            action.location= team.location
            action.name= team.name
            action.league= team.league
            action.division= team.division
            action.wins= team.wins.toString()
            action.losses= team.losses.toString()
            action.streak= team.streak
            action.gamesBack= team.gamesBack.toString()
            action.lastTen= team.lastTen
            Navigation.findNavController(holder.itemView).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return teamList.size
    }

    inner class TeamsViewHolder(itemView: View, context: Context) : RecyclerView.ViewHolder(itemView) {
        var teamNameTextView: TextView
        var plusImageView: ImageView
        var detailsImageView: ImageView
        var context= context


        init {
            teamNameTextView = itemView.findViewById(R.id.teamNameText)
            plusImageView = itemView.findViewById(R.id.addTeamImageView)
            detailsImageView= itemView.findViewById(R.id.detailsImageView)







//            plusImageView.setOnClickListener{
//
//
//                val drawable= context.resources.getDrawable(android.R.drawable.ic_delete, null)
//
//               plusImageView.setImageDrawable(drawable)
//
//               // plusImageView.setImageDrawable()
//            }

        }



    }
}