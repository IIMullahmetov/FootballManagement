package ru.kpfu.itis.android.api;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import ru.kpfu.itis.android.models.Authorization;
import ru.kpfu.itis.android.models.Championship;
import ru.kpfu.itis.android.models.Match;
import ru.kpfu.itis.android.models.MatchPOJO;
import ru.kpfu.itis.android.models.modelForList.ChampionshipInList;
import ru.kpfu.itis.android.models.modelForList.ElementList;
import ru.kpfu.itis.android.models.News;
import ru.kpfu.itis.android.models.User;
import ru.kpfu.itis.android.models.bodyForRequest.UserForRegistration;
import ru.kpfu.itis.android.models.bodyForRequest.UserPost;
import ru.kpfu.itis.android.models.modelForList.MatchBodyInList;

/**
 * Created by hlopu on 12.05.2018.
 */

public interface SportApiRequests {
    public static final String DOWNLOAD_IMAGE = "http://footballmanagement.azurewebsites.net/file/download?guid=";

    @POST("/auth/login")
    Observable<Response<Authorization>> authorization(@Body UserPost user);

    @POST("/auth/google")
    Observable<Response<Authorization>> authorizationWithGoogle(@Query("email") String email, @Query("firstName") String firstName,
                                                                @Query("lastName") String lastName, @Query("birthday") String birthday,
                                                                @Query("gender") String gender, @Query("googleToken") String googleToken);

    @POST("/registration/register")
    Observable<Response<Void>> registration(@Body UserForRegistration user);

    @GET("/get")
    Observable<Response<User>> getUser(@Header("Authorization") String token);

    @GET("/post/get_list")
    Observable<Response<ElementList<News>>> getPosts();

    @GET("/match/get_list")
    Observable<Response<ElementList<MatchBodyInList>>> getMatches();

    @GET("/match/get/{id}")
    Observable<Response<MatchPOJO>> getMatch(@Path("id") int id);

    @GET("/tourney/get_list")
    Observable<Response<ElementList<ChampionshipInList>>> getChampionships(@Query("size") int size);

    @GET("/tourney/get/{id}")
    Observable<Response<Championship>> getChampionship(@Path("id") int id);

}
