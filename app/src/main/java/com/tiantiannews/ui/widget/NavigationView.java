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

public class NavigationView extends RelativeLayout {

    @BindView(R.id.img_navigation_left)
    ImageView navigationLeft;

    @BindView(R.id.img_navigation_right)
    ImageView navigationRight;

    @BindView(R.id.tv_navigation)
    TextView navigationTitle;

    @BindView(R.id.tv_navigation_right)
    TextView navigationTextRight;

    private Context mContext;

    private int navigationLeftId = 0;

    public NavigationView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.navigation_bar, this);
        ButterKnife.bind(this);
        setNavigationLeft();
    }

    public void setNavigationLeft() {
        if (navigationLeftId == 0) {
            navigationLeftId = R.drawable.back;
        }
        navigationLeft.setImageResource(navigationLeftId);
        navigationLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) mContext).finish();
            }
        });
    }

    public void setNavigationLeft(int leftId) {
        this.navigationLeftId = leftId;
        setNavigationLeft();
    }

    public void setOnClickListenerNavigationLeft(OnClickListener listener) {
        navigationLeft.setOnClickListener(listener);
    }

    public void setOnClickListenerNavigationRight(OnClickListener listener) {
        navigationLeft.setOnClickListener(listener);
    }

    public void setNavigationTitle(String title) {
        navigationTitle.setText(title);
    }

    public void setNavigationTitle(int titleId) {
        navigationTitle.setText(getResources().getString(titleId));
    }

    public void setNavigationTextRight(String title) {
        navigationTextRight.setText(title);
    }

    public void setNavigationTextRight(int titleId) {
        navigationTextRight.setText(getResources().getString(titleId));
    }

}
