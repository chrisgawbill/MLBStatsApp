package com.example.mlbstatsapp

import android.util.Log
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.mlbstatsapp.database.Team
import com.example.mlbstatsapp.database.User
import com.example.mlbstatsapp.database.UserWithTeams
import java.util.*


class TeamsListViewModel(activity: FragmentActivity?): ViewModel() {
    val db = LoadData.getInstance(activity?.applicationContext ?: activity?.applicationContext)
    private var user:User= db.createTestUserAndInsertFavoriteTeams()
    private var userId= user?.id



    fun testTeamRetrieval() {


        Log.i("User Details: ", "ID: " + user!!.getId())

        Log.i("User ID is:", "ID: " + userId)
    }

    //    public static void getUserFavorites(){
    //       List<UserWithTeams> userWithTeams= LoadData.appDb.getUserWithTeamsDao().getUsersAndTeams(userId);
    //
    //
    //
    //        for(UserWithTeams userTeamFavs: userWithTeams){
    //
    //            String teamId= userTeamFavs.getTeamId();
    //
    //            Log.i("Team ID is:  ", teamId);
    //
    //            Team team= LoadData.appDb.getTeamDao().selectTeam(teamId);
    //
    //            //team.setTeamFavorite(true);
    //
    //        }
    //
    //    }



    fun deleteFavoriteRelationship(team: Team) {
        team.setTeamFavorite(false)
        val userWithTeams: UserWithTeams =
            db.getUserWithTeams(userId, team.id)
        Log.i("User Team Combo", userWithTeams.getUserId().toString() + "")
        db.deleteUserWithTeams(userWithTeams)
    }

    fun addFavoriteRelationship(team: Team) {
        team.setTeamFavorite(true)
        db.insertUsersAndTeams(UserWithTeams(userId!!, team.id))
    }

    //    public ArrayList<Team> getFavoriteTeamsFromUser(int id){
    val teamsInDB: ArrayList<Team>
        get() {
            val teamList: List<Team> = db.getTeamList()
            val favoriteTeams = ArrayList<Team>()
            val userWithTeamsList: List<UserWithTeams> =
                db.getUsersandTeams(
                    userId
                )
            Log.i(
                "List Size for user " + userId + ": ",
                userWithTeamsList.size.toString() + " objects in list"
            )
            for (userTeam in userWithTeamsList) {
                val team: Team = db.selectTeam(userTeam.getTeamId())
                Log.i("Is this executing? ", "Yes")
                favoriteTeams.add(team)
            }
            for (team in teamList) {
                if (favoriteTeams.contains(team)) {
                    team.setTeamFavorite(true)
                } else {
                    Log.i("The team named ", team.name + " is not a favorite")
                }
            }
            /*
            Collections.sort(teamList, object : Comparator<Team?> {
                override fun compare(t1: Team, t2: Team): Int {
                    return t1.toString().compareTo(t2.toString())
                }
            })
            */

            teamList.sortedWith(compareBy<Team> { it.location }.thenBy { it.name })

            return teamList as ArrayList<Team>
        }

    //
    //
    //
    //    }

}