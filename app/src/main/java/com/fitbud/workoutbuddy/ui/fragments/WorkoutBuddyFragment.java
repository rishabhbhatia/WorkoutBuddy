package com.fitbud.workoutbuddy.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fitbud.workoutbuddy.interfaces.WorkoutBuddyFragmentInterface;
import com.fitbud.workoutbuddy.ui.activities.WorkoutBuddyActivity;

/**
 * Created by Rishabh Bhatia on 5/28/2016.
 */
public class WorkoutBuddyFragment extends Fragment implements WorkoutBuddyFragmentInterface {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public String onBackPressed() {
        return "";
    }

    @Override
    public void removeSelf(WorkoutBuddyActivity activity, WorkoutBuddyFragment fragment) {
        try {
            activity.removeFragment(activity.getSupportFragmentManager(), fragment);
        }catch (Exception e) {e.printStackTrace();}
    }

    @Override
    public void popFragmentBackStackImmediate(WorkoutBuddyActivity activity) {
        try {
            activity.getSupportFragmentManager().popBackStackImmediate();
        }catch (Exception e) {e.printStackTrace();}
    }
}
