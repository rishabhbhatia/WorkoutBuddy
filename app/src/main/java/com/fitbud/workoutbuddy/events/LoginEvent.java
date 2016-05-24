package com.fitbud.workoutbuddy.events;

import android.content.Context;

import com.fitbud.workoutbuddy.utils.Const;
import com.fitbud.workoutbuddy.utils.WorkoutBuddyUtils;

/**
 * Created by Rishabh Bhatia on 5/24/2016.
 */
public class LoginEvent {

    private String username;
    private String password;

    public LoginEvent(Context context, String username, String password)
    {
        this.username = username;
        this.password = password;

        WorkoutBuddyUtils.getSharedPrefEditor(context).putString(Const.USER_LOGIN_USERNAME, username).apply();
        WorkoutBuddyUtils.getSharedPrefEditor(context).putBoolean(Const.USER_LOGIN_STATUS, true).apply();
    }


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
