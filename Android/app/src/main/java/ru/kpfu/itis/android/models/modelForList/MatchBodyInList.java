package ru.kpfu.itis.android.models.modelForList;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MatchBodyInList implements Serializable, Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("startDt")
    @Expose
    private String startDt;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("home")
    @Expose
    private TeamForList home;
    @SerializedName("guest")
    @Expose
    private TeamForList guest;

    protected MatchBodyInList(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        startDt = in.readString();
        status = in.readString();
    }

    public static final Creator<MatchBodyInList> CREATOR = new Creator<MatchBodyInList>() {
        @Override
        public MatchBodyInList createFromParcel(Parcel in) {
            return new MatchBodyInList(in);
        }

        @Override
        public MatchBodyInList[] newArray(int size) {
            return new MatchBodyInList[size];
        }
    };

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TeamForList getHome() {
        return home;
    }

    public void setHome(TeamForList home) {
        this.home = home;
    }

    public TeamForList getGuest() {
        return guest;
    }

    public void setGuest(TeamForList guest) {
        this.guest = guest;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(id);
        }
        parcel.writeString(startDt);
        parcel.writeString(status);
    }
}
