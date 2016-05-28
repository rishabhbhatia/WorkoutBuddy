package com.fitbud.workoutbuddy.ui.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.percent.PercentRelativeLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import com.fitbud.workoutbuddy.R;
import com.fitbud.workoutbuddy.ui.activities.WorkoutBuddyActivity;
import com.fitbud.workoutbuddy.utils.WorkoutBuddyUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rishabh Bhatia on 5/28/2016.
 */
public class ForgotPassFragment extends WorkoutBuddyFragment {

    @BindView(R.id.prl_forgot_pass_main_holder)
    PercentRelativeLayout mainRelative;
    @BindView(R.id.ll_forgot_pass_input_holder)
    LinearLayout linearInputHolder;
    @BindView(R.id.et_forgot_pass_username)
    EditText etUsername;
    @BindView(R.id.bt_forgot_pass_send)
    Button btSend;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    public static ForgotPassFragment newInstance() {
        return new ForgotPassFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_forgot_pass, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        try {
            super.onViewCreated(view, savedInstanceState);
            ButterKnife.bind(view);

            initializeToolbar();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializeToolbar() {
        TextView tvToolbarTitle = (TextView) toolbar.findViewById(R.id.tv_toolbar_label);
        ImageView ivToolbarBack = (ImageView) toolbar.findViewById(R.id.iv_toolbar_back);

        tvToolbarTitle.setText(getString(R.string.app_name));

        ivToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((WorkoutBuddyActivity)getActivity()).removeFragment(getActivity().getSupportFragmentManager(),
                        ForgotPassFragment.this);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.bt_forgot_pass_send)
    public void forgotPassSendClicked() {
        try {

            if (etUsername.getText() == null || etUsername.getText().toString().equals("")) {
                WorkoutBuddyUtils.showSnackbar(mainRelative, getResources().getString(R.string.forgot_pass_empty_rider_id),
                        Snackbar.LENGTH_LONG);
            } else {
                new forgotPassAction().execute();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class forgotPassAction extends AsyncTask<Void,Void,Void> {

        private boolean isOnline = false;

        @Override
        protected void onPreExecute() {

            try {

                if (getActivity() == null || getActivity().isFinishing() || !ForgotPassFragment.this.isVisible())
                    return;

                super.onPreExecute();
                WorkoutBuddyUtils.showSimpleProgressDialog(getActivity());

            }catch (Exception e) {
                e.printStackTrace();
            }
        }


        @Override
        protected Void doInBackground(Void... voids) {

            try {

                if (getActivity() == null || getActivity().isFinishing() || !ForgotPassFragment.this.isVisible())
                    return null;

                if (WorkoutBuddyUtils.isNetworkConnected(getActivity())) {
                    isOnline = true;
                }
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {

            try {

                if (getActivity() == null || getActivity().isFinishing() || !ForgotPassFragment.this.isVisible())
                    return;

                super.onPostExecute(aVoid);
                if (isOnline) {
                    //send reset pass request
                } else {
                    WorkoutBuddyUtils.showSnackbar(mainRelative, getString(R.string.no_internet), Snackbar.LENGTH_LONG);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}