package ru.kpfu.itis.android.service;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.providers.SharedPreferencesProvider;

public class ApiService {
    private SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
    private Context context;

    public ApiService(Context context) {
        this.context = context;
    }

    @SuppressLint("CheckResult")
    public void getUser(String token) {
        requests.getUser("Bearer " + token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.code() == 200) {
                        SharedPreferencesProvider.getInstance(context).saveUser(response.body());
                        Toast.makeText(context, "Данные пользователя успешно подгружены", Toast.LENGTH_SHORT).show();
                    } else
                        Log.d("getUser", String.valueOf(response.code()));
                        Toast.makeText(context, "Данные пользователя не были подгружены", Toast.LENGTH_SHORT).show();

                }, throwable -> {
                    Log.d("getUser", String.valueOf(throwable.getMessage()));
                    Toast.makeText(context, "Данные пользователя не были подгружены", Toast.LENGTH_SHORT).show();
                });
    }
}
