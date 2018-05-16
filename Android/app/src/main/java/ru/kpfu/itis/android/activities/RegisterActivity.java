package ru.kpfu.itis.android.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import ru.kpfu.itis.android.R;

public class RegisterActivity extends AppCompatActivity {

    Button btn_signUp;
    SharedPreferences preferences;
    Context context;
    TextView password;
    TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        preferences = context.getSharedPreferences("preferences", MODE_PRIVATE);

        password = findViewById(R.id.password);
        email = findViewById(R.id.email);

        btn_signUp = findViewById(R.id.btn_signUp);
        btn_signUp.setOnClickListener(v -> {
            
        });
    }
}
