package ru.kpfu.itis.android.api;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ru.kpfu.itis.android.models.Authorization;
import ru.kpfu.itis.android.models.UserForRegistration;
import ru.kpfu.itis.android.models.UserPost;

/**
 * Created by hlopu on 12.05.2018.
 */

public interface SportApiRequests {

    @POST("/auth/login")
    Observable<Response<Authorization>> authorization(@Body UserPost user);

    @POST("/auth/google")
    Observable<Response<Authorization>> authorizationWithGoogle(@Query("email") String email, @Query("firstName") String firstName,
                                             @Query("lastName") String lastName, @Query("birthday") String birthday,
                                             @Query("gender") String gender, @Query("googleToken") String googleToken);
    @POST("/registration/register")
    Observable<Response<String>> registration(@Body UserForRegistration user);
}
