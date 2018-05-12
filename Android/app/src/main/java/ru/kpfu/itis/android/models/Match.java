package ru.kpfu.itis.android.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hlopu on 08.04.2018.
 */

public class Match implements Parcelable {
    private String team1;
    private String team2;
    private String score1;
    private String score2;
    private String nameChampionships;
    private String date;
    private String referee;
    private List<Goal> goalsTeam1;
    private List<Goal> goalsTeam2;

    public Match(String team1, String team2, String score1, String score2, String nameChampionships, String date, String referee, List<Goal> goalsTeam1, List<Goal> goalsTeam2) {
        this.team1 = team1;
        this.team2 = team2;
        this.score1 = score1;
        this.score2 = score2;
        this.nameChampionships = nameChampionships;
        this.date = date;
        this.referee = referee;
        this.goalsTeam1 = goalsTeam1;
        this.goalsTeam2 = goalsTeam2;
    }

    protected Match(Parcel in) {
        team1 = in.readString();
        team2 = in.readString();
        score1 = in.readString();
        score2 = in.readString();
        nameChampionships = in.readString();
        date = in.readString();
        referee = in.readString();
    }

    public static final Creator<Match> CREATOR = new Creator<Match>() {
        @Override
        public Match createFromParcel(Parcel in) {
            return new Match(in);
        }

        @Override
        public Match[] newArray(int size) {
            return new Match[size];
        }
    };

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

    public String getReferee() {
        return referee;
    }

    public void setReferee(String referee) {
        this.referee = referee;
    }

    public List<Goal> getGoalsTeam1() {
        return goalsTeam1;
    }

    public void setGoalsTeam1(List<Goal> goalsTeam1) {
        this.goalsTeam1 = goalsTeam1;
    }

    public List<Goal> getGoalsTeam2() {
        return goalsTeam2;
    }

    public void setGoalsTeam2(List<Goal> goalsTeam2) {
        this.goalsTeam2 = goalsTeam2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(team1);
        dest.writeString(team2);
        dest.writeString(score1);
        dest.writeString(score2);
        dest.writeString(nameChampionships);
        dest.writeString(date);
        dest.writeString(referee);
    }
}
