package com.fitbud.workoutbuddy.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.fitbud.workoutbuddy.R;

/**
 * Created by Rishabh Bhatia on 5/22/2016.
 */
public class SplashActivity extends WorkoutBuddyActivity {

    private Handler handler = null;
    private Runnable runnable = null;
    private final int SPLASH_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void onPause() {

        if(handler != null && runnable != null)
        {
            handler.removeCallbacks(runnable);
            handler = null;
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(handler == null)
        {
            handler = new Handler();

            runnable = new Runnable() {
                @Override
                public void run() {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
                        }
                    });
                }
            };

            handler.postDelayed(runnable,SPLASH_TIME);
        }
    }

}
