package com.example.mlbstatsapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface PitcherDao {
    @Query("SELECT * FROM pitcher WHERE `player-id`= :id")
    Pitcher findPitcher(String id);

    @Query("SELECT * FROM pitcher")
    List<Pitcher> getAllPitchers();

    @Insert
    void insertPitcher(Pitcher pitcher);

    @Query("DELETE FROM Pitcher WHERE `player-id`= :id")
    void deletePitcher(String id);


    @Query("UPDATE Pitcher SET  'era'= :era, 'wins'= :wins, 'losses'= :losses WHERE 'player-id' = :id")
    void updatePitchingStats(String id, String era, String wins, String losses);
}
