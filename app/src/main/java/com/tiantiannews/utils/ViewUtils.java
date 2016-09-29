package com.tiantiannews.utils;

import android.view.MotionEvent;
import android.view.View;

import com.tiantiannews.base.BaseApplication;


public class ViewUtils {

    /**
     * touch前后view的颜色改变
     * @param view
     * @param downColor
     * @param upColor
     */
    public static void addTouchColor(final View view, final int downColor, final int upColor) {

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setBackgroundColor(BaseApplication.getInstance().getResources().getColor(
                            downColor));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    view.setBackgroundColor(BaseApplication.getInstance().getResources().getColor(
                            upColor));
                }
                return false;
            }
        });

    }
}
