package ru.kpfu.itis.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hlopu on 08.04.2018.
 */

public class Championship implements Serializable {
    private int icon;
    private List<News> news;

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("startDt")
    @Expose
    private String startDt;
    @SerializedName("endDt")
    @Expose
    private String endDt;
    @SerializedName("items")
    @Expose
    private List<TableChampionship> items = null;
    @SerializedName("message")
    @Expose
    private String message;

    public Championship(String name, int icon, List<News> news) {
        this.name = name;
        this.icon = icon;
        this.news = news;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<News> getNews() {
        return news;
    }

    public void setNews(List<News> news) {
        this.news = news;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getEndDt() {
        return endDt;
    }

    public void setEndDt(String endDt) {
        this.endDt = endDt;
    }

    public List<TableChampionship> getItems() {
        return items;
    }

    public void setItems(List<TableChampionship> items) {
        this.items = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
