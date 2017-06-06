package com.tiantiannews.ui.activity;

import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.base.ui.widget.AppBar;
import com.tiantiannews.R;
import com.tiantiannews.base.BaseActivity;

import butterknife.BindView;

public class TestActivity extends BaseActivity {

    @BindView(R.id.app_bar)
    AppBar appBar;
    @BindView(R.id.sb_change_alpha)
    SeekBar sbChangeAlpha;
    @BindView(R.id.view_need_offset)
    RelativeLayout viewNeedOffset;

//    private int mAlpha = StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    public void initViews() {
//        sbChangeAlpha.setMax(255);
//        sbChangeAlpha.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                mAlpha = progress;
//
//                StatusBarUtil.setTranslucentForImageView(TestActivity.this, mAlpha, viewNeedOffset);
//
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//
//            }
//        });
//        sbChangeAlpha.setProgress(StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initVariables() {
        super.initVariables();
//        StatusBarUtil.setTranslucentForImageView(TestActivity.this, 0, viewNeedOffset);
    }

}
