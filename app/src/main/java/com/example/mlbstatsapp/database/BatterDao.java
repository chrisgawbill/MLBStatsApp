package com.example.mlbstatsapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface BatterDao {
    @Query("SELECT * FROM batter WHERE `player-id`= :id")
    Batter findBatter(String id);

    @Query("SELECT * FROM batter")
    List<Batter> getAllBatters();

    @Insert
    void insertBatter(Batter batter);

    @Query("DELETE FROM Batter WHERE `player-id`= :id")
    void deleteBatter(String id);
}
