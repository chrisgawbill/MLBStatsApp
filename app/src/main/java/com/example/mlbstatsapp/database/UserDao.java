package com.example.mlbstatsapp.database;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user where id= :id")
    public User selectUser(int id);

    @Query("SELECT * FROM user WHERE username= :username  ")
    public User getUserByUsername(String username);

    @Insert
    public void insertUser(User user);
}