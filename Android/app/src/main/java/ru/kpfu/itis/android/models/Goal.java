package ru.kpfu.itis.android.models;

import java.io.Serializable;

/**
 * Created by hlopu on 23.04.2018.
 */

public class Goal implements Serializable {
    private String NameBombardir;
    private String NameAssistant;
    private String Time;

    public Goal(String NameBombardir, String NameAssistant, String Time) {
        this.NameBombardir = NameBombardir;
        this.NameAssistant = NameAssistant;
        this.Time = Time;
    }

    public String getNameBombardir() {
        return NameBombardir;
    }

    public void setNameBombardir(String NameBombardir) {
        this.NameBombardir = NameBombardir;
    }

    public String getNameAssistant() {
        return NameAssistant;
    }

    public void setNameAssistant(String NameAssistant) {
        this.NameAssistant = NameAssistant;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }
}
