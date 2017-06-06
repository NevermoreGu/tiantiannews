package com.tiantiannews.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tiantiannews.R;

public class TestStatusBarActivity extends AppCompatActivity {

    private View mViewNeedOffset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_status_bar);
        mViewNeedOffset = findViewById(R.id.view_need_offset);
//        StatusBarUtil.setTranslucentForImageView(this,0, mViewNeedOffset);
    }
}
