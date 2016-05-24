package com.fitbud.workoutbuddy.ui.activities;

import android.content.Intent;
import android.os.Bundle;

import com.fitbud.workoutbuddy.R;

/**
 * Created by Rishabh Bhatia on 5/22/2016.
 */
public class MainLandingActivity extends WorkoutBuddyActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
