package ru.kpfu.itis.android.models;

import java.io.Serializable;

/**
 * Created by hlopu on 24.04.2018.
 */

public class Player implements Serializable{
    private String name;
    private String team;
    private String number;
    private String countMatches;
    private String countGoals;
    private String photo;

    public Player(String name, String team, String number, String countMatches, String countGoals, String photo) {
        this.name = name;
        this.team = team;
        this.number = number;
        this.countMatches = countMatches;
        this.countGoals = countGoals;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCountMatches() {
        return countMatches;
    }

    public void setCountMatches(String countMatches) {
        this.countMatches = countMatches;
    }

    public String getCountGoals() {
        return countGoals;
    }

    public void setCountGoals(String countGoals) {
        this.countGoals = countGoals;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
