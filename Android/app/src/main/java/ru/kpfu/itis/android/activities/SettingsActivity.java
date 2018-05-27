package ru.kpfu.itis.android.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.kpfu.itis.android.R;
import ru.kpfu.itis.android.api.SportApi;
import ru.kpfu.itis.android.api.SportApiRequests;
import ru.kpfu.itis.android.models.bodyForRequest.UserForRegistration;
import ru.kpfu.itis.android.providers.SharedPreferencesProvider;

public class SettingsActivity extends AppCompatActivity {

    Button addAvatar;
    Button btnExit;
    Activity context = this;
    ImageView avatar;
    Uri mCropImageUri;
    TextView tvName;
    Button tvChangeUsername;
    Button tvChangePassword;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);


        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        TextView title = myToolbar.findViewById(R.id.toolbar_title);
        title.setText("Настройки аккаунта");
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Русский");
        arrayList.add("English");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);

//        Spinner spinner = findViewById(R.id.spinner);
//        spinner.setAdapter(arrayAdapter);

        btnExit = findViewById(R.id.exit);
        avatar = findViewById(R.id.avatar);
        tvName = findViewById(R.id.nickname);
        tvChangeUsername = findViewById(R.id.change_username);
        tvChangePassword = findViewById(R.id.change_password);
        tvName.setText(SharedPreferencesProvider.getInstance(this).getUser().getFirstName() + " "
                + SharedPreferencesProvider.getInstance(this).getUser().getLastName());

        btnExit.setOnClickListener(view -> {
            SharedPreferencesProvider.getInstance(this).deleteUserTokken();
            SharedPreferencesProvider.getInstance(this).deleteUser();
            Intent intent = new Intent(SettingsActivity.this, AuthActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        });

        if (!sharedPref.getString("profile", "").equals("")) {
            mCropImageUri = Uri.parse(sharedPref.getString("profile", ""));
            Glide.with(context)
                    .load(mCropImageUri)
                    .apply(RequestOptions.circleCropTransform())
                    .into(avatar);
        }

        addAvatar = findViewById(R.id.add_avatar);
        addAvatar.setOnClickListener(v -> {
            CropImage.startPickImageActivity(context);
        });
        tvChangeUsername.setOnClickListener(v -> {
            LayoutInflater li = LayoutInflater.from(this);

            View view = li.inflate(R.layout.change_username_fragment, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            TextView tvTitle = view.findViewById(R.id.tv_dialog_title);
            EditText etFirst = view.findViewById(R.id.et1_dialog);
            EditText etSecond = view.findViewById(R.id.et2_dialog);
            ProgressBar pbDialog = view.findViewById(R.id.pb_dialog);
            tvTitle.setText("Изменить имя:");
            etFirst.setHint("Имя");
            etSecond.setHint("Фамилия");
            builder.setCancelable(false)
                    .setPositiveButton("OK",
                            (dialog, id) -> {
                                if (etFirst.length() != 0 && etSecond.length() != 0) {
                                    SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
                                    requests.changeUsername(new UserForRegistration(etFirst.getText().toString(), etSecond.getText().toString(), "man"),
                                            "Bearer " + SharedPreferencesProvider.getInstance(this).getUserTokken())
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(response -> {
                                                if (response.code() == 200) {
                                                    Toast.makeText(this, "Изменение прошло успешно!", Toast.LENGTH_SHORT).show();
                                                    tvName.setText(etFirst.getText().toString() + " " + etSecond.getText().toString());
                                                    dialog.cancel();
                                                } else {
                                                    pbDialog.setVisibility(View.GONE);
                                                    Log.d("Change name", "THROW " + response.message());
                                                }
                                            }, throwable -> {
                                                pbDialog.setVisibility(View.GONE);
                                                Log.d("Change name", "THROW " + throwable.getMessage());
                                                Toast.makeText(this, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                            });
                                } else
                                    Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                            })
                    .setNegativeButton("Отмена",
                            (dialog, id) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });


        tvChangePassword.setOnClickListener(v -> {
            LayoutInflater li = LayoutInflater.from(this);

            View view = li.inflate(R.layout.change_username_fragment, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setView(view);
            TextView tvTitle = view.findViewById(R.id.tv_dialog_title);
            EditText etFirst = view.findViewById(R.id.et1_dialog);
            EditText etSecond = view.findViewById(R.id.et2_dialog);
            ProgressBar pbDialog = view.findViewById(R.id.pb_dialog);
            tvTitle.setText("Изменить пароль:");
            etFirst.setHint("Введите новый пароль");
            etSecond.setHint("Повторите пароль");
            builder.setCancelable(false)
                    .setPositiveButton("OK",
                            (dialog, id) -> {
                                if (etFirst.length() != 0 && etSecond.length() != 0) {
                                    SportApiRequests requests = SportApi.getInstance().getmSportApiRequests();
                                    requests.changePassword(new UserForRegistration(etFirst.getText().toString(), etSecond.getText().toString()),
                                            "Bearer " + SharedPreferencesProvider.getInstance(this).getUserTokken())
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(response -> {
                                                if (response.code() == 200) {
                                                    Toast.makeText(this, "Изменение прошло успешно!", Toast.LENGTH_SHORT).show();
                                                    dialog.cancel();
                                                } else {
                                                    pbDialog.setVisibility(View.GONE);
                                                    Log.d("Change password", "THROW " + response.message());
                                                }
                                            }, throwable -> {
                                                pbDialog.setVisibility(View.GONE);
                                                Log.d("Change password", "THROW " + throwable.getMessage());
                                                Toast.makeText(this, "Throw " + throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                            });
                                } else
                                    Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show();
                            })
                    .setNegativeButton("Отмена",
                            (dialog, id) -> dialog.cancel());

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        });
    }

    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(context, data);

            if (CropImage.isReadExternalStoragePermissionsRequired(context, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                // no permissions required or already granted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == Activity.RESULT_OK) {
                Uri resultUri = result.getUri();
                mCropImageUri = resultUri;

                Glide.with(context)
                        .load(resultUri)
                        .apply(RequestOptions.circleCropTransform())
                        .into(avatar);

                SharedPreferences sharedPref = context.getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                sharedPref.edit().putString("profile", String.valueOf(mCropImageUri)).apply();


            } else {
                Toast.makeText(context, "Что-то пошло не так, повторите позднее", Toast.LENGTH_LONG).show();
            }
        }
    }


    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setCropShape(CropImageView.CropShape.OVAL)
                .setFixAspectRatio(true)
                .start(this);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
