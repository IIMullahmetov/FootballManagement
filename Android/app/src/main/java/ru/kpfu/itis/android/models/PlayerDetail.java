package ru.kpfu.itis.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class PlayerDetail implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("firstName")
    @Expose
    private String firstName;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("teamId")
    @Expose
    private Integer teamId;
    @SerializedName("team")
    @Expose
    private String team;
    @SerializedName("matchesCount")
    @Expose
    private Integer matchesCount;
    @SerializedName("winsCount")
    @Expose
    private Integer winsCount;
    @SerializedName("goalsCount")
    @Expose
    private Integer goalsCount;
    @SerializedName("assistsCount")
    @Expose
    private Integer assistsCount;
    @SerializedName("birthDt")
    @Expose
    private String birthDt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public Integer getMatchesCount() {
        return matchesCount;
    }

    public void setMatchesCount(Integer matchesCount) {
        this.matchesCount = matchesCount;
    }

    public Integer getWinsCount() {
        return winsCount;
    }

    public void setWinsCount(Integer winsCount) {
        this.winsCount = winsCount;
    }

    public Integer getGoalsCount() {
        return goalsCount;
    }

    public void setGoalsCount(Integer goalsCount) {
        this.goalsCount = goalsCount;
    }

    public Integer getAssistsCount() {
        return assistsCount;
    }

    public void setAssistsCount(Integer assistsCount) {
        this.assistsCount = assistsCount;
    }

    public String getBirthDt() {
        return birthDt;
    }

    public void setBirthDt(String birthDt) {
        this.birthDt = birthDt;
    }
}
