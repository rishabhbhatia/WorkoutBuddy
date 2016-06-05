package com.fitbud.workoutbuddy.interfaces;

import android.view.View;

/**
 * Created by Rishabh Bhatia on 6/5/2016.
 */
public interface RecyclerClickListener {

    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
