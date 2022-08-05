package com.example.mlbstatsapp;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.Room;

import com.example.mlbstatsapp.database.AppDatabase;
import com.example.mlbstatsapp.database.Team;
import com.example.mlbstatsapp.database.TeamDao;
import com.example.mlbstatsapp.di.AppComponent;
import com.example.mlbstatsapp.di.AppModule;

import java.time.LocalDateTime;

import javax.inject.Inject;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LoadData extends AppCompatActivity {

    private static LoadData single_instance= null;

    private LocalDateTime ldt= LocalDateTime.now();
    private int year;
    private String teamsListApi;
    private String teamStatsApi;

    private AppDatabase appDb= Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mlb-stats").allowMainThreadQueries().build();

    private LoadData(){




        year= ldt.getYear();
        teamsListApi= "https://lookup-service-prod.mlb.com/json/named.team_all_season.bam?sport_code=%27mlb%27&all_star_sw=%27N%27&sort_order=sort_by=%27name_asc%27%7D&season=$"+year;
        teamStatsApi= "https://erikberg.com/mlb/standings.json";

    }

    public static LoadData getInstance(){
        if(single_instance==null)
            single_instance= new LoadData();

        return single_instance;
    }

    public String getTeamsListApi(){
        return teamsListApi;
    }

    public String getTeamStatsApi(){
        return teamStatsApi;
    }

    public void updateTeamStats(Team team, String name){

        if(appDb.getTeamDao().hasTeam(name))
            appDb.getTeamDao().updateTeam(team);


    }

    public void insertTeam(Team team){

        appDb.getTeamDao().insertTeam(team);

    }

}
