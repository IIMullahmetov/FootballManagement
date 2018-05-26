package ru.kpfu.itis.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class TeamMatch implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("logotype")
    @Expose
    private String logotype;
    @SerializedName("redCards")
    @Expose
    private Integer redCards;
    @SerializedName("yellowCards")
    @Expose
    private Integer yellowCards;
    @SerializedName("fauls")
    @Expose
    private Integer fauls;
    @SerializedName("possession")
    @Expose
    private Integer possession;
    @SerializedName("goals")
    @Expose
    private List<Goal> goals = null;
    @SerializedName("players")
    @Expose
    private List<PlayerPOJO> players = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLogotype() {
        return logotype;
    }

    public void setLogotype(String logotype) {
        this.logotype = logotype;
    }

    public Integer getRedCards() {
        return redCards;
    }

    public void setRedCards(Integer redCards) {
        this.redCards = redCards;
    }

    public Integer getYellowCards() {
        return yellowCards;
    }

    public void setYellowCards(Integer yellowCards) {
        this.yellowCards = yellowCards;
    }

    public Integer getFauls() {
        return fauls;
    }

    public void setFauls(Integer fauls) {
        this.fauls = fauls;
    }

    public Integer getPossession() {
        return possession;
    }

    public void setPossession(Integer possession) {
        this.possession = possession;
    }


    public List<Goal> getGoals() {
        return goals;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }

    public List<PlayerPOJO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerPOJO> players) {
        this.players = players;
    }

}
