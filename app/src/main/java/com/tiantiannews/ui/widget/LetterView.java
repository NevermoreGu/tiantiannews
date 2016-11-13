package com.tiantiannews.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.tiantiannews.R;

public class LetterView extends View {

    private Context mContext;

    OnTouchingLetterChangedListener onTouchingLetterChangedListener;

    String[] letters = {"#", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
            "Y", "Z"};
    boolean showBkg = false; // 是否显示背景
    int choose = -1;// 当前选中首字母的位置
    Paint paint = new Paint();

    private TextView mTextDialog;

    public void setTextView(TextView mTextDialog) {
        this.mTextDialog = mTextDialog;
    }

    public LetterView(Context context) {
        super(context);
        this.mContext = context;
    }

    public LetterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    public LetterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.mContext = context;
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (showBkg) {
            canvas.drawColor(Color.parseColor("#50545454"));
        }
        int height = getHeight();
        int width = getWidth();
        int singleHeight = height / letters.length;
        for (int i = 0; i < letters.length; i++) {
            paint.setColor(mContext.getResources().getColor(R.color.colorPrimary));
            paint.setTextSize(36);
            //设置粗体
//            Typeface font = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD);
//            paint.setTypeface(font);
            paint.setAntiAlias(true);
            if (i == choose) {
                paint.setColor(mContext.getResources().getColor(R.color.colorPrimary));
                paint.setFakeBoldText(true);
            }
            float xPos = width / 2 - paint.measureText(letters[i]) / 2;
            float yPos = singleHeight * i + singleHeight;
            canvas.drawText(letters[i], xPos, yPos, paint);
            paint.reset();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        final float y = event.getY();
        final int oldChoose = choose;
        final OnTouchingLetterChangedListener listener = onTouchingLetterChangedListener;
        final int c = (int) (y / getHeight() * letters.length);
        switch (action) {
            // case MotionEvent.ACTION_DOWN:
            // showBkg = true;
            // if (oldChoose != c && listener != null) {
            // if (c > 0 && c < b.length) {
            // listener.onTouchingLetterChanged(b[c]);
            // choose = c;
            // invalidate();
            // }
            // }
            //
            // break;
            // case MotionEvent.ACTION_MOVE:
            // if (oldChoose != c && listener != null) {
            // if (c > 0 && c < b.length) {
            // listener.onTouchingLetterChanged(b[c]);
            // choose = c;
            // invalidate();
            // }
            // }
            // break;
            case MotionEvent.ACTION_UP:
                showBkg = false;
                choose = -1;
                invalidate();
                if (mTextDialog != null) {
                    mTextDialog.setVisibility(View.INVISIBLE);
                }
                break;
            default:
                showBkg = true;
                if (oldChoose != c && listener != null) {
                    if (c > 0 && c < letters.length) {
                        listener.onTouchingLetterChanged(letters[c]);
                        if (mTextDialog != null) {
                            mTextDialog.setText(letters[c]);
                            mTextDialog.setVisibility(View.VISIBLE);
                        }
                        choose = c;
                        invalidate();
                    }
                }
                break;
        }

        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    public void setOnTouchingLetterChangedListener(
            OnTouchingLetterChangedListener onTouchingLetterChangedListener) {
        this.onTouchingLetterChangedListener = onTouchingLetterChangedListener;
    }

    public interface OnTouchingLetterChangedListener {
        void onTouchingLetterChanged(String s);
    }
}
