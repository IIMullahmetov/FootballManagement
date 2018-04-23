package ru.kpfu.itis.android.models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hlopu on 08.04.2018.
 */

public class Championship implements Serializable {
    private String name;
    private List<News> news;

    public Championship(String name, List<News> news) {
        this.name = name;
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
}
