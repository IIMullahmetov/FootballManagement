package ru.kpfu.itis.android.api;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.kpfu.itis.android.models.Authorization;
import ru.kpfu.itis.android.models.User;

/**
 * Created by hlopu on 12.05.2018.
 */

public interface SportApiRequests {

    @POST("/auth/login")
    Observable<Authorization> authorization(@Query("email") String email, @Query("password") String password);

    @POST("/auth/google")
    Observable<Authorization> authorizationWithGoogle(@Query("email") String email, @Query("firstName") String firstName,
                                             @Query("lastName") String lastName, @Query("birthday") String birthday,
                                             @Query("gender") String gender, @Query("googleToken") String googleToken);
}
