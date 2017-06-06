package com.tiantiannews.base;

import android.app.Activity;
import android.arch.lifecycle.LifecycleFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.base.ui.interf.BaseViewInterface;
import com.base.ui.widget.AppBar;
import com.tiantiannews.R;

import butterknife.ButterKnife;

public abstract class BaseFragment extends LifecycleFragment implements BaseViewInterface {

    protected Activity mContext;

    protected View viewRoot;

    protected AppBar appBar;

    protected LayoutInflater mInflater;

    protected void onBeforeSetContentLayout() {
    }

    protected abstract int getLayoutId();

    protected View inflateView(int resId) {
        return mInflater.inflate(resId, null);
    }

    /**
     * 处理从上一个界面接收的intent,或singleTop,singleTask,singleInstance onNewIntent中intent
     */
    protected void handlerIntent() {
    }

    protected void initAppBar() {
        appBar = (AppBar) viewRoot.findViewById(R.id.app_bar);
        appBar.setAppBarLeftDefault();
        appBar.setOnClickListenerAppBarLeft(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getActivity().onBackPressed();
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        mInflater = mContext.getLayoutInflater();
        handlerIntent();
        initVariables();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewRoot  = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, viewRoot);
        initViews();
        return viewRoot;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData();
    }

}
