package com.fitbud.workoutbuddy.interfaces;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

/**
 * Created by Rishabh Bhatia on 5/28/2016.
 */
public interface WorkoutBuddyInterface {

    void switchFragment(FragmentManager fm, Fragment fragment, String name,  String switchType);

    void removeFragment(FragmentManager fm, Fragment fragment);
}
