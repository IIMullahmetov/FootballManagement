package ru.kpfu.itis.android.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

import ru.kpfu.itis.android.models.modelForList.MatchBodyInList;

public class ChampionshipTitle extends ExpandableGroup<MatchBodyInList> {

    private String title;
    List<MatchBodyInList> list;

    public ChampionshipTitle(String title, List<MatchBodyInList> items) {
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
