package com.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewUtils {

    /**
     * touch前后view的颜色改变
     *
     * @param view
     * @param downColorId
     * @param upColorId
     */
    public static void addTouchColor(final View view, final int downColorId, final int upColorId, final Context context) {

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    view.setBackgroundColor(context.getResources().getColor(
                            downColorId));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    view.setBackgroundColor(context.getResources().getColor(
                            upColorId));
                }
                return false;
            }
        });

    }

    /**
     * touch前后view的drawable改变
     *
     * @param view
     * @param downDrawableId
     * @param upDrawableId
     * @param location       location为0时是left drawable,1时是top drawable,2时是right drawable,3时是bottom drawable,4为background
     */
    public static void addTouchDrawable(final View view, final int downDrawableId, final int upDrawableId, final int location, Context context) {
        final Drawable downDrawable = context.getResources().getDrawable(
                downDrawableId);
        downDrawable.setBounds(0, 0, downDrawable.getIntrinsicWidth(),
                downDrawable.getIntrinsicHeight());
        final Drawable upDrawable = context.getResources().getDrawable(
                upDrawableId);
        upDrawable.setBounds(0, 0, upDrawable.getIntrinsicWidth(),
                upDrawable.getIntrinsicHeight());
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (view instanceof TextView) {
                        switch (location) {
                            case 0:
                                ((TextView) view).setCompoundDrawables(downDrawable, ((TextView) view).getCompoundDrawables()[1],
                                        ((TextView) view).getCompoundDrawables()[2], ((TextView) view).getCompoundDrawables()[3]);
                                break;
                            case 1:
                                ((TextView) view).setCompoundDrawables(((TextView) view).getCompoundDrawables()[0], downDrawable,
                                        ((TextView) view).getCompoundDrawables()[2], ((TextView) view).getCompoundDrawables()[3]);
                                break;
                            case 2:
                                ((TextView) view).setCompoundDrawables(((TextView) view).getCompoundDrawables()[0],
                                        ((TextView) view).getCompoundDrawables()[1], downDrawable, ((TextView) view).getCompoundDrawables()[3]);
                                break;
                            case 3:
                                ((TextView) view).setCompoundDrawables(((TextView) view).getCompoundDrawables()[0],
                                        ((TextView) view).getCompoundDrawables()[1], ((TextView) view).getCompoundDrawables()[2], downDrawable);
                                break;
                            case 4:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    view.setBackground(downDrawable);
                                } else {
                                    view.setBackgroundDrawable(downDrawable);
                                }
                                break;
                        }


                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            view.setBackground(downDrawable);
                        } else {
                            view.setBackgroundDrawable(downDrawable);
                        }
                    }

                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                    if (view instanceof TextView) {
                        switch (location) {
                            case 0:
                                ((TextView) view).setCompoundDrawables(upDrawable, ((TextView) view).getCompoundDrawables()[1],
                                        ((TextView) view).getCompoundDrawables()[2], ((TextView) view).getCompoundDrawables()[3]);
                                break;
                            case 1:
                                ((TextView) view).setCompoundDrawables(((TextView) view).getCompoundDrawables()[0], upDrawable,
                                        ((TextView) view).getCompoundDrawables()[2], ((TextView) view).getCompoundDrawables()[3]);
                                break;
                            case 2:
                                ((TextView) view).setCompoundDrawables(((TextView) view).getCompoundDrawables()[0],
                                        ((TextView) view).getCompoundDrawables()[1], upDrawable, ((TextView) view).getCompoundDrawables()[3]);
                                break;
                            case 3:
                                ((TextView) view).setCompoundDrawables(((TextView) view).getCompoundDrawables()[0],
                                        ((TextView) view).getCompoundDrawables()[1], ((TextView) view).getCompoundDrawables()[2], upDrawable);
                                break;
                            case 4:
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                    view.setBackground(upDrawable);
                                } else {
                                    view.setBackgroundDrawable(upDrawable);
                                }
                                break;
                        }


                    } else {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            view.setBackground(upDrawable);
                        } else {
                            view.setBackgroundDrawable(upDrawable);
                        }
                    }
                }
                return false;
            }
        });

    }

    public static void addTouchImageResource(final ImageView imageView, final int downImageId, final int upImageId) {

        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    imageView.setImageResource(downImageId);

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    imageView.setImageResource(upImageId);
                }
                return false;
            }
        });
    }

    public static void setBackgroundPlatform(View view, int res, Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(context.getResources().getDrawable(res));
        } else {
            view.setBackgroundDrawable(context.getResources().getDrawable(res));
        }
    }

}
