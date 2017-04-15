package com.tiantiannews.ui.widget.progressbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by hao on 2016/8/9.
 */
public class SpaceProgressView extends View {
    private Paint bigCirclePaint;
    private Paint smallCirclePaint;
    private RectF mectF;
    private Path mPath;
    private int maxColorNumber = 10;
    private float singlPoint = 9;
    private float lineWidth = 0.3f;
    private float progress = 0;
    private int currtJd = 0;
    private int setJd = 120;
    //圆环颜色
    private int[] doughnutColors = new int[]{Color.GREEN, Color.YELLOW, Color.RED};
    private float left = 50;
    private float top = 50;
    private float height = 100;
    private float length = 50;
    private float space = 10;

    public SpaceProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        bigCirclePaint = new Paint();
        bigCirclePaint.setAntiAlias(true);
        bigCirclePaint.setStrokeWidth(2);
        bigCirclePaint.setColor(Color.BLUE);

        smallCirclePaint = new Paint();
        smallCirclePaint.setAntiAlias(true);
        smallCirclePaint.setStrokeWidth(2);
        smallCirclePaint.setColor(Color.YELLOW);

        mectF = new RectF(50, 50, 350, 350);
        mPath = new Path();
        mPath.addArc(mectF, 0, 150);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawRect(50, 50, 850, 250, bigCirclePaint);
        float right = 0f;
        for (int i = 0; i < maxColorNumber; i++) {
            // 绘制间隔快
            right = left + length;
            canvas.drawRect(left, top, right, top + height, bigCirclePaint);
            left = (right + space);
        }
//        float degrees = (float) (360.0 / 120);
//        smallCirclePaint.setColor(Color.WHITE);
//        canvas.save();
//        canvas.translate(50, 200);
//        for (int i = 0; i < 120; i++) {
////            if (i % 10 == 0) {
////                canvas.drawLine(0, 0, 25, 0, smallCirclePaint);
////            } else {
////                canvas.drawLine(0, 0, 10, 0, smallCirclePaint);
////            }
//            canvas.drawLine(0, 0, 30, 0, smallCirclePaint);
//            canvas.rotate(degrees, 150, 0);
//        }
//        smallCirclePaint.setColor(Color.GRAY);
//        for (int i = 0; i < currtJd; i++) {
////            if (i % 10 == 0) {
////                canvas.drawLine(0, 0, 25, 0, smallCirclePaint);
////            } else {
////                canvas.drawLine(0, 0, 10, 0, smallCirclePaint);
////            }
//            canvas.drawLine(0, 0, 30, 0, smallCirclePaint);
//            canvas.rotate(degrees, 150, 0);
//        }
//        canvas.save();
//        canvas.translate(-50, -200);
//        smallCirclePaint.setColor(Color.BLUE);
////        canvas.drawArc(mectF, -180, 90, true, smallCirclePaint);
//        smallCirclePaint.setColor(Color.WHITE);
//        canvas.drawCircle(200, 200, 120, smallCirclePaint);
//        if (currtJd <= 120) {
//            try {
//                Thread.sleep(200);
//                currtJd++;
//                postInvalidate();
//            } catch (Exception e) {
//            }
//        }
    }
}
