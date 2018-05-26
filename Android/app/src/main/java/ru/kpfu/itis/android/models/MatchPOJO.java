package ru.kpfu.itis.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MatchPOJO implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("tourneyId")
    @Expose
    private Integer tourneyId;
    @SerializedName("startDt")
    @Expose
    private String startDt;
    @SerializedName("home")
    @Expose
    private TeamMatch home;
    @SerializedName("guest")
    @Expose
    private TeamMatch guest;
    @SerializedName("message")
    @Expose
    private String message;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTourneyId() {
        return tourneyId;
    }

    public void setTourneyId(Integer tourneyId) {
        this.tourneyId = tourneyId;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public TeamMatch getHome() {
        return home;
    }

    public void setHome(TeamMatch home) {
        this.home = home;
    }

    public TeamMatch getGuest() {
        return guest;
    }

    public void setGuest(TeamMatch guest) {
        this.guest = guest;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
