package ru.kpfu.itis.android.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hlopu on 12.05.2018.
 */

public class SportApi {
    private static final String BASE_URL = "http://footballmanagement.azurewebsites.net";
    private SportApiRequests mSportApiRequests;
    private static SportApi sportApi;

    public SportApi() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .build();
        mSportApiRequests = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(SportApiRequests.class);
    }
    public static SportApi getInstance(){
        if(sportApi==null) sportApi = new SportApi();
        return sportApi;
    }

    public SportApiRequests getmSportApiRequests() {
        return mSportApiRequests;
    }

}
