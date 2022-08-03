package com.example.mlbstatsapp;

public class Pitcher {

    private int id;
    private String firstName;
    private String lastName;
    private String hometown;
    private String team;
    private String bats;
    private String handThrows;
    private float era;
    private int wins;
    private int losses;

    public Pitcher(String firstName, String lastName, String hometown, String team, String bats, String handThrows, float era, int wins, int losses) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hometown = hometown;
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
