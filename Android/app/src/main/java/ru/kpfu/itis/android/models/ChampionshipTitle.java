package ru.kpfu.itis.android.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ChampionshipTitle extends ExpandableGroup<Match> {

    private String title;
    List<Match> list;

    public ChampionshipTitle(String title, List<Match> items) {
        super(title, items);
        this.title = title;
        list = items;
    }


    @Override
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
