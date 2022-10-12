package com.example.keys.aman.settings;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.keys.R;
import com.example.keys.aman.SplashActivity;
import com.example.keys.aman.authentication.BiometricActivity;
import com.example.keys.aman.signin_login.LogInActivity;

public class TutorialActivity extends AppCompatActivity {

    private ImageView img_back;
    LogInActivity logInActivity = new LogInActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SplashActivity.isForeground = true;
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (SplashActivity.isBackground){
            Intent intent = new Intent(TutorialActivity.this, BiometricActivity.class);
            intent.putExtra(logInActivity.getREQUEST_CODE_NAME(), "LockBackGroundApp");
            startActivity(intent);
        }
        if (SplashActivity.isForeground){
            SplashActivity.isForeground = false;
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (!SplashActivity.isForeground){
            SplashActivity.isBackground = true;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SplashActivity.isForeground = true;
        finish();
        overridePendingTransition(0, R.anim.slide_out_down);
    }
}