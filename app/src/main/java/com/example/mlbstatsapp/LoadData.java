package com.example.mlbstatsapp;

import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.room.Room;

import com.example.mlbstatsapp.database.AppDatabase;
import com.example.mlbstatsapp.database.Team;
import com.example.mlbstatsapp.database.TeamDao;

import java.time.LocalDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LoadData {

    private static LoadData single_instance= null;

    private LocalDateTime ldt= LocalDateTime.now();
    private int year;
    private String teamsListApi;
    private String teamStatsApi;

    private TeamDao teamDao;


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

    public void updateTeamStats(Team team, String name){

        if(teamDao.hasTeam(name)){

            teamDao.updateTeam(team);

        }
    }

    public void insertTeam(Team team){

        teamDao.insertTeam(team);


    }

}
