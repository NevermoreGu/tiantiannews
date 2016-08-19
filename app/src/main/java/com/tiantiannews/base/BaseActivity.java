package com.tiantiannews.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import com.tiantiannews.ui.interf.BaseViewInterface;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity implements  BaseViewInterface {

    protected LayoutInflater mInflater;

    protected void onBeforeSetContentLayout() {}

    protected abstract int getLayoutId();

    protected void handlerIntent(){}

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBeforeSetContentLayout();
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        ButterKnife.bind(this);
        mContext = this;
        mInflater = getLayoutInflater();
        handlerIntent();
        initData();
        initView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handlerIntent();
    }
}
