package com.tiantiannews.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiantiannews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AppBar extends RelativeLayout {

    @BindView(R.id.img_app_left)
    ImageView imgAppBarLeft;

    @BindView(R.id.img_app_right)
    ImageView imgAppBarRight;

    @BindView(R.id.tv_app)
    TextView tvAppBarTitle;

    @BindView(R.id.tv_app_right)
    TextView tVAppBarRight;

    private Context mContext;

    private int AppBarLeftDrawableId = 0;

    public AppBar(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public AppBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public AppBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.app_bar, this);
        ButterKnife.bind(this);
        setAppBarLeftDefault();
    }

    public TextView getAppBarTitle() {
        return tvAppBarTitle;
    }

    public ImageView getAppBarImageLeft() {
        return imgAppBarLeft;
    }

    public ImageView getAppBarImageRight() {
        return imgAppBarRight;
    }

    /**
     * 默认左边的图标与点击事件
     */
    public void setAppBarLeftDefault() {
        if (AppBarLeftDrawableId == 0) {
            AppBarLeftDrawableId = R.drawable.back;
        }
        imgAppBarLeft.setImageResource(AppBarLeftDrawableId);
        imgAppBarLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) mContext).finish();
            }
        });
    }

    /**
     * 设置左边的图标
     *
     * @param leftDrawableId
     */
    public void setAppBarLeftDrawable(int leftDrawableId) {
        this.AppBarLeftDrawableId = leftDrawableId;
        setAppBarLeftDefault();
    }

    /**
     * 设置左边的点击事件
     *
     * @param listener
     */
    public void setOnClickListenerAppBarLeft(OnClickListener listener) {
        imgAppBarLeft.setOnClickListener(listener);
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setAppBarTitle(String title) {
        tvAppBarTitle.setText(title);
    }

    public void setAppBarTitle(int titleId) {
        tvAppBarTitle.setText(getResources().getString(titleId));
    }

    /**
     * 设置右边的文字
     *
     * @param title
     */
    public void setAppBarRightText(String title) {
        tVAppBarRight.setText(title);
    }

    /**
     * 设置右边的图标
     *
     * @param rightDrawableId
     */
    public void setAppBarRightDrawable(int rightDrawableId) {
        imgAppBarRight.setImageResource(rightDrawableId);
    }

    /**
     * 设置右边的点击事件
     *
     * @param listener
     */
    public void setOnClickListenerAppBarRight(OnClickListener listener) {
        imgAppBarLeft.setOnClickListener(listener);
    }
}