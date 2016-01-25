package com.janacare.janacareproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity implements AppConstants{

    /**
     * Handler - To display Main activity after 1.5 seconds delay.
     */
    private final Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
            finish();
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(runnable, this.getIntent().getData()==null?SPLASH_SCREEN_DELAY:1);
    }


    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}