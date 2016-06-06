package com.fitbud.workoutbuddy.ui.activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fitbud.workoutbuddy.interfaces.WorkoutBuddyInterface;
import com.fitbud.workoutbuddy.utils.Const;

public class WorkoutBuddyActivity extends AppCompatActivity implements WorkoutBuddyInterface {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void switchFragment(FragmentManager fm, Fragment fragment, String name,  String switchType)
    {
        try {
            if (fm != null) {
                boolean fragmentPopped = false;

                if (fm.getBackStackEntryCount() != 0) {
                    fragmentPopped = fm.popBackStackImmediate(name, 0);
                }

                Fragment fragmentExisting = fm.findFragmentByTag(name);
                boolean currentlyRunningFrag = false;

                if (fragmentExisting != null) {
                    currentlyRunningFrag = fragmentExisting.isVisible();
                }

                if (!fragmentPopped && !currentlyRunningFrag) {
                    FragmentTransaction ft = fm.beginTransaction();

                    if(switchType.equalsIgnoreCase(Const.FRAGMENT_SWITCH_ADD))
                    {
                        ft.add(android.R.id.content, fragment, name).addToBackStack(name);
                    }else
                    {
                        ft.replace(android.R.id.content, fragment, name).addToBackStack(name);
                    }
                    ft.commitAllowingStateLoss();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void removeFragment(FragmentManager fm, Fragment fragment) {
        try {
            fm.beginTransaction().remove(fragment).commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
