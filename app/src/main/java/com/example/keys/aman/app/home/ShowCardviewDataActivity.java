package com.example.keys.aman.app.home;

import static com.example.keys.aman.app.SplashActivity.mRewardedAd;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.keys.R;
import com.example.keys.aman.app.home.addpassword.addPasswordData;
import com.example.keys.aman.app.notes.pinLockFragment;
import com.example.keys.aman.app.signin_login.LogInActivity;
import com.google.android.gms.ads.OnUserEarnedRewardListener;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ShowCardviewDataActivity extends AppCompatActivity {
    TextView tvDisplayLogin, tvDisplayWebsite, tvTitle;
    TextInputEditText tietDisplayPassword;
    TextInputLayout tilDisplayPassword;
    ImageView imgBack;
    ImageView imgWebsiteLogo, imgOpenWebsite;
    private String comingDate, comingLoginName, comingLoginPassword, comingLoginWebsiteName, comingLoginWebsiteLink;
    private Bitmap bmWebsiteLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_show_cardview_data);
        //Hooks
        tvTitle = findViewById(R.id.tv_img_title);
        tvDisplayLogin = findViewById(R.id.displaylogin);
        tietDisplayPassword = findViewById(R.id.displaypassword);
        tilDisplayPassword = findViewById(R.id.til_displaypassword);
        tvDisplayWebsite = findViewById(R.id.displaywebsite);
        imgBack = findViewById(R.id.img_back);
        imgWebsiteLogo = findViewById(R.id.website_logo);
        imgOpenWebsite = findViewById(R.id.open_website);

        Intent intent = getIntent();
        comingDate = intent.getStringExtra("date");
        comingLoginName = getIntent().getStringExtra("loginname");
        comingLoginPassword = getIntent().getStringExtra("loginpassowrd");
        comingLoginWebsiteName = getIntent().getStringExtra("loginwebsite_name");
        comingLoginWebsiteLink = getIntent().getStringExtra("loginwebsite_link");
        tvDisplayLogin.setText(comingLoginName);
        tietDisplayPassword.setText(comingLoginPassword);
        String Title = comingLoginWebsiteName.substring(0, 1).toUpperCase() + comingLoginWebsiteName.substring(1);
        tvDisplayWebsite.setText(comingLoginWebsiteLink);
        tvTitle.setText(Title.replace("_","."));

        tilDisplayPassword.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(tietDisplayPassword.getInputType());
                if (tietDisplayPassword.getInputType() == InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD) {


                    tietDisplayPassword.setInputType(InputType.TYPE_CLASS_TEXT |
                            InputType.TYPE_TEXT_VARIATION_PASSWORD);

                } else {
                    if (mRewardedAd != null) {
                        Activity activityContext = ShowCardviewDataActivity.this;
                        mRewardedAd.show(activityContext, new OnUserEarnedRewardListener() {
                            @Override
                            public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                                // Handle the reward.
                                int rewardAmount = rewardItem.getAmount();
                                String rewardType = rewardItem.getType();
                            }
                        });
                    } else {
                        Toast.makeText(ShowCardviewDataActivity.this, "The rewarded ad wasn't ready yet.", Toast.LENGTH_SHORT).show();
                    }
                    Intent intent1 = new Intent(getApplicationContext(), pinLockFragment.class);
                    intent1.putExtra(LogInActivity.REQUEST_CODE_NAME, "ShowCardviewDataActivity");
                    intent1.putExtra("title", "Enter Pin");
                    startActivityForResult(intent1, 123);


                }
                tietDisplayPassword.setSelection(tietDisplayPassword.getText().length());

            }
        });
        
        imgWebsiteLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("website_logo1 " + bmWebsiteLogo);
                Toast.makeText(ShowCardviewDataActivity.this, "logo fetched!!" + bmWebsiteLogo, Toast.LENGTH_SHORT).show();
            }
        });

        cardViewDataThreadRunnable dataThreadRunnable = new cardViewDataThreadRunnable(comingLoginWebsiteLink);
        new Thread(dataThreadRunnable).start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String result = data.getStringExtra("result");
            if (result.equals("yes")) {
                tietDisplayPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
        }
    }

    public void editdata(View view) {
        Intent intent = new Intent(ShowCardviewDataActivity.this, addPasswordData.class);
        intent.putExtra(LogInActivity.REQUEST_CODE_NAME, "ShowCardviewDataActivity");
        intent.putExtra("date", comingDate);
        intent.putExtra("loginname", comingLoginName);
        intent.putExtra("loginpassowrd", comingLoginPassword);
        intent.putExtra("loginwebsite_name", comingLoginWebsiteName);
        intent.putExtra("loginwebsite_link", comingLoginWebsiteLink);
        startActivity(intent);
    }
    public void goBack(View view) {
        finish();
        overridePendingTransition(0, R.anim.slide_out_down);
    }
//    public void openWebsite(View view) {
//        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(addPasswordData.addWebsiteLink));
//        Toast.makeText(this, "Opening Website", Toast.LENGTH_SHORT).show();
//        startActivity(intent);
//    }
    private static Bitmap fetchFavicon(Uri uri) {
        final Uri iconUri = uri.buildUpon().path("favicon.ico").build();

        InputStream is = null;
        BufferedInputStream bis = null;
        try {
            URLConnection conn = new URL(iconUri.toString()).openConnection();
            conn.connect();
            is = conn.getInputStream();
            bis = new BufferedInputStream(is, 8192);
            return BitmapFactory.decodeStream(bis);
        } catch (IOException e) {
            return null;
        }
    }
    public class cardViewDataThreadRunnable implements Runnable {

        private String tempLoginWebsiteLink;

        public cardViewDataThreadRunnable (String loginWebsiteLink){
            this.tempLoginWebsiteLink = loginWebsiteLink;
        }

        Handler handler = new Handler();
        @Override
        public void run() {
            bmWebsiteLogo = fetchFavicon(Uri.parse(tempLoginWebsiteLink));
            handler.post(new Runnable() {
                @Override
                public void run() {
                    imgWebsiteLogo.setImageBitmap(bmWebsiteLogo);
                }
            });
        }
    }


}