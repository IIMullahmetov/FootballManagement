package ru.kpfu.itis.android.models;

import java.io.Serializable;

/**
 * Created by hlopu on 08.04.2018.
 */

public class News implements Serializable {
    private String photo;
    private String title;
    private String content;

    public News(String photo, String title, String content) {
        this.photo = photo;
        this.title = title;
        this.content = content;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
