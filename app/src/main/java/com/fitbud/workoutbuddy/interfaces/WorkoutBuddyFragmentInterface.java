package com.fitbud.workoutbuddy.interfaces;

import com.fitbud.workoutbuddy.ui.activities.WorkoutBuddyActivity;
import com.fitbud.workoutbuddy.ui.fragments.WorkoutBuddyFragment;

/**
 * Created by Rishabh Bhatia on 6/5/2016.
 */
public interface WorkoutBuddyFragmentInterface {

    String onBackPressed();

    void removeSelf(WorkoutBuddyActivity activity, WorkoutBuddyFragment fragment);

    void popFragmentBackStackImmediate(WorkoutBuddyActivity activity);
}
