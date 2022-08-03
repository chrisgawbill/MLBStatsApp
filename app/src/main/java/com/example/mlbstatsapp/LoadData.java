package com.example.mlbstatsapp;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.LocalDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class LoadData {

    private static LoadData single_instance= null;

    private LocalDateTime ldt= LocalDateTime.now();
    private int year;
    private String teamsListApi;
    private String teamStatsApi;

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

    public void updateStats(){

    }

}
