package ru.kpfu.itis.android.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.bodyForRequest.UserForRegistration;

public class RegisterActivity extends AppCompatActivity {

    private Button btn_signUp;
    private SharedPreferences preferences;
    Context context;
    TextView password;
    TextView email;
    private TextView tvName;
    private TextView tvSurname;
    private TextView tvConfirmPassword;
    private RadioGroup radioGroupGender;
    private ProgressBar pbRegistration;
    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        context = RegisterActivity.this;

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);
        tvName = findViewById(R.id.register_name);
        tvSurname = findViewById(R.id.register_surname);
        tvConfirmPassword = findViewById(R.id.confirm_password);
        radioGroupGender = findViewById(R.id.rbgroup_gender);
        pbRegistration = findViewById(R.id.pb_registration);

        btn_signUp = findViewById(R.id.btn_register_signUp);
        btn_signUp.setOnClickListener(v -> {
            if (checkCorrectData()) {

                SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
                setVisibleProgressBar(View.VISIBLE);
                requests.registration(new UserForRegistration(email.getText().toString(), tvName.getText().toString(),
                        tvSurname.getText().toString(), getValueRadioGender(), password.getText().toString(),
                        tvConfirmPassword.getText().toString(),
                        null)).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(response -> {
                            Log.d("REGISTR CODE", response.code()+ " "+response.message());
                            if (response.code() == 200) {
                                Toast.makeText(context, "Подтвердите ваш email, письмо было выслано на почту", Toast.LENGTH_LONG).show();
                                finish();
                            } else {
                                setVisibleProgressBar(View.GONE);
                                Toast.makeText(context, "Регистрация не удалась!", Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, "Throw " + response.message(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(context, "Throw " + response.body(), Toast.LENGTH_SHORT).show();
                            }
                        }, throwable -> {
                            setVisibleProgressBar(View.GONE);
                            Log.d("REGISTER", "THROW "+throwable.getMessage());
                            Toast.makeText(context, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        });
            }
        });

    }

    public void setVisibleProgressBar(int visibility) {
        switch (visibility) {
            case View.GONE:
                pbRegistration.setVisibility(View.GONE);
                btn_signUp.setVisibility(View.VISIBLE);
                break;
            case View.VISIBLE:
                pbRegistration.setVisibility(View.VISIBLE);
                btn_signUp.setVisibility(View.GONE);
                break;
        }
    }

    public String getValueRadioGender() {
        switch (radioGroupGender.getCheckedRadioButtonId()) {
            case R.id.rb_man:
                return "man";
            case R.id.rb_woman:
                return "woman";
        }
        return null;
    }

    public boolean checkCorrectData() {
        if (!validateEmail(email.getText().toString())) {
            Toast.makeText(context, "Введите корректный email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (tvName.length() == 0 || tvSurname.length() == 0 || password.length() == 0 || tvConfirmPassword.length() == 0) {
            Toast.makeText(context, "Заполните все поля!", Toast.LENGTH_SHORT).show();
            return false;

        } else if (password.length() < 6) {
            Toast.makeText(context, "Длина пароля должна быть больше 6 символов!", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!password.getText().toString().equals(tvConfirmPassword.getText().toString())) {
            password.setText("");
            tvConfirmPassword.setText("");
            Toast.makeText(context, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
            return false;
        } else if (radioGroupGender.getCheckedRadioButtonId() == -1) {
            Toast.makeText(context, "Выберите пол", Toast.LENGTH_SHORT).show();
            return false;
        } else return true;
    }

    public boolean validateEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
