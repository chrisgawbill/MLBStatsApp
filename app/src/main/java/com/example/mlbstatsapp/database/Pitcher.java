package com.example.mlbstatsapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pitcher implements Player{

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "player-id")
    private String playerId;
    @ColumnInfo(name= "first-name")
    private String firstName;
    @ColumnInfo(name= "last-name")
    private String lastName;
    @ColumnInfo(name= "hometown")
    private String hometown;
    @ColumnInfo(name="height")
    private String height;
    @ColumnInfo(name="weight")
    private String weight;
    @ColumnInfo(name= "team")
    private String team;
    @ColumnInfo(name= "bats")
    private String bats;
    @ColumnInfo(name= "throws")
    private String handThrows;
    @ColumnInfo(name= "era")
    private float era;
    @ColumnInfo(name= "wins")
    private int wins;
    @ColumnInfo(name= "losses")
    private int losses;

    public Pitcher(String playerId, String firstName, String lastName, String hometown,String height, String weight, String team, String bats, String handThrows, float era, int wins, int losses) {
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.hometown = hometown;
        this.height = height;
        this.weight = weight;
        this.team = team;
        this.bats = bats;
        this.handThrows = handThrows;
        this.era = era;
        this.wins = wins;
        this.losses = losses;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id= id;
    }

    public String getPlayerId(){return playerId;}

    public void setPlayerId(String playerId){this.playerId = playerId;}

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    public String getHeight(){return height;}

    public void setHeight(String height){this.height = height;}

    public String getWeight(){return weight;}

    public void setWeight(String weight){this.weight = weight;}

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getBats() {
        return bats;
    }

    public void setBats(String bats) {
        this.bats = bats;
    }

    public String getHandThrows() {
        return handThrows;
    }

    public void setHandThrows(String handThrows) {
        this.handThrows = handThrows;
    }

    public float getEra() {
        return era;
    }

    public void setEra(float era) {
        this.era = era;
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
}
