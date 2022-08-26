package com.example.mlbstatsapp.database;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Team {

    @PrimaryKey
    @NonNull
    private String id;
    @ColumnInfo(name= "location")
    private String location;
    @ColumnInfo(name= "name")
    private String name;
    @ColumnInfo(name= "wins")
    private int wins;
    @ColumnInfo(name= "losses")
    private int losses;
    @ColumnInfo(name= "streak")
    private String streak;
    @ColumnInfo(name= "last-ten")
    private String lastTen;
    @ColumnInfo(name= "league")
    private String league;
    @ColumnInfo(name= "division")
    private String division;
    @ColumnInfo(name= "games-back")
    private float gamesBack;

    @Ignore
    private boolean isTeamFavorite;



    public Team(String id, String location, String name, String league, String division) {
        this.id= id;
        this.location = location;
        this.name = name;

        this.league = league;
        this.division = division;


    }

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id= id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getLosses() {
        return losses;
    }

    public void setLosses(int losses) {
        this.losses = losses;
    }

    public String getStreak() {
        return streak;
    }

    public void setStreak(String streak) {
        this.streak = streak;
    }

    public String getLastTen() {
        return lastTen;
    }

    public void setLastTen(String lastTen) {
        this.lastTen = lastTen;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public float getGamesBack() {
        return gamesBack;
    }

    public void setGamesBack(float gamesBack) {
        this.gamesBack = gamesBack;
    }

    public boolean isTeamFavorite() {
        return isTeamFavorite;
    }

    public void setTeamFavorite(boolean teamFavorite) {
        isTeamFavorite = teamFavorite;
    }

    @Override
    public String toString() {
        return location+" "+name+" "+gamesBack+ " "+ streak;
    }

    @Override
    public boolean equals(Object o){


       if (this.name.equals(((Team) o).getName())){
           return true;
       }
       return false;
    }
}
