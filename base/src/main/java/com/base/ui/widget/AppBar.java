package com.base.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.R;

public class AppBar extends RelativeLayout {

    private ImageView imgAppBarLeft;

    private ImageView imgAppBarRight;

    private TextView tvAppBarTitle;

    private TextView tVAppBarRight;

    private RelativeLayout rlAppBarRight;

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
        imgAppBarLeft = (ImageView) findViewById(R.id.img_app_left);
        imgAppBarRight = (ImageView) findViewById(R.id.img_app_right);
        tvAppBarTitle = (TextView) findViewById(R.id.tv_app);
        rlAppBarRight = (RelativeLayout) findViewById(R.id.rl_app_right);
        tVAppBarRight = (TextView) findViewById(R.id.tv_app_right);
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

    public TextView getAppBarTvRight() {
        return tVAppBarRight;
    }

    /**
     * 默认左边的图标与点击事件
     */
    public void setAppBarLeftDefault() {
        if (AppBarLeftDrawableId == 0) {
            AppBarLeftDrawableId = R.drawable.back;
        }
        imgAppBarLeft.setImageResource(AppBarLeftDrawableId);
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

    public void setAppBarTitleAndColor(String title, int colorId) {
        tvAppBarTitle.setText(title);
        tvAppBarTitle.setTextColor(getResources().getColor(colorId));
    }

    /**
     * 设置右边的文字
     *
     * @param title
     */
    public void setAppBarRightText(String title) {
        tVAppBarRight.setText(title);
    }

    public void setAppBarRightTextAndColor(String title, int colorId) {
        tVAppBarRight.setText(title);
        tVAppBarRight.setTextColor(getResources().getColor(colorId));
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
        rlAppBarRight.setOnClickListener(listener);
    }
}
