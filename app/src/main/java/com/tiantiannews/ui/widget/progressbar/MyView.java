package com.tiantiannews.ui.widget.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by hao on 2016/8/5.
 */
public class MyView extends View {
    //主画笔画外圈圆以及内圈全
    private Paint mPaint;
    private Path mPath;
    private Path circiePath;
    private RectF rectF;
    private int height;
    private int with;
    private Paint textPaint;
    private RectF rectFB;
    private RectF rectFN;
    private Path mPathN;
    //总共进度
    private int allJD = 100;
    //当前进度
    private int cuttrenJD = 0;

    private int jdRadius;
    private float circleX;
    private float circley;
    private boolean isFirst = true;

    private int bigRadius = 250;


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(5);
        mPaint.setStyle(Paint.Style.FILL);
        mPath = new Path();
        mPathN = new Path();
        circiePath = new Path();
        rectF = new RectF(40, 100, 540, 600);
        rectFN = new RectF(40, 108, 540, 600);
        circiePath.addCircle(290, 350, 250, Path.Direction.CCW);
        mPathN = new Path();
        mPathN.addArc(rectFN, 0, -180);
        mPath.addArc(rectF, 0, -180);
        textPaint = new Paint();
        textPaint.setColor(Color.YELLOW);
        textPaint.setAntiAlias(true);
        jdRadius = 30;
        circleX = 40;
        circley = 350;

        setBackgroundColor(Color.BLUE);

    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.YELLOW);
        canvas.drawPath(circiePath, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawPath(mPath, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawPath(mPathN, mPaint);
        float x = textPaint.measureText(cuttrenJD + "");
        textPaint.setTextSize(80);
        textPaint.setColor(Color.YELLOW);
        canvas.drawText(cuttrenJD + "%", (420 - x) / 2, 280, textPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawCircle(circleX, circley, jdRadius, mPaint);
        textPaint.setColor(Color.BLUE);
        textPaint.setTextSize(20);
        float xCircle = textPaint.measureText(cuttrenJD + "");
        textPaint.setTextSize(25);
        canvas.drawText(cuttrenJD + "%", circleX - xCircle, circley + 8, textPaint);
        try {
            Thread.sleep(500);
            if (cuttrenJD < allJD) {
                cuttrenJD ++;
//                circleX = (float) (bigRadius * Math.cos(cuttrenJD * (100d / 180d)));
                circleX = (float) (290 -   bigRadius * Math.cos((cuttrenJD * (180d / 100d)) * 2 * Math.PI / 360));
                circley = (float) (350 - bigRadius * Math.sin((cuttrenJD * (180d / 100d)) * 2 * Math.PI / 360));
                Log.e("cuttrenJD", cuttrenJD + "==circleX==" + circleX + "==circley==" + circley);
                postInvalidate();
            }
        } catch (Exception e) {
        }
    }


}
