package com.fitbud.workoutbuddy.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.fitbud.workoutbuddy.R;
import com.fitbud.workoutbuddy.utils.WorkoutBuddyUtils;
import com.malinskiy.superrecyclerview.SuperRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rishabh Bhatia on 5/22/2016.
 */
public class MainLandingActivity extends WorkoutBuddyActivity {

    @BindView(R.id.rv_landing_clients)
    SuperRecyclerView rvClients;
    @BindView(R.id.rv_landing_recently_viewed)
    SuperRecyclerView rvRecentlyViewed;
    @BindView(R.id.ll_landing_new_workout_footer)
    LinearLayout linearNewWorkoutFooter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_landing);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.ll_landing_new_workout_footer)
    public void loadNewWorkoutScreen() {

    }

    @Override
    public void onBackPressed() {
        WorkoutBuddyUtils.closeApp(MainLandingActivity.this);
    }
}
