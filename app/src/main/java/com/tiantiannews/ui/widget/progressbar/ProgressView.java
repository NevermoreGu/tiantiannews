package com.tiantiannews.ui.widget.progressbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by hao on 2016/8/11.
 */
public class ProgressView extends View {
    private Paint mPaint;
    private Paint circlePaint;
    private int[] colors = new int[]{ Color.parseColor("#25e197"), Color.parseColor("#fe4838")};
    private float currtenJD = 1f;
    private float alljD = 100f;

    public ProgressView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStrokeWidth(20);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeCap(Paint.Cap.ROUND);


        circlePaint = new Paint();
        circlePaint.setStrokeWidth(10);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LinearGradient lg = new LinearGradient(50, 50,getWidth() - 50, 70, Color.parseColor("#25e197"), Color.parseColor("#fe4838"), Shader.TileMode.MIRROR);

        mPaint.setShader(lg);
        int colorss = ColorUtils.getCurrentColor((currtenJD / alljD), colors);
        Log.e("currtenJDcolorss", colorss + "=colorss=");
        canvas.drawLine(50, 50, getWidth() - 50, 50, mPaint);
        circlePaint.setColor(colorss);
        canvas.drawCircle(50 + (getWidth() - 50) / 100 * currtenJD, 50, 20, circlePaint);
        try {
            Thread.sleep(200);
            if (currtenJD < alljD) {
                currtenJD++;
                postInvalidate();
                Log.e("currtenJD", currtenJD + "==");
            }else {
            }
        } catch (Exception e) {
        }
    }
    private Bitmap saveViewBitmap(View view) {
// get current view bitmap
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache(true);
        Bitmap bitmap = view.getDrawingCache(true);

        Bitmap bmp = duplicateBitmap(bitmap);
        if (bitmap != null && !bitmap.isRecycled()) { bitmap.recycle(); bitmap = null; }
        // clear the cache
        view.setDrawingCacheEnabled(false);
        return bmp;
    }
    public static Bitmap duplicateBitmap(Bitmap bmpSrc)
    {
        if (null == bmpSrc)
        { return null; }

        int bmpSrcWidth = bmpSrc.getWidth();
        int bmpSrcHeight = bmpSrc.getHeight();

        Bitmap bmpDest = Bitmap.createBitmap(bmpSrcWidth, bmpSrcHeight, Bitmap.Config.ARGB_8888); if (null != bmpDest) { Canvas canvas = new Canvas(bmpDest); final Rect rect = new Rect(0, 0, bmpSrcWidth, bmpSrcHeight);

        canvas.drawBitmap(bmpSrc, rect, rect, null); }

        return bmpDest;
    }

}
