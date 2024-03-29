package com.talkmate.aman.settings;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.talkmate.aman.data.MyPreference;
import com.talkmate.aman.R;
import com.talkmate.aman.SplashActivity;
import com.talkmate.aman.authentication.AppLockCounterClass;
import com.talkmate.aman.base.TabLayoutActivity;
import com.talkmate.aman.iActivityTracking;
import com.talkmate.aman.signin_login.LogInActivity;

public class TutorialActivity extends AppCompatActivity implements iActivityTracking {

    private ImageView img_back;
    LogInActivity logInActivity = new LogInActivity();
    TabLayoutActivity tabLayoutActivity = new TabLayoutActivity();
    iActivityTracking iActivityTracking = new TutorialActivity();
    //todo 2 object calling of AppLockCounterClass
    AppLockCounterClass appLockCounterClass = new AppLockCounterClass(TutorialActivity.this, TutorialActivity.this);
    MyPreference myPreference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        myPreference = MyPreference.getInstance(this);
        //todo 3 when is coming from background or foreground always isForeground false
        SplashActivity.isForeground = false;
        iActivityTracking.onComingFromActivity();
        img_back = findViewById(R.id.img_back);

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo 4 if app is going to another activity make isForeground = true
                SplashActivity.isForeground = true;
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        //todo 9 onStartOperation, it will check app is
        // coming from foreground or background.
        appLockCounterClass.onStartOperation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //todo 10 onPauseOperation, it will check app is
        // going to foreground or background.
        // if UI component made isForeground = true then it
        // is going to another activity then this method will make
        // isForeground = false, so user will not be verified.
        // if UI component is not clicked then it
        // is going in background then this method will make
        // isBackground = true and timer will started,
        // at time of return, user will be verified.
        appLockCounterClass.checkedItem = myPreference.getLockAppSelectedOption();
        appLockCounterClass.onPauseOperation();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //todo 11 app is going to close no to do anything
        SplashActivity.isForeground = true;
        finish();
        overridePendingTransition(0, R.anim.slide_out_down);
    }

    @Override
    public void onComingFromActivity() {
        Intent intent = getIntent();
        String comingFromActivity = intent.getStringExtra(logInActivity.REQUEST_CODE_NAME);
        System.out.println("comingFromActivity: " + comingFromActivity);
    }
}