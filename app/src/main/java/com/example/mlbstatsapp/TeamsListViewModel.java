package com.example.mlbstatsapp;

import android.os.Bundle;
import android.util.Log;

import androidx.room.Room;

import com.example.mlbstatsapp.Fragments.IndividualTeamFragment;
import com.example.mlbstatsapp.database.AppDatabase;
import com.example.mlbstatsapp.database.Team;
import com.example.mlbstatsapp.database.User;
import com.example.mlbstatsapp.database.UserWithTeams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TeamsListViewModel {

    //private AppDatabase appDB=  Room.databaseBuilder(this.getContext(), AppDatabase.class, "mlb-stats").allowMainThreadQueries().build();


    private User user;
    private static int userId;
   // private static int testUserId= 1;  //Hardcoded for testing purposes

    public void testTeamRetrieval(){
        Team team= LoadData.appDb.getTeamDao().selectTeamByName("Guardians");

        Log.i("Team Retrieved:  ", team.getName());

       this.user= LoadData.createTestUserAndInsertFavoriteTeams();
       Log.i("User Details: ", "ID: "+ user.getId());
       userId= user.getId();
       Log.i("User ID is:", "ID: " +userId);
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

    public  void deleteFavoriteRelationship(Team team){

            team.setTeamFavorite(false);
            UserWithTeams userWithTeams= LoadData.appDb.getUserWithTeamsDao().getUserTeam(userId, team.getId());
            Log.i("User Team Combo", userWithTeams.getUserId()+ "");
            LoadData.appDb.getUserWithTeamsDao().delete(userWithTeams);
    }

    public void addFavoriteRelationship(Team team){
        team.setTeamFavorite(true);
        LoadData.appDb.getUserWithTeamsDao().insert(new UserWithTeams(userId, team.getId()));

    }


    public ArrayList<Team> getTeamsInDB(){
            List<Team> teamList= LoadData.appDb.getTeamDao().getTeamList();
            ArrayList<Team> favoriteTeams = new ArrayList<>();

            List<UserWithTeams> userWithTeamsList= LoadData.appDb.getUserWithTeamsDao().getUsersAndTeams(userId);
            Log.i("List Size for user " +userId+": ", userWithTeamsList.size()+ " objects in list");
            for(UserWithTeams userTeam: userWithTeamsList){

                Team team= LoadData.appDb.getTeamDao().selectTeam(userTeam.getTeamId());
                Log.i("Is this executing? ",  "Yes");
                favoriteTeams.add(team);
            }



            for(Team team: teamList){
                if(favoriteTeams.contains(team)){
                    team.setTeamFavorite(true);
                }
                else{
                    Log.i("The team named ", team.getName()+" is not a favorite");
                }
            }

        Collections.sort(teamList, new Comparator<Team>() {
            @Override
            public int compare(Team t1, Team t2) {
                return t1.toString().compareTo(t2.toString());
            }
        });
            return (ArrayList<Team>) teamList;
    }

//    public ArrayList<Team> getFavoriteTeamsFromUser(int id){
//
//
//
//    }



}
