package com.example.mlbstatsapp;

public class Team {

    private int id;
    private String location;
    private String name;
    private int wins;
    private int losses;
    private String streak;
    private String lastTen;
    private String league;
    private String division;
    private int gamesBack;


    public Team(String location, String name, int wins, int losses, String streak, String lastTen, String league, String division, int gamesBack) {
        this.location = location;
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.streak = streak;
        this.lastTen = lastTen;
        this.league = league;
        this.division = division;
        this.gamesBack = gamesBack;
    }

    public int getId() {
        return id;
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

    public int getGamesBack() {
        return gamesBack;
    }

    public void setGamesBack(int gamesBack) {
        this.gamesBack = gamesBack;
    }
}
