package com.example.mlbstatsapp;

public class Batter {

    private int id;
    private String firstName;
    private String lastName;
    private String hometown;
    private String team;
    private String bats;
    private String handThrows;
    private String position;
    private int hr;
    private int rbi;
    private float ba;

    public Batter(String firstName, String lastName, String hometown, String team, String bats, String handThrows, String position, int hr, int rbi, float ba) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.hometown = hometown;
        this.team = team;
        this.bats = bats;
        this.handThrows = handThrows;
        this.position = position;
        this.hr = hr;
        this.rbi = rbi;
        this.ba = ba;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public int getRbi() {
        return rbi;
    }

    public void setRbi(int rbi) {
        this.rbi = rbi;
    }

    public float getBa() {
        return ba;
    }

    public void setBa(float ba) {
        this.ba = ba;
    }
}
