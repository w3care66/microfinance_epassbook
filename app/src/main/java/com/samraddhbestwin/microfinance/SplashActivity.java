package com.samraddhbestwin.microfinance;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import com.samraddhbestwin.microfinance.Session.SessionManager;

import java.util.HashMap;
import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 4000;
    SessionManager sessionManager;
    String member_id,pin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        Objects.requireNonNull(getSupportActionBar()).hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.activity_splash);

          sessionManager = new SessionManager(this);
          HashMap<String, String> userdata = sessionManager.getUserDetails();
          member_id=userdata.get(SessionManager.KEY_member_id);
          pin=userdata.get(SessionManager.KEY_pin);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    if (member_id == null && pin ==null ){
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(member_id != null && pin !=null) {
                           Intent intent = new Intent(SplashActivity.this, PinActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("member_id",member_id);
                            bundle.putString("pin",pin);
                            startActivity(intent);
                            finish();
                    }else if(member_id != null && pin == null) {
                        Intent intent = new Intent(SplashActivity.this, PinActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("member_id",member_id);
                        startActivity(intent);
                        finish();
                    }

            }
        }, SPLASH_DISPLAY_LENGTH);

    }




}