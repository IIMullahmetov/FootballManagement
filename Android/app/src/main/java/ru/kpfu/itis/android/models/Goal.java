package ru.kpfu.itis.android.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by hlopu on 23.04.2018.
 */

public class Goal implements Serializable {
    @SerializedName("goalDt")
    @Expose
    private String goalDt;
    @SerializedName("authorId")
    @Expose
    private Integer authorId;
    @SerializedName("assistantId")
    @Expose
    private Integer assistantId;

    public String getGoalDt() {
        return goalDt;
    }

    public void setGoalDt(String goalDt) {
        this.goalDt = goalDt;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getAssistantId() {
        return assistantId;
    }

    public void setAssistantId(Integer assistantId) {
        this.assistantId = assistantId;
    }
}
