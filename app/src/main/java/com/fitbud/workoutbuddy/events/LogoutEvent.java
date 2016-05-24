package com.fitbud.workoutbuddy.events;

import android.content.Context;

import com.fitbud.workoutbuddy.utils.Const;
import com.fitbud.workoutbuddy.utils.WorkoutBuddyUtils;

/**
 * Created by Rishabh Bhatia on 5/24/2016.
 */
public class LogoutEvent {

    public LogoutEvent(Context context)
    {
        WorkoutBuddyUtils.getSharedPrefEditor(context).putBoolean(Const.USER_LOGIN_STATUS, false).apply();
    }

}
