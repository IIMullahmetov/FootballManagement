package ru.kpfu.itis.android.models;

/**
 * Created by Bulat on 24.04.2018 at 19:34.
 */
public class TableItem {

    private int id;
    private String icon;

    private String teamName;

    private String games;

    private String wins;

    private String draws;

    private String losses;

    private String points;

    public TableItem(int id, String icon, String teamName, String games, String wins, String draws, String losses, String points) {
        this.id = id;
        this.icon = icon;
        this.teamName = teamName;
        this.games = games;
        this.wins = wins;
        this.draws = draws;
        this.losses = losses;
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getLosses() {
        return losses;
    }

    public void setLosses(String losses) {
        this.losses = losses;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }
}
