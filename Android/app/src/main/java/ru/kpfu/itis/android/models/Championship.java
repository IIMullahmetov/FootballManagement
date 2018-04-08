package ru.kpfu.itis.android.models;

import java.io.Serializable;

/**
 * Created by hlopu on 08.04.2018.
 */

public class Championship implements Serializable {
    private String name;

    public Championship(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
