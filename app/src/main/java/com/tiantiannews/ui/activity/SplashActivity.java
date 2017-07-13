package com.tiantiannews.ui.activity;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.utils.ActivityUtils;

public class SplashActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initVariables() {

    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {

//        Intent intentReceiver = new Intent(mContext, ActionBootCompletedBroadcastReceiver.class);
//        intentReceiver.setAction(ActionBootCompletedBroadcastReceiver.ACTION_NOTIFICATION);
//        sendBroadcast(intentReceiver);

        ActivityUtils.openActivity(mContext, LoginActivity.class);
        finish();
    }
}
