package ru.kpfu.itis.android.models;

import java.io.Serializable;

/**
 * Created by hlopu on 12.05.2018.
 */

public class User implements Serializable {
    String email;
    String firstName;
    String lastName;
    String gender;
    String tokken;

    public User(String email, String firstName, String lastName, String gender, String tokken) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.tokken = tokken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTokken() {
        return tokken;
    }

    public void setTokken(String tokken) {
        this.tokken = tokken;
    }
}
