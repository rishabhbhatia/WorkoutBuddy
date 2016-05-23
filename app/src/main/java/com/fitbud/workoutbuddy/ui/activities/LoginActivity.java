package com.fitbud.workoutbuddy.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fitbud.workoutbuddy.R;

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

    private Animation shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        measureLoginButton();

        shake = AnimationUtils.loadAnimation(this, R.anim.shake);
    }

    private void measureLoginButton()
    {
        linearUsernameHolder.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        etUsername.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);

        linearPasswordHolder.setMinimumWidth(linearUsernameHolder.getMeasuredWidth());
        linearPasswordHolder.setMinimumHeight(linearUsernameHolder.getMeasuredHeight());

        etPass.setWidth(etUsername.getMeasuredWidth());
        etPass.setHeight(etUsername.getMeasuredHeight());

        btLogin.setWidth(linearUsernameHolder.getMeasuredWidth());
        btLogin.setHeight(linearUsernameHolder.getMeasuredHeight());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
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
            Intent intent = new Intent(LoginActivity.this, MainLandingActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_from_bottom, R.anim.slide_out_to_bottom);
        }
    }
}
