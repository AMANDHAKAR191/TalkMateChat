package com.talkmate.aman;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;


import com.talkmate.aman.signin_login.LogInActivity;

public class SplashActivity extends AppCompatActivity {


    private String[] PERMISSIONS;
    public static boolean isConnected = false;
    ConnectivityManager connectivityManager;
    public static boolean isForeground = false;
    public static boolean isBackground = false;
    ImageView imageView, imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        isForeground = true;
        Intent i = new Intent(SplashActivity.this, LogInActivity.class);
        startActivity(i);
        finish();

    }


//    public void createShortcutOfApp() {
//
//        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
//
//        Intent intent = new Intent(SplashActivity.this, PasswordGeneratorActivity.class);
//        intent.setAction(Intent.ACTION_VIEW);
//        ShortcutInfo shortcutInfo = new ShortcutInfo.Builder(this, "short1").
//                setShortLabel("Gen Password").
//                setLongLabel("Open PassGenActivity ").
//                setIcon(Icon.createWithResource(SplashActivity.this, R.drawable.add)).
//                setIntent(intent).
//                build();
//        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcutInfo));
//    }


}