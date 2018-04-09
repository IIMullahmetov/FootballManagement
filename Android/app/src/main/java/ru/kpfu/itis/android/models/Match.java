package ru.kpfu.itis.android.models;

import java.io.Serializable;

/**
 * Created by hlopu on 08.04.2018.
 */

public class Match implements Serializable {
    private String team1;
    private String team2;
    private String score1;
    private String score2;
    private String nameChampionships;
    private String date;

    public Match(String team1, String team2, String score1, String score2, String nameChampionships, String date) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.nameChampionships = nameChampionships;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTeam1() {
        return team1;
    }

    public void setTeam1(String team1) {
        this.team1 = team1;
    }

    public String getTeam2() {
        return team2;
    }

    public void setTeam2(String team2) {
        this.team2 = team2;
    }

    public String getScore1() {
        return score1;
    }

    public void setScore1(String score1) {
        this.score1 = score1;
    }

    public String getScore2() {
        return score2;
    }

    public void setScore2(String score2) {
        this.score2 = score2;
    }

    public String getNameChampionships() {
        return nameChampionships;
    }

    public void setNameChampionships(String nameChampionships) {
        this.nameChampionships = nameChampionships;
    }
}
