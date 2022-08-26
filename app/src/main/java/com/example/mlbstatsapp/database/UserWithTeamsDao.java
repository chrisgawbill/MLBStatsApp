package com.example.mlbstatsapp.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Transaction;

import java.util.List;

@Dao
public interface UserWithTeamsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(UserWithTeams userWithTeams);


   @Query("SELECT * FROM UserWithTeams WHERE userId = :id")
    List<UserWithTeams> getUsersAndTeams(int id);

   @Query("SELECT * FROM UserWithTeams WHERE (userId= :idUser AND teamId= :idTeam) ")
   UserWithTeams getUserTeam(int idUser, String idTeam);

   @Delete
    void delete(UserWithTeams userWithTeams);



}
