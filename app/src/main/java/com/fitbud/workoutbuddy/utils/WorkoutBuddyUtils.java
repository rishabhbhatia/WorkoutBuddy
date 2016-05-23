package com.fitbud.workoutbuddy.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.fitbud.workoutbuddy.R;
import com.fitbud.workoutbuddy.ui.activities.LoginActivity;

import java.io.IOException;
import java.net.InetAddress;
import java.util.List;

/**
 * Created by Rishabh Bhatia on 5/22/2016.
 */
public class WorkoutBuddyUtils {

    private static ProgressDialog mProgressDialog;
    private static AlertDialog messageDialog;

    /**
     * This method converts dp unit to equivalent pixels, depending on device density.
     *
     * @param dp A value in dp (density independent pixels) unit. Which we need to convert into pixels
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent px equivalent to dp depending on device density
     */
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    /**
     * This method converts device specific pixels to density independent pixels.
     *
     * @param px A value in px (pixels) unit. Which we need to convert into db
     * @param context Context to get resources and device specific display metrics
     * @return A float value to represent dp equivalent to px value
     */
    public static float convertPixelsToDp(float px, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static void hideSoftKeyboard(Context context,View view)
    {
        try {
            InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }catch (NullPointerException e){}
    }

    public static SharedPreferences.Editor getSharedPrefEditor(Context context)
    {
        return getSharedPrefs(context).edit();
    }

    public static SharedPreferences getSharedPrefs(Context context)
    {
        return context.getSharedPreferences(Const.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    public static boolean isNetworkConnectedSimple(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    //CALL THIS IN BACKGROUND THREAD OR ASYNC
    public static boolean isNetworkConnected(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && isOnline();
    }

    public static boolean isInternetAvailable()
    {
        try {
            InetAddress ipAddr = InetAddress.getByName("google.com");

            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isOnline() {

        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);

        } catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    public static void showToast(Context context, String s,int duration)
    {
        Toast.makeText(context, s, duration).show();
    }

    public static void showSnackbar(View view,String message,int duration)
    {
        Snackbar.make(view, message, duration).show();
    }

    public static void showSimpleProgressDialog(Context context)
    {
        removeSimpleAlert();
        removeSimpleProgressDialog();

        showSimpleProgressDialog(context, null, context.getResources().
                getString(R.string.simple_progress_dialog_loading), true);
    }

    public static void showSimpleProgressDialog(Context context, String s, String s1, boolean cancelable)
    {
        try
        {
            if (mProgressDialog == null)
            {
                mProgressDialog = ProgressDialog.show(context, s, s1);
                mProgressDialog.setCancelable(cancelable);
            }
            if (!mProgressDialog.isShowing())
            {
                mProgressDialog.show();
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void showSimpleAlert(Context context,String title,String msg)
    {
        removeSimpleAlert();
        removeSimpleProgressDialog();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        messageDialog = dialogBuilder.create();
        messageDialog.show();
    }

    public static void removeSimpleAlert()
    {
        if(messageDialog != null && messageDialog.isShowing())
        {
            messageDialog.dismiss();
            messageDialog = null;
        }
    }

    public static void removeSimpleProgressDialog()
    {
        try
        {
            if (mProgressDialog != null && mProgressDialog.isShowing())
            {
                mProgressDialog.dismiss();
                mProgressDialog = null;
            }
            return;
        }
        catch (IllegalArgumentException illegalargumentexception)
        {
            illegalargumentexception.printStackTrace();
            return;
        }
        catch (RuntimeException runtimeexception)
        {
            runtimeexception.printStackTrace();
            return;
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }
    }

    public static boolean isAppIsInBackground(Context context)
    {
        boolean isInBackground = true;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT_WATCH) {
            List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo processInfo : runningProcesses) {
                if (processInfo.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                    for (String activeProcess : processInfo.pkgList) {
                        if (activeProcess.equals(context.getPackageName())) {
                            isInBackground = false;
                        }
                    }
                }
            }
        } else {
            String packageName;

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                List<ActivityManager.AppTask>  tasks = am.getAppTasks();
                packageName = tasks.get(0).getTaskInfo().baseIntent.getComponent().getPackageName();

            }else {
                List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
                ComponentName componentInfo = taskInfo.get(0).topActivity;
                packageName = componentInfo.getPackageName();
            }

            if (packageName.equals(context.getPackageName())) {
                isInBackground = false;
            }
        }

        return isInBackground;
    }

    public static void showLogoutPopup(final Activity context, String title, String msg, String buttonText,
                                       Boolean showNegativeButton)
    {
        removeSimpleAlert();
        removeSimpleProgressDialog();

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        dialogBuilder.setTitle(title);
        dialogBuilder.setMessage(msg);
        dialogBuilder.setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getSharedPrefEditor(context).putBoolean(Const.USER_LOGIN_STATUS,false).apply();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
                context.overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
                context.finish();
            }
        });

        if(showNegativeButton)
        {
            dialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
        }

        messageDialog = dialogBuilder.create();
        messageDialog.show();
    }
}
