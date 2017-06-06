package com.tiantiannews.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;

import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by guqian on 2017/5/9.
 */

public class TestHandlerActivity extends BaseActivity {

    private static final int MSG_AUTO_SCROLL = 0;
    private static final int DEFAULT_INTERNAL_IM_MILLIS = 5000;
    @BindView(R.id.btn_cancel)
    Button btnCancel;

    private H mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_cancel)
    public void onViewClicked() {
        Log.d("TestHandlerActivity", "test handler message remove");
        mHandler.removeMessages(MSG_AUTO_SCROLL);
    }

    private class H extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_AUTO_SCROLL:
                    Log.d("TestHandlerActivity", "test handler receiver message");
                    sendEmptyMessageDelayed(MSG_AUTO_SCROLL, DEFAULT_INTERNAL_IM_MILLIS);
                    break;
                default:
                    super.handleMessage(msg);
                    break;
            }
        }
    }

    @Override
    public void initViews() {
    }

    @Override
    public void loadData() {
        mHandler = new H();
        mHandler.sendEmptyMessageDelayed(MSG_AUTO_SCROLL, DEFAULT_INTERNAL_IM_MILLIS);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test_handler;
    }
}
