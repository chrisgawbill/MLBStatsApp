package com.example.mlbstatsapp.database;


import androidx.constraintlayout.helper.widget.Flow;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TeamDao {

    @Query("SELECT * FROM team")
    List<Team> getTeamList();

    @Query("SELECT * FROM team WHERE id= :id")
    Team selectTeam(String id);

    //Team can be selected by entering the team name
    @Query("SELECT * FROM team WHERE name= :name")
    Team selectTeamByName(String name);

    //This method will be used to regularly update the team stats in the database.
    //Every so often, the api will be loaded and each element will be checked if the element
    //exists in teh database.   If it does, it will be updated in the updateTeam method
    @Query("SELECT EXISTS (SELECT * FROM team WHERE id = :id)")
    boolean hasTeam(String id);


    @Insert
    void insertTeam(Team team);

    //Update team will be called on a time interval as games end several times daily in the MLB

    @Update
    void updateTeam(Team team);
}