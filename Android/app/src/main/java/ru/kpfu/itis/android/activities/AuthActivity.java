package ru.kpfu.itis.android.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.bodyForRequest.UserPost;
import ru.kpfu.itis.android.providers.SharedPreferencesProvider;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {

    Context context = this;
    public static int RC_SIGN_IN = 101;
    Button btn_signIn;
    private Button btnSignUp;
    SignInButton btn_googleSignIn;
    private ProgressBar pbAuth;
    private TextInputEditText etEmail;
    private GoogleApiClient mGoogleApiClient;

    private TextInputEditText etPassword;
    private ConstraintLayout cLayout;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        if (SharedPreferencesProvider.getInstance(context).getUserTokken() != null) {
            startMainActivity();
        }

        btn_signIn = findViewById(R.id.btn_signIn);
        btn_signIn.setOnClickListener(this);
        pbAuth = findViewById(R.id.pb_auth);
        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        cLayout = findViewById(R.id.clayout_auth);

        btn_googleSignIn = findViewById(R.id.google_auth);
        btn_googleSignIn.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)

                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnSignUp = findViewById(R.id.btn_signUp);
        btnSignUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
        switch (v.getId()) {
            case R.id.btn_signIn:
                setVisibleProgressBar(View.VISIBLE);
                requests.authorization(new UserPost(etEmail.getText().toString(), etPassword.getText().toString()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            if (response.code() == 200) {
                                SharedPreferencesProvider.getInstance(this).saveUserTokken(response.body().getAccessToken());
                                downloadDataForUser(response.body().getAccessToken());
                            } else if (response.code() == 400) {
                                setVisibleProgressBar(View.GONE);
                                Log.d("Auth", "THROW " + response.code());
                                Toast.makeText(context, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                            } else {
                                setVisibleProgressBar(View.GONE);
                                Log.d("Auth", "THROW " + response.code());
                            }

                        }, throwable -> {
                            setVisibleProgressBar(View.GONE);
                            Log.d("Auth", "THROW " + throwable.getMessage());
                            Toast.makeText(context, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        });
                break;

            case R.id.btn_signUp:
                Intent intent = new Intent(AuthActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;

            case R.id.google_auth:
                setVisibleProgressBar(View.VISIBLE);
                signIn();
                setVisibleProgressBar(View.GONE);
                break;
        }
    }

    private void signIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        } else {
            Toast.makeText(context, "Не удалось войти", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("CheckResult")
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {


        if (completedTask.isSuccessful()) {
            Log.d("Result google", completedTask.getResult().getEmail());
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = completedTask.getResult();
            System.out.println("DISPLAY NAME    " + acct.getDisplayName());

            SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
            requests.authorizationWithGoogle(acct.getEmail(), acct.getFamilyName(), acct.getGivenName(),
                    //TODO birthday and gender
                    "17.04.1997", "man", acct.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(response -> {
                        SharedPreferencesProvider.getInstance(this).saveUserTokken(response.body().getAccessToken());
                        downloadDataForUser(response.body().getAccessToken());
                        Toast.makeText(context, "Вход выполнен успешно!", Toast.LENGTH_SHORT).show();
                    }, throwable -> {
                        setVisibleProgressBar(View.GONE);
                        Toast.makeText(context, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    });
        } else {
            Log.d("Google Auth", "Не удалось");
        }

    }

    private void checkAccount() {
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);

    }

    private void updateUI(GoogleSignInAccount account) {

    }

    private void setVisibleProgressBar(int visibility) {
        switch (visibility) {
            case View.GONE:
                pbAuth.setVisibility(View.GONE);
                cLayout.setVisibility(View.VISIBLE);
                break;
            case View.VISIBLE:
                pbAuth.setVisibility(View.VISIBLE);
                cLayout.setVisibility(View.GONE);
                break;

        }
    }

    @SuppressLint("CheckResult")
    public void downloadDataForUser(String token) {
        Log.d("TOKEN", "Bearer " + token);
        SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
        requests.getUser("Bearer " + token).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    if (response.code() == 200) {
                        SharedPreferencesProvider.getInstance(context).saveUser(response.body());
                        Toast.makeText(context, "Данные пользователя успешно подгружены", Toast.LENGTH_SHORT).show();
                        startMainActivity();
                    } else {
                        Log.d("getUser CODE", String.valueOf(response.code()));
                        setVisibleProgressBar(View.GONE);
                        Toast.makeText(context, "Данные пользователя не были подгружены", Toast.LENGTH_SHORT).show();
                    }

                }, throwable -> {
                    setVisibleProgressBar(View.GONE);
                    Log.d("getUser", String.valueOf(throwable.getMessage()));
                    Toast.makeText(context, "Данные пользователя не были подгружены", Toast.LENGTH_SHORT).show();
                });
    }

    public void startMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d("GOOGLE_AUTH_ERROR", "onConnectionFailed:" + connectionResult);
    }
}
