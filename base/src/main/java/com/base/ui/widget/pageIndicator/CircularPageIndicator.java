package com.base.ui.widget.pageIndicator;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

import com.base.R;

/**
 * Created by guqian on 2017/7/26.
 */

public class CircularPageIndicator extends View implements PageIndicator {

    private final int DEFAULT_STROKE_COLOR = 0;

    private Context mContext;
    private Paint mPaint;
    private final int DEFAULT_RADIUS = 20;
    private final int DEFAULT_PADDING = 30;
    private int mBetweenSpace;
    private int mCount;
    private int mWidth;
    private int mHeight;
    private int mStartX;
    private int mStartY;


    public CircularPageIndicator(Context context) {
        super(context);
    }

    public CircularPageIndicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircularPageIndicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircularPageIndicator);
        a.getColor(R.styleable.CircularPageIndicator_stroke_color, DEFAULT_STROKE_COLOR);
        a.recycle();

        mBetweenSpace = DEFAULT_RADIUS * 2 + DEFAULT_PADDING;
        this.mContext = context;
        mPaint = new Paint();
        mPaint.setStrokeWidth(10);
        mPaint.setColor(context.getResources().getColor(R.color.white));


    }

    private void initCircularPageIndicator() {

    }

    @Override
    public void setViewPager(ViewPager view) {
        setViewPager(view, 0);
    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {
        if (view == null) {
            return;
        }
        PagerAdapter pagerAdapter = view.getAdapter();
        if (pagerAdapter.getCount() == 0) {
            return;
        }
        mCount = pagerAdapter.getCount();
        invalidate();
    }

    @Override
    public void setCurrentItem(int item) {

    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {

    }

    @Override
    public void notifyDataSetChanged() {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (mCount == 0) {
            return;
        }
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = getPaddingBottom();
        int minWidth = mBetweenSpace * (mCount - 1) + DEFAULT_RADIUS * 2;
        int minHeight = DEFAULT_RADIUS * 2;

        switch (widthMode) {
            case MeasureSpec.AT_MOST:
                mWidth = minWidth + paddingLeft + paddingRight;
                break;
            case MeasureSpec.EXACTLY:
                mWidth = widthSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                mWidth = widthSize;
                break;
        }

        switch (heightMode) {
            case MeasureSpec.AT_MOST:
                mHeight = minHeight + paddingTop + paddingBottom;
                break;
            case MeasureSpec.EXACTLY:
                mHeight = heightSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                mHeight = heightSize;
                break;
        }
        mStartX = paddingLeft + DEFAULT_RADIUS;
        mStartY = (int) (mHeight / 2);
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mCount == 0) {
            return;
        }
        for (int i = 0; i < mCount; i++) {
            canvas.drawCircle(mStartX + i * mBetweenSpace, mStartY, DEFAULT_RADIUS, mPaint);
        }
    }
}
