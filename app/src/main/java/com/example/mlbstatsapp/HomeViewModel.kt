package com.example.mlbstatsapp

import android.app.Activity
import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.mlbstatsapp.database.Batter
import com.example.mlbstatsapp.database.Pitcher
import com.example.mlbstatsapp.database.Team
import com.example.mlbstatsapp.database.UserWithTeams

class HomeViewModel(activity: FragmentActivity?):ViewModel() {
    val db = LoadData.getInstance(activity?.applicationContext ?: activity?.applicationContext)

    /**
     * Returns all the batters in db
     */
    fun getAllBatters():List<Batter>{
        return db.getAllBatters()
    }

    /**
     * Returns all the pitchers in db
     */
    fun getAllPitchers():List<Pitcher>{
        return db.getAllPitchers()
    }

    fun getFavoriteTeams():List<Team>{
        val user= db.createTestUserAndInsertFavoriteTeams()
        val userWithTeamsList= db.getUsersandTeams(user.id)
        //val teamsList= db.teamList
        val favoriteTeamsList= ArrayList<Team>()

        for(userTeam in userWithTeamsList){
            val team= db.selectTeam(userTeam.teamId)

            favoriteTeamsList.add(team)
        }


        return favoriteTeamsList

    }
}