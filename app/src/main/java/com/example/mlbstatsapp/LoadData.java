package com.example.mlbstatsapp;

import android.content.Context;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.mlbstatsapp.database.AppDatabase;
import com.example.mlbstatsapp.database.Batter;
import com.example.mlbstatsapp.database.Pitcher;
import com.example.mlbstatsapp.database.Player;
import com.example.mlbstatsapp.database.Team;
import com.example.mlbstatsapp.database.User;
import com.example.mlbstatsapp.database.UserWithTeams;
import com.example.mlbstatsapp.database.UserWithTeamsDao;
//import com.example.mlbstatsapp.di.AppComponent;
//import com.example.mlbstatsapp.di.AppModule;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


//@RequiresApi(api = Build.VERSION_CODES.O)
public class LoadData extends AppCompatActivity {

    private static LoadData single_instance= null;

    private final LocalDateTime ldt= LocalDateTime.now();
    private int year;
    //private String teamsListApi;
    private final String teamStatsApi;



    private  Context context;

    private AppDatabase appDb;// appDb= Room.databaseBuilder(context, AppDatabase.class, "mlb-stats").allowMainThreadQueries().build();

    private LoadData(Context context){

       this.context= context;

        appDb= Room.databaseBuilder(this.getContext(), AppDatabase.class, "mlb-stats").allowMainThreadQueries().build();

        year= ldt.getYear();
        //teamsListApi=
        teamStatsApi= "https://erikberg.com/mlb/standings.json";

    }

    public static LoadData getInstance(Context context){
       //LoadData single_instance= null;
        if(single_instance==null)
            single_instance= new LoadData(context);


        return single_instance;
    }

    public Context getContext() {
        return context;
    }


    //public String getTeamsListApi(){
     //   return teamsListApi;
  //  }

    public String getTeamStatsApi(){
        return teamStatsApi;
    }

    public void updateTeamStats(Team team, String id){

        if(appDb.getTeamDao().hasTeam(id))
            appDb.getTeamDao().updateTeam(team);


    }

    public void insertTeams(){
        AndroidNetworking.get(teamStatsApi).build()
                .getAsJSONObject(new JSONObjectRequestListener() {


                    @Override
                    public void onResponse(JSONObject response) {
                        String jsonString= response.toString();
                        Log.i("This is the full string: \n\n", jsonString);

                        try {
                            JSONObject obj= new JSONObject(jsonString);
                            JSONArray arr= obj.getJSONArray("standing");



                            for(int i=0; i<arr.length(); i++){

                                String id= arr.getJSONObject(i).getString("team_id");
                              //  int id= Integer.parseInt(stringId);
                                String location = arr.getJSONObject(i).getString("first_name");
                                //String fullName= arr.getJSONObject(i).getString("name_display_full");
                                String teamName= arr.getJSONObject(i).getString("last_name");
                                String leagueAbbrev= arr.getJSONObject(i).getString("conference");
                                String league;

                                switch(leagueAbbrev){
                                    case "AL":
                                        league= "American League";
                                        break;
                                    case "NL":
                                        league= "National League";
                                        break;
                                    default:
                                        //This should never happen unless there is an error reading the JSON string.  Inserted only for non-null purposes
                                        league= "Not Retrieved Properly";
                                }
                                String divisionAbbrev= arr.getJSONObject(i).getString("division");
                                String division;

                                switch(divisionAbbrev){
                                    case "E":
                                        division= "East";
                                        break;
                                    case "W":
                                        division= "West";
                                        break;
                                    case "C":
                                        division= "Central";
                                        break;
                                    default:
                                        //This should never happen unless there is an error reading the JSON string. Inserted only for non-null purposes

                                        division= "Not retreived properly";
                                }




                                if(!appDb.getTeamDao().hasTeam(id)){

                                    Team team= new Team(id, location, teamName, league, division);

                                    insertTeam(team);

                                    Log.i("Team Inserted", team.getLocation()+" "+ team.getName());

                                }


                            }



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.i("AN ERROR OCCURRED!!!!: ","\nThe Error Was:\n"+ anError+"\n");
                        anError.printStackTrace();
                    }
                });
    }

   public void updateAllTeams(){

       AndroidNetworking.get(teamStatsApi).build()
               .getAsJSONObject(new JSONObjectRequestListener(){



                   @Override
                   public void onResponse(JSONObject response) {


                       try {

                           String jsonString= response.toString();
                           JSONObject obj= new JSONObject(jsonString);
                           JSONArray arr= obj.getJSONArray("standing");

                           for(int i= 0; i<arr.length(); i++){
                               String teamId= arr.getJSONObject(i).getString("team_id");
                               if(appDb.getTeamDao().hasTeam(teamId)){

                                  Team team= appDb.getTeamDao().selectTeam(teamId);

                                  float gamesBack= (float) arr.getJSONObject(i).getDouble("games_back");

                                  team.setGamesBack(gamesBack);

                                  int wins= arr.getJSONObject(i).getInt("won");

                                  team.setWins(wins);

                                  int losses= arr.getJSONObject(i).getInt("lost");

                                  team.setLosses(losses);

                                  String streak= arr.getJSONObject(i).getString("streak");

                                  team.setStreak(streak);

                                  String lastTen= arr.getJSONObject(i).getString("last_ten");

                                  team.setLastTen(lastTen);

                                  updateTeam(team);

                                  




                               }
                           }





                       } catch (JSONException e) {
                           e.printStackTrace();
                       }




                   }

                   @Override
                   public void onError(ANError anError) {

                   }
               });

   }



    private void insertTeam(Team team){

        appDb.getTeamDao().insertTeam(team);

    }

    private void updateTeam(Team team){

        appDb.getTeamDao().updateTeam(team);

    }

    /**
     *
     * @param player
     */
    public void insertPlayer(Player player){


    }
    public Batter getBatter(String id){
        return appDb.getBatterDao().findBatter(id);
    }
    public List<Batter> getAllBatters(){
        return appDb.getBatterDao().getAllBatters();
    }
    public void insertBatter(Batter batter){appDb.getBatterDao().insertBatter(batter);}
    public void deleteBatter(String id){appDb.getBatterDao().deleteBatter(id);}
    public void updateBatterStats(String id, String hr, String rbi, String ba){appDb.getBatterDao().updateHittingStats(id,hr,rbi,ba);}
    public Pitcher getPitcher(String id){
        return appDb.getPitcherDao().findPitcher(id);
    }
    public List<Pitcher> getAllPitchers(){
        return appDb.getPitcherDao().getAllPitchers();
    }
    public void insertPitcher(Pitcher pitcher){appDb.getPitcherDao().insertPitcher(pitcher);}
    public void deletePitcher(String id){appDb.getPitcherDao().deletePitcher(id);}
    public void updatePitchingStats(String id, String era, String wins, String losses){appDb.getPitcherDao().updatePitchingStats(id,era,wins,losses);}

    public Team selectTeam(String teamName){return appDb.getTeamDao().selectTeam(teamName);}
    public List<Team> getTeamList(){

        List<Team> teamList= appDb.getTeamDao().getTeamList();

        Collections.sort(teamList, new Comparator<Team>() {
            @Override
            public int compare(Team team, Team t1) {
               int locationCompare= team.getLocation().compareTo(t1.getLocation());

               if(locationCompare!=0){
                   return locationCompare;
               }

               return team.getName().compareTo(t1.getName());

            }
        });


        return teamList;
    }
    public List<UserWithTeams> getUsersandTeams(Integer userID){return appDb.getUserWithTeamsDao().getUsersAndTeams(userID);}
    public void insertUsersAndTeams(UserWithTeams userWithTeams){appDb.getUserWithTeamsDao().insert(userWithTeams);}
    public void deleteUserWithTeams(UserWithTeams userWithTeams){appDb.getUserWithTeamsDao().delete(userWithTeams);}
    public UserWithTeams getUserWithTeams(Integer userID, String teamID){return appDb.getUserWithTeamsDao().getUserTeam(userID, teamID);}

    //Method only being used for development purposes.  Upon deployment, a real user will be included from logging into the application.
    public User createTestUserAndInsertFavoriteTeams(){

        User user= appDb.getUserDao().getUserByUsername("KenM66");



        if(user==null) {
            user = new User("KenM66", "Kenneth", "Milota", "kenm661@gmail.com");

            appDb.getUserDao().insertUser(user);

            user= appDb.getUserDao().getUserByUsername("KenM66");

            Team team1= appDb.getTeamDao().selectTeamByName("Guardians");
            Team team2= appDb.getTeamDao().selectTeamByName("Rangers");

            User retrievedUser= appDb.getUserDao().getUserByUsername(user.getUsername());

            UserWithTeams userWithTeams= new UserWithTeams(retrievedUser.getId(), team1.getId());
            UserWithTeams userWithTeams2= new UserWithTeams(retrievedUser.getId(), team2.getId());
            appDb.getUserWithTeamsDao().insert(userWithTeams);
            appDb.getUserWithTeamsDao().insert(userWithTeams2);



            Log.i("Team ID: ", team1.getId()+ " "+ team1.getName());
        }

        return user;

    }
}
