package ru.kpfu.itis.android.models.news;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class NewsDetail implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("createDt")
    @Expose
    private String createDt;
    @SerializedName("items")
    @Expose
    private List<ItemNews> items = null;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreateDt() {
        return createDt;
    }

    public void setCreateDt(String createDt) {
        this.createDt = createDt;
    }

    public List<ItemNews> getItems() {
        return items;
    }

    public void setItems(List<ItemNews> items) {
        this.items = items;
    }
}
