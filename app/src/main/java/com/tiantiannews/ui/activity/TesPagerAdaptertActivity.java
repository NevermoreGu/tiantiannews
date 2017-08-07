package com.tiantiannews.ui.activity;

import android.support.v4.view.ViewPager;

import com.base.ui.widget.pageIndicator.CircularPageIndicator;
import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;
import com.tiantiannews.ui.adapter.TestFragmentPagerStateAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TesPagerAdaptertActivity extends BaseActivity {


    @BindView(R.id.viewpager)
    ViewPager viewpager;
    @BindView(R.id.cpi)
    CircularPageIndicator cpi;

    private TestFragmentPagerStateAdapter mAdapter;
    private List<String> imageInfo;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pager_adapter_test;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void loadData() {
        setImageInfo();
        mAdapter = new TestFragmentPagerStateAdapter(getSupportFragmentManager());
        viewpager.setAdapter(mAdapter);
        cpi.setViewPager(viewpager);
    }

    @Override
    public void initVariables() {
        super.initVariables();
        imageInfo = new ArrayList<>();
    }

    private void setImageInfo() {
        for (int i = 0; i < 4; i++) {
            imageInfo.add("http://tupian.enterdesk.com/2013/mxy/12/11/4/4.jpg");
        }
    }
}
