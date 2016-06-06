package com.fitbud.workoutbuddy.ui.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.percent.PercentRelativeLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fitbud.workoutbuddy.R;
import com.fitbud.workoutbuddy.events.LoginEvent;
import com.fitbud.workoutbuddy.ui.fragments.ForgotPassFragment;
import com.fitbud.workoutbuddy.ui.fragments.WorkoutBuddyFragment;
import com.fitbud.workoutbuddy.utils.Const;
import com.fitbud.workoutbuddy.utils.WorkoutBuddyUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rishabh Bhatia on 5/22/2016.
 */
public class LoginActivity extends WorkoutBuddyActivity {

    @BindView(R.id.bt_login)
    Button btLogin;
    @BindView(R.id.et_login_pass)
    EditText etPass;
    @BindView(R.id.et_login_username)
    EditText etUsername;
    @BindView(R.id.ll_login_username_holder)
    LinearLayout linearUsernameHolder;
    @BindView(R.id.ll_login_pass_holder)
    LinearLayout linearPasswordHolder;
    @BindView(R.id.iv_login_username_error)
    ImageView ivUsernameError;
    @BindView(R.id.iv_login_password_error)
    ImageView ivPasswordError;
    @BindView(R.id.prl_login_main_holder)
    PercentRelativeLayout mainRelative;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        measureLoginButton();

        initializeToolbar();

        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
    }

    private void measureLoginButton()
    {
        try {
            linearUsernameHolder.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            etUsername.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

            linearPasswordHolder.setMinimumWidth(linearUsernameHolder.getMeasuredWidth());
            linearPasswordHolder.setMinimumHeight(linearUsernameHolder.getMeasuredHeight());

            etPass.setWidth(etUsername.getMeasuredWidth());
            etPass.setHeight(etUsername.getMeasuredHeight());

            btLogin.setWidth(linearUsernameHolder.getMeasuredWidth());
            btLogin.setHeight(linearUsernameHolder.getMeasuredHeight());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeToolbar() {
        try {
            TextView tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_label);
            ImageView ivToolbarBack = (ImageView) toolbar.findViewById(R.id.iv_toolbar_back);

            //replace back button icon with home button icon

            tvToolbarTitle.setText(getString(R.string.app_name));

            ivToolbarBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    WorkoutBuddyUtils.closeApp(LoginActivity.this);
                }
            });
        }catch (Exception e) {e.printStackTrace();}
    }

    @OnClick(R.id.bt_login)
    public void onLogin()
    {
        if(TextUtils.isEmpty(etUsername.getText()))
        {
            ivUsernameError.startAnimation(shake);
        }else if(TextUtils.isEmpty(etPass.getText()))
        {
            ivPasswordError.startAnimation(shake);
        }else
        {
            new loginTask().execute();
        }
    }

    private class loginTask extends AsyncTask<Void, Void, Void>
    {
        private boolean isOnline = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            WorkoutBuddyUtils.showSimpleProgressDialog(LoginActivity.this);
        }

        @Override
        protected Void doInBackground(Void... voids) {

            if(WorkoutBuddyUtils.isNetworkConnected(LoginActivity.this))
            {
                isOnline = true;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            WorkoutBuddyUtils.removeSimpleProgressDialog();

            if(isOnline)
            {
                EventBus bus = EventBus.getDefault();
                LoginEvent loginEvent = new LoginEvent(LoginActivity.this, etUsername.getText().toString(),
                        etPass.getText().toString());
                bus.post(loginEvent);
            }else
            {
                WorkoutBuddyUtils.showSnackbar(mainRelative, getResources().getString(R.string.no_internet),
                        Snackbar.LENGTH_LONG);
            }
        }
    }

    @Subscribe
    public void onLoginEvent(LoginEvent loginEvent)
    {
        Intent intent = new Intent(LoginActivity.this, MainLandingActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    public void onBackPressed() {

        String fragTag = notifyFragments();
        if(fragTag.equals(""))
        {
            WorkoutBuddyUtils.closeApp(LoginActivity.this);
        }
    }

    private String notifyFragments() {

        String activeFragTag = "";

        List<Fragment> fragments = getSupportFragmentManager().getFragments();

        if(fragments == null|| fragments.size() ==0) return activeFragTag;

        for(Fragment f : fragments)
        {
            if(f != null && f instanceof WorkoutBuddyFragment)
            {
                String fragTag = ((WorkoutBuddyFragment) f).onBackPressed();

                if(!fragTag.equals(""))
                {
                    activeFragTag = fragTag;
                    return fragTag;
                }
            }
        }

        return activeFragTag;
    }

    @OnClick(R.id.tv_forgot_pass)
    public void loadForgotPassFrag() {
        ForgotPassFragment forgotPassFragment = ForgotPassFragment.newInstance();
        switchFragment(getSupportFragmentManager(), forgotPassFragment, getResources().
                getString(R.string.fragment_forgot_pass_tag), Const.FRAGMENT_SWITCH_REPLACE);
    }

}
