package ru.kpfu.itis.android.activities;

import android.content.Context;
import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.UserPost;

public class AuthActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = this;
    public static int RC_SIGN_IN = 101;
    Button btn_signIn;
    SignInButton btn_googleSignIn;
    private ProgressBar pbAuth;
    private TextInputEditText etEmail;
    private TextInputEditText etPassword;
    private ConstraintLayout cLayout;

    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);


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

    }

    @Override

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_signIn:
                setVisibleProgressBar(View.VISIBLE);
                SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
                requests.authorization(new UserPost(etEmail.getText().toString(), etPassword.getText().toString()))
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            if (response.code() == 200) {
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (response.code() == 400) {
                                Toast.makeText(context, "Неверный логин или пароль", Toast.LENGTH_SHORT).show();
                            }
                        }, throwable -> {
                            setVisibleProgressBar(View.GONE);
                            Toast.makeText(context, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        });
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

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            Log.d("ACCOUNT", account.getEmail());
            SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
            System.out.println(account.getEmail() + " " + account.getIdToken());
            requests.authorizationWithGoogle(account.getEmail(), account.getFamilyName(), account.getGivenName(),
                    //TODO birthday and gender
                    "17.04.1997", "man", account.getIdToken())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(user -> {
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                        Toast.makeText(context, "Вход выполнен успешно!", Toast.LENGTH_SHORT).show();
                    }, throwable -> {
                        setVisibleProgressBar(View.GONE);
                        Toast.makeText(context, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    });

            // Signed in successfully, show authenticated UI.
//            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("ERROR", "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
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
}
