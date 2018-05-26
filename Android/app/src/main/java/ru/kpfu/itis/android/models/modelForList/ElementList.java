package ru.kpfu.itis.android.models.modelForList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ElementList<TYPE> implements Serializable {
    @SerializedName("items")
    @Expose
    private List<TYPE> items = null;
    @SerializedName("message")
    @Expose
    private String message;

    public List<TYPE> getItems() {
        return items;
    }

    public void setItems(List<TYPE> items) {
        this.items = items;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
