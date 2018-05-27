package ru.kpfu.itis.android.models.bodyForRequest;

import java.io.Serializable;

/**
 * Created by hlopu on 16.05.2018.
 */

public class UserForRegistration implements Serializable {
    String email;
    String firstName;
    String lastName;
    String password;
    String confirmPassword;
    String birthDay;
    String gender;
    String googleToken;

    public UserForRegistration(String email, String firstName, String lastName, String gender, String googleToken) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.gender = gender;
        this.googleToken = googleToken;
    }

    public UserForRegistration(String email, String firstName, String lastName, String gender, String password, String confirmPassword, String birthDay) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.birthDay = birthDay;
    }

    public UserForRegistration(String firstName, String lastName, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
    }

    public UserForRegistration(String password, String confirmPassword) {
        this.password = password;
        this.confirmPassword = confirmPassword;
    }
}
