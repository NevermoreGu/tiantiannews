package com.qrcode;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

public class ScanBoxView extends View {

    private int mMoveStepDistance;
    private int mAnimDelayTime;

    private Rect mFramingRect;
    private float mScanLineTop;
    private float mScanLineLeft;
    private Paint mPaint;
    private TextPaint mTipPaint;

    /**
     * 除去扫描框，其余部分阴影颜色，默认值为#33FFFFFF
     */
    private int mMaskColor;
    /**
     * 扫描框边角线的颜色，默认值为@android:color/white
     */
    private int mCornerColor;
    /**
     * 扫描框边角线的长度，默认值为20dp
     */
    private int mCornerLength;
    /**
     * 扫描框边角线的宽度，默认值为3dp
     */
    private int mCornerSize;
    /**
     * 扫描框的宽度，默认值为200dp
     */
    private int mRectWidth;
    private int mRectHeight;
    /**
     * 条码扫样式描框的高度，默认值为140dp
     */
    private int mBarcodeRectHeight;
    /**
     * 扫描框距离toolbar底部的距离，默认值为90dp
     */
    private int mTopOffset;
    /**
     * 扫描线的宽度，默认值为1dp
     */
    private int mScanLineSize;
    /**
     * 扫描线的颜色，默认值为@android:color/white
     */
    private int mScanLineColor;
    /**
     * 扫描线距离上下或者左右边框的间距，默认值为0dp
     */
    private int mScanLineMargin;
    /**
     * 是否显示默认的图片扫描线，默认值为false
     */
    private boolean mIsShowDefaultScanLineDrawable;
    /**
     * 扫描线的图片资源，默认值为null
     */
    private Drawable mCustomScanLineDrawable;

    private Bitmap mScanLineBitmap;
    /**
     * 扫描边框的宽度，默认值为1dp
     */
    private int mBorderSize;
    /**
     * 扫描边框的颜色，默认值为@android:color/white
     */
    private int mBorderColor;
    /**
     * 扫描线从顶部移动到底部的动画时间，默认值为1000
     */
    private int mAnimTime;
    /**
     * 扫描框是否垂直居中，该属性为true时会忽略qrcv_topOffset属性，默认值为false
     */
    private boolean mIsCenterVertical;
    /**
     * Toolbar的高度，当有设置qrcv_isCenterVertical属性时，通过该属性来修正有Toolbar时导致扫描框垂直居中的偏差，默认值为0dp
     */
    private int mToolbarHeight;
    /**
     * 是否是扫条形码，默认值为false
     */
    private boolean mIsBarcode;
    /**
     * 扫描二维码时的提示文案，默认值为null
     */
    private String mQRCodeTipText;
    /**
     * 扫描条码时的提示文案，默认值为null
     */
    private String mBarCodeTipText;
    private String mTipText;
    /**
     * 提示文案字体大小，默认值为14sp
     */
    private int mTipTextSize;
    /**
     * 提示文案颜色，默认值为@android:color/white
     */
    private int mTipTextColor;
    /**
     * 提示文案是否在扫描框的底部，默认值为false
     */
    private boolean mIsTipTextBelowRect;
    /**
     * 提示文案与扫描框之间的间距，默认值为20dp
     */
    private int mTipTextMargin;
    /**
     * 是否把提示文案作为单行显示，默认值为false
     */
    private boolean mIsShowTipTextAsSingleLine;
    /**
     * 提示文案的背景色，默认值为#22000000
     */
    private int mTipBackgroundColor;
    /**
     * 是否显示提示文案的背景，默认值为false
     */
    private boolean mIsShowTipBackground;
    /**
     * 扫描线是否来回移动，默认值为true
     */
    private boolean mIsScanLineReverse;
    /**
     * 是否显示默认的网格图片扫描线，默认值为false
     */
    private boolean mIsShowDefaultGridScanLineDrawable;
    /**
     * 扫描线的网格图片资源，默认值为null
     */
    private Drawable mCustomGridScanLineDrawable;
    private Bitmap mGridScanLineBitmap;
    private float mGridScanLineBottom;
    private float mGridScanLineRight;

    private Bitmap mOriginQRCodeScanLineBitmap;
    private Bitmap mOriginBarCodeScanLineBitmap;
    private Bitmap mOriginQRCodeGridScanLineBitmap;
    private Bitmap mOriginBarCodeGridScanLineBitmap;


    private float mHalfCornerSize;
    private StaticLayout mTipTextSl;
    private int mTipBackgroundRadius;

    /**
     * 是否只识别扫描框区域的二维码，默认值为false
     */
    private boolean mIsOnlyDecodeScanBoxArea;

    public ScanBoxView(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mMaskColor = Color.parseColor("#33FFFFFF");

        mCornerColor = Color.WHITE;
        mCornerLength = BGAQRCodeUtil.dp2px(context, 20);
        mCornerSize = BGAQRCodeUtil.dp2px(context, 6);

        mScanLineSize = BGAQRCodeUtil.dp2px(context, 1);
        mScanLineColor = Color.WHITE;
        mScanLineMargin = 0;
        mIsShowDefaultScanLineDrawable = false;
        mCustomScanLineDrawable = null;
        mScanLineBitmap = null;

        mTopOffset = BGAQRCodeUtil.dp2px(context, 90);

        mRectWidth = BGAQRCodeUtil.dp2px(context, 200);
        mBarcodeRectHeight = BGAQRCodeUtil.dp2px(context, 140);
        mBorderSize = BGAQRCodeUtil.dp2px(context, 1);
        mBorderColor = Color.WHITE;
        mAnimTime = 1000;
        mIsCenterVertical = false;

        mToolbarHeight = 0;
        mIsBarcode = false;
        mMoveStepDistance = BGAQRCodeUtil.dp2px(context, 2);

        mTipText = null;
        mTipTextSize = BGAQRCodeUtil.sp2px(context, 14);
        mTipTextColor = Color.WHITE;
        mIsTipTextBelowRect = false;
        mTipTextMargin = BGAQRCodeUtil.dp2px(context, 20);
        mIsShowTipTextAsSingleLine = false;
        mTipBackgroundColor = Color.parseColor("#22000000");
        mIsShowTipBackground = false;
        mIsScanLineReverse = false;
        mIsShowDefaultGridScanLineDrawable = false;

        mTipPaint = new TextPaint();
        mTipPaint.setAntiAlias(true);

        mTipBackgroundRadius = BGAQRCodeUtil.dp2px(context, 4);

        mIsOnlyDecodeScanBoxArea = false;
    }

    public void initCustomAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.QRCodeView);
        final int count = typedArray.getIndexCount();
        for (int i = 0; i < count; i++) {
            initCustomAttr(typedArray.getIndex(i), typedArray);
        }
        typedArray.recycle();

        afterInitCustomAttrs();
    }

    private void initCustomAttr(int attr, TypedArray typedArray) {
        if (attr == R.styleable.QRCodeView_qrcv_topOffset) {
            mTopOffset = typedArray.getDimensionPixelSize(attr, mTopOffset);
        } else if (attr == R.styleable.QRCodeView_qrcv_cornerSize) {
            mCornerSize = typedArray.getDimensionPixelSize(attr, mCornerSize);
        } else if (attr == R.styleable.QRCodeView_qrcv_cornerLength) {
            mCornerLength = typedArray.getDimensionPixelSize(attr, mCornerLength);
        } else if (attr == R.styleable.QRCodeView_qrcv_scanLineSize) {
            mScanLineSize = typedArray.getDimensionPixelSize(attr, mScanLineSize);
        } else if (attr == R.styleable.QRCodeView_qrcv_rectWidth) {
            mRectWidth = typedArray.getDimensionPixelSize(attr, mRectWidth);
        } else if (attr == R.styleable.QRCodeView_qrcv_maskColor) {
            mMaskColor = typedArray.getColor(attr, mMaskColor);
        } else if (attr == R.styleable.QRCodeView_qrcv_cornerColor) {
            mCornerColor = typedArray.getColor(attr, mCornerColor);
        } else if (attr == R.styleable.QRCodeView_qrcv_scanLineColor) {
            mScanLineColor = typedArray.getColor(attr, mScanLineColor);
        } else if (attr == R.styleable.QRCodeView_qrcv_scanLineMargin) {
            mScanLineMargin = typedArray.getDimensionPixelSize(attr, mScanLineMargin);
        } else if (attr == R.styleable.QRCodeView_qrcv_isShowDefaultScanLineDrawable) {
            mIsShowDefaultScanLineDrawable = typedArray.getBoolean(attr, mIsShowDefaultScanLineDrawable);
        } else if (attr == R.styleable.QRCodeView_qrcv_customScanLineDrawable) {
            mCustomScanLineDrawable = typedArray.getDrawable(attr);
        } else if (attr == R.styleable.QRCodeView_qrcv_borderSize) {
            mBorderSize = typedArray.getDimensionPixelSize(attr, mBorderSize);
        } else if (attr == R.styleable.QRCodeView_qrcv_borderColor) {
            mBorderColor = typedArray.getColor(attr, mBorderColor);
        } else if (attr == R.styleable.QRCodeView_qrcv_animTime) {
            mAnimTime = typedArray.getInteger(attr, mAnimTime);
        } else if (attr == R.styleable.QRCodeView_qrcv_isCenterVertical) {
            mIsCenterVertical = typedArray.getBoolean(attr, mIsCenterVertical);
        } else if (attr == R.styleable.QRCodeView_qrcv_toolbarHeight) {
            mToolbarHeight = typedArray.getDimensionPixelSize(attr, mToolbarHeight);
        } else if (attr == R.styleable.QRCodeView_qrcv_barcodeRectHeight) {
            mBarcodeRectHeight = typedArray.getDimensionPixelSize(attr, mBarcodeRectHeight);
        } else if (attr == R.styleable.QRCodeView_qrcv_isBarcode) {
            mIsBarcode = typedArray.getBoolean(attr, mIsBarcode);
        } else if (attr == R.styleable.QRCodeView_qrcv_barCodeTipText) {
            mBarCodeTipText = typedArray.getString(attr);
        } else if (attr == R.styleable.QRCodeView_qrcv_qrCodeTipText) {
            mQRCodeTipText = typedArray.getString(attr);
        } else if (attr == R.styleable.QRCodeView_qrcv_tipTextSize) {
            mTipTextSize = typedArray.getDimensionPixelSize(attr, mTipTextSize);
        } else if (attr == R.styleable.QRCodeView_qrcv_tipTextColor) {
            mTipTextColor = typedArray.getColor(attr, mTipTextColor);
        } else if (attr == R.styleable.QRCodeView_qrcv_isTipTextBelowRect) {
            mIsTipTextBelowRect = typedArray.getBoolean(attr, mIsTipTextBelowRect);
        } else if (attr == R.styleable.QRCodeView_qrcv_tipTextMargin) {
            mTipTextMargin = typedArray.getDimensionPixelSize(attr, mTipTextMargin);
        } else if (attr == R.styleable.QRCodeView_qrcv_isShowTipTextAsSingleLine) {
            mIsShowTipTextAsSingleLine = typedArray.getBoolean(attr, mIsShowTipTextAsSingleLine);
        } else if (attr == R.styleable.QRCodeView_qrcv_isShowTipBackground) {
            mIsShowTipBackground = typedArray.getBoolean(attr, mIsShowTipBackground);
        } else if (attr == R.styleable.QRCodeView_qrcv_tipBackgroundColor) {
            mTipBackgroundColor = typedArray.getColor(attr, mTipBackgroundColor);
        } else if (attr == R.styleable.QRCodeView_qrcv_isScanLineReverse) {
            mIsScanLineReverse = typedArray.getBoolean(attr, mIsScanLineReverse);
        } else if (attr == R.styleable.QRCodeView_qrcv_isShowDefaultGridScanLineDrawable) {
            mIsShowDefaultGridScanLineDrawable = typedArray.getBoolean(attr, mIsShowDefaultGridScanLineDrawable);
        } else if (attr == R.styleable.QRCodeView_qrcv_customGridScanLineDrawable) {
            mCustomGridScanLineDrawable = typedArray.getDrawable(attr);
        } else if (attr == R.styleable.QRCodeView_qrcv_isOnlyDecodeScanBoxArea) {
            mIsOnlyDecodeScanBoxArea = typedArray.getBoolean(attr, mIsOnlyDecodeScanBoxArea);
        }
    }

    private void afterInitCustomAttrs() {
        if (mCustomGridScanLineDrawable != null) {
            mOriginQRCodeGridScanLineBitmap = ((BitmapDrawable) mCustomGridScanLineDrawable).getBitmap();
        }
        if (mOriginQRCodeGridScanLineBitmap == null) {
            mOriginQRCodeGridScanLineBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qrcode_default_grid_scan_line);
            mOriginQRCodeGridScanLineBitmap = BGAQRCodeUtil.makeTintBitmap(mOriginQRCodeGridScanLineBitmap, mScanLineColor);
        }
        mOriginBarCodeGridScanLineBitmap = BGAQRCodeUtil.adjustPhotoRotation(mOriginQRCodeGridScanLineBitmap, 90);
        mOriginBarCodeGridScanLineBitmap = BGAQRCodeUtil.adjustPhotoRotation(mOriginBarCodeGridScanLineBitmap, 90);
        mOriginBarCodeGridScanLineBitmap = BGAQRCodeUtil.adjustPhotoRotation(mOriginBarCodeGridScanLineBitmap, 90);


        if (mCustomScanLineDrawable != null) {
            mOriginQRCodeScanLineBitmap = ((BitmapDrawable) mCustomScanLineDrawable).getBitmap();
        }
        if (mOriginQRCodeScanLineBitmap == null) {
            mOriginQRCodeScanLineBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.qrcode_default_scan_line);
            mOriginQRCodeScanLineBitmap = BGAQRCodeUtil.makeTintBitmap(mOriginQRCodeScanLineBitmap, mScanLineColor);
        }
        mOriginBarCodeScanLineBitmap = BGAQRCodeUtil.adjustPhotoRotation(mOriginQRCodeScanLineBitmap, 90);

        mTopOffset += mToolbarHeight;
        mHalfCornerSize = 1.0f * mCornerSize / 2;

        mTipPaint.setTextSize(mTipTextSize);
        mTipPaint.setColor(mTipTextColor);

        setIsBarcode(mIsBarcode);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (mFramingRect == null) {
            return;
        }

        // 画遮罩层
        drawMask(canvas);

        // 画边框线
        drawBorderLine(canvas);

        // 画四个直角的线
        drawCornerLine(canvas);

        // 画扫描线
        drawScanLine(canvas);

        // 画提示文本
        drawTipText(canvas);

        // 移动扫描线的位置
        moveScanLine();

    }

    /**
     * 画遮罩层
     *
     * @param canvas
     */
    private void drawMask(Canvas canvas) {
        int width = canvas.getWidth();
        int height = canvas.getHeight();

        if (mMaskColor != Color.TRANSPARENT) {
            mPaint.setStyle(Paint.Style.FILL);
            mPaint.setColor(mMaskColor);
            canvas.drawRect(0, 0, width, mFramingRect.top, mPaint);
            canvas.drawRect(0, mFramingRect.top, mFramingRect.left, mFramingRect.bottom + 1, mPaint);
            canvas.drawRect(mFramingRect.right + 1, mFramingRect.top, width, mFramingRect.bottom + 1, mPaint);
            canvas.drawRect(0, mFramingRect.bottom + 1, width, height, mPaint);
        }
    }

    /**
     * 画边框线
     *
     * @param canvas
     */
    private void drawBorderLine(Canvas canvas) {
        if (mBorderSize > 0) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mBorderColor);
            mPaint.setStrokeWidth(mBorderSize);
            canvas.drawRect(mFramingRect, mPaint);
        }
    }

    /**
     * 画四个直角的线
     *
     * @param canvas
     */
    private void drawCornerLine(Canvas canvas) {
        if (mHalfCornerSize > 0) {
            mPaint.setStyle(Paint.Style.STROKE);
            mPaint.setColor(mCornerColor);
            mPaint.setStrokeWidth(mCornerSize);
            canvas.drawLine(mFramingRect.left - mHalfCornerSize, mFramingRect.top, mFramingRect.left - mHalfCornerSize + mCornerLength, mFramingRect.top, mPaint);
            canvas.drawLine(mFramingRect.left, mFramingRect.top - mHalfCornerSize, mFramingRect.left, mFramingRect.top - mHalfCornerSize + mCornerLength, mPaint);
            canvas.drawLine(mFramingRect.right + mHalfCornerSize, mFramingRect.top, mFramingRect.right + mHalfCornerSize - mCornerLength, mFramingRect.top, mPaint);
            canvas.drawLine(mFramingRect.right, mFramingRect.top - mHalfCornerSize, mFramingRect.right, mFramingRect.top - mHalfCornerSize + mCornerLength, mPaint);

            canvas.drawLine(mFramingRect.left - mHalfCornerSize, mFramingRect.bottom, mFramingRect.left - mHalfCornerSize + mCornerLength, mFramingRect.bottom, mPaint);
            canvas.drawLine(mFramingRect.left, mFramingRect.bottom + mHalfCornerSize, mFramingRect.left, mFramingRect.bottom + mHalfCornerSize - mCornerLength, mPaint);
            canvas.drawLine(mFramingRect.right + mHalfCornerSize, mFramingRect.bottom, mFramingRect.right + mHalfCornerSize - mCornerLength, mFramingRect.bottom, mPaint);
            canvas.drawLine(mFramingRect.right, mFramingRect.bottom + mHalfCornerSize, mFramingRect.right, mFramingRect.bottom + mHalfCornerSize - mCornerLength, mPaint);
        }
    }

    /**
     * 画扫描线
     *
     * @param canvas
     */
    private void drawScanLine(Canvas canvas) {
        if (mIsBarcode) {
            if (mGridScanLineBitmap != null) {
                RectF dstGridRectF = new RectF(mFramingRect.left + mHalfCornerSize + 0.5f, mFramingRect.top + mHalfCornerSize + mScanLineMargin, mGridScanLineRight, mFramingRect.bottom - mHalfCornerSize - mScanLineMargin);

                Rect srcGridRect = new Rect((int) (mGridScanLineBitmap.getWidth() - dstGridRectF.width()), 0, mGridScanLineBitmap.getWidth(), mGridScanLineBitmap.getHeight());

                if (srcGridRect.left < 0) {
                    srcGridRect.left = 0;
                    dstGridRectF.left = dstGridRectF.right - srcGridRect.width();
                }

                canvas.drawBitmap(mGridScanLineBitmap, srcGridRect, dstGridRectF, mPaint);
            } else if (mScanLineBitmap != null) {
                RectF lineRect = new RectF(mScanLineLeft, mFramingRect.top + mHalfCornerSize + mScanLineMargin, mScanLineLeft + mScanLineBitmap.getWidth(), mFramingRect.bottom - mHalfCornerSize - mScanLineMargin);
                canvas.drawBitmap(mScanLineBitmap, null, lineRect, mPaint);
            } else {
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(mScanLineColor);
                canvas.drawRect(mScanLineLeft, mFramingRect.top + mHalfCornerSize + mScanLineMargin, mScanLineLeft + mScanLineSize, mFramingRect.bottom - mHalfCornerSize - mScanLineMargin, mPaint);
            }
        } else {
            if (mGridScanLineBitmap != null) {
                RectF dstGridRectF = new RectF(mFramingRect.left + mHalfCornerSize + mScanLineMargin, mFramingRect.top + mHalfCornerSize + 0.5f, mFramingRect.right - mHalfCornerSize - mScanLineMargin, mGridScanLineBottom);

                Rect srcRect = new Rect(0, (int) (mGridScanLineBitmap.getHeight() - dstGridRectF.height()), mGridScanLineBitmap.getWidth(), mGridScanLineBitmap.getHeight());

                if (srcRect.top < 0) {
                    srcRect.top = 0;
                    dstGridRectF.top = dstGridRectF.bottom - srcRect.height();
                }

                canvas.drawBitmap(mGridScanLineBitmap, srcRect, dstGridRectF, mPaint);
            } else if (mScanLineBitmap != null) {
                RectF lineRect = new RectF(mFramingRect.left + mHalfCornerSize + mScanLineMargin, mScanLineTop, mFramingRect.right - mHalfCornerSize - mScanLineMargin, mScanLineTop + mScanLineBitmap.getHeight());
                canvas.drawBitmap(mScanLineBitmap, null, lineRect, mPaint);
            } else {
                mPaint.setStyle(Paint.Style.FILL);
                mPaint.setColor(mScanLineColor);
                canvas.drawRect(mFramingRect.left + mHalfCornerSize + mScanLineMargin, mScanLineTop, mFramingRect.right - mHalfCornerSize - mScanLineMargin, mScanLineTop + mScanLineSize, mPaint);
            }
        }
    }

    /**
     * 画提示文本
     *
     * @param canvas
     */
    private void drawTipText(Canvas canvas) {
        if (TextUtils.isEmpty(mTipText) || mTipTextSl == null) {
            return;
        }

        if (mIsTipTextBelowRect) {
            if (mIsShowTipBackground) {
                mPaint.setColor(mTipBackgroundColor);
                mPaint.setStyle(Paint.Style.FILL);
                if (mIsShowTipTextAsSingleLine) {
                    Rect tipRect = new Rect();
                    mTipPaint.getTextBounds(mTipText, 0, mTipText.length(), tipRect);
                    float left = (canvas.getWidth() - tipRect.width()) / 2 - mTipBackgroundRadius;
                    canvas.drawRoundRect(new RectF(left, mFramingRect.bottom + mTipTextMargin - mTipBackgroundRadius, left + tipRect.width() + 2 * mTipBackgroundRadius, mFramingRect.bottom + mTipTextMargin + mTipTextSl.getHeight() + mTipBackgroundRadius), mTipBackgroundRadius, mTipBackgroundRadius, mPaint);
                } else {
                    canvas.drawRoundRect(new RectF(mFramingRect.left, mFramingRect.bottom + mTipTextMargin - mTipBackgroundRadius, mFramingRect.right, mFramingRect.bottom + mTipTextMargin + mTipTextSl.getHeight() + mTipBackgroundRadius), mTipBackgroundRadius, mTipBackgroundRadius, mPaint);
                }
            }

            canvas.save();
            if (mIsShowTipTextAsSingleLine) {
                canvas.translate(0, mFramingRect.bottom + mTipTextMargin);
            } else {
                canvas.translate(mFramingRect.left + mTipBackgroundRadius, mFramingRect.bottom + mTipTextMargin);
            }
            mTipTextSl.draw(canvas);
            canvas.restore();
        } else {
            if (mIsShowTipBackground) {
                mPaint.setColor(mTipBackgroundColor);
                mPaint.setStyle(Paint.Style.FILL);

                if (mIsShowTipTextAsSingleLine) {
                    Rect tipRect = new Rect();
                    mTipPaint.getTextBounds(mTipText, 0, mTipText.length(), tipRect);
                    float left = (canvas.getWidth() - tipRect.width()) / 2 - mTipBackgroundRadius;
                    canvas.drawRoundRect(new RectF(left, mFramingRect.top - mTipTextMargin - mTipTextSl.getHeight() - mTipBackgroundRadius, left + tipRect.width() + 2 * mTipBackgroundRadius, mFramingRect.top - mTipTextMargin + mTipBackgroundRadius), mTipBackgroundRadius, mTipBackgroundRadius, mPaint);
                } else {
                    canvas.drawRoundRect(new RectF(mFramingRect.left, mFramingRect.top - mTipTextMargin - mTipTextSl.getHeight() - mTipBackgroundRadius, mFramingRect.right, mFramingRect.top - mTipTextMargin + mTipBackgroundRadius), mTipBackgroundRadius, mTipBackgroundRadius, mPaint);
                }
            }

            canvas.save();
            if (mIsShowTipTextAsSingleLine) {
                canvas.translate(0, mFramingRect.top - mTipTextMargin - mTipTextSl.getHeight());
            } else {
                canvas.translate(mFramingRect.left + mTipBackgroundRadius, mFramingRect.top - mTipTextMargin - mTipTextSl.getHeight());
            }
            mTipTextSl.draw(canvas);
            canvas.restore();
        }
    }

    /**
     * 移动扫描线的位置
     */
    private void moveScanLine() {
        if (mIsBarcode) {
            if (mGridScanLineBitmap == null) {
                // 处理非网格扫描图片的情况
                mScanLineLeft += mMoveStepDistance;
                int scanLineSize = mScanLineSize;
                if (mScanLineBitmap != null) {
                    scanLineSize = mScanLineBitmap.getWidth();
                }

                if (mIsScanLineReverse) {
                    if (mScanLineLeft + scanLineSize > mFramingRect.right - mHalfCornerSize || mScanLineLeft < mFramingRect.left + mHalfCornerSize) {
                        mMoveStepDistance = -mMoveStepDistance;
                    }
                } else {
                    if (mScanLineLeft + scanLineSize > mFramingRect.right - mHalfCornerSize) {
                        mScanLineLeft = mFramingRect.left + mHalfCornerSize + 0.5f;
                    }
                }
            } else {
                // 处理网格扫描图片的情况
                mGridScanLineRight += mMoveStepDistance;
                if (mGridScanLineRight > mFramingRect.right - mHalfCornerSize) {
                    mGridScanLineRight = mFramingRect.left + mHalfCornerSize + 0.5f;
                }
            }
        } else {
            if (mGridScanLineBitmap == null) {
                // 处理非网格扫描图片的情况
                mScanLineTop += mMoveStepDistance;
                int scanLineSize = mScanLineSize;
                if (mScanLineBitmap != null) {
                    scanLineSize = mScanLineBitmap.getHeight();
                }

                if (mIsScanLineReverse) {
                    if (mScanLineTop + scanLineSize > mFramingRect.bottom - mHalfCornerSize || mScanLineTop < mFramingRect.top + mHalfCornerSize) {
                        mMoveStepDistance = -mMoveStepDistance;
                    }
                } else {
                    if (mScanLineTop + scanLineSize > mFramingRect.bottom - mHalfCornerSize) {
                        mScanLineTop = mFramingRect.top + mHalfCornerSize + 0.5f;
                    }
                }
            } else {
                // 处理网格扫描图片的情况
                mGridScanLineBottom += mMoveStepDistance;
                if (mGridScanLineBottom > mFramingRect.bottom - mHalfCornerSize) {
                    mGridScanLineBottom = mFramingRect.top + mHalfCornerSize + 0.5f;
                }
            }

        }
        postInvalidateDelayed(mAnimDelayTime, mFramingRect.left, mFramingRect.top, mFramingRect.right, mFramingRect.bottom);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        calFramingRect();
    }

    private void calFramingRect() {
        Point screenResolution = BGAQRCodeUtil.getScreenResolution(getContext());
        int leftOffset = (screenResolution.x - mRectWidth) / 2;
        mFramingRect = new Rect(leftOffset, mTopOffset, leftOffset + mRectWidth, mTopOffset + mRectHeight);

        if (mIsBarcode) {
            mGridScanLineRight = mScanLineLeft = mFramingRect.left + mHalfCornerSize + 0.5f;
        } else {
            mGridScanLineBottom = mScanLineTop = mFramingRect.top + mHalfCornerSize + 0.5f;
        }
    }

    public Rect getScanBoxAreaRect(int previewHeight) {
        if (mIsOnlyDecodeScanBoxArea) {
            Rect rect = new Rect(mFramingRect);
            rect.top = (int) (1.0f * rect.top * previewHeight / getMeasuredHeight());
            rect.bottom = (int) (1.0f * rect.bottom * previewHeight / getMeasuredHeight());
            return rect;
        } else {
            return null;
        }
    }

    public void setIsBarcode(boolean isBarcode) {
        mIsBarcode = isBarcode;

        if (mCustomGridScanLineDrawable != null || mIsShowDefaultGridScanLineDrawable) {
            if (mIsBarcode) {
                mGridScanLineBitmap = mOriginBarCodeGridScanLineBitmap;
            } else {
                mGridScanLineBitmap = mOriginQRCodeGridScanLineBitmap;
            }
        } else if (mCustomScanLineDrawable != null || mIsShowDefaultScanLineDrawable) {
            if (mIsBarcode) {
                mScanLineBitmap = mOriginBarCodeScanLineBitmap;
            } else {
                mScanLineBitmap = mOriginQRCodeScanLineBitmap;
            }
        }

        if (mIsBarcode) {
            mTipText = mBarCodeTipText;
            mRectHeight = mBarcodeRectHeight;
            mAnimDelayTime = (int) ((1.0f * mAnimTime * mMoveStepDistance) / mRectWidth);
        } else {
            mTipText = mQRCodeTipText;
            mRectHeight = mRectWidth;
            mAnimDelayTime = (int) ((1.0f * mAnimTime * mMoveStepDistance) / mRectHeight);
        }

        if (!TextUtils.isEmpty(mTipText)) {
            if (mIsShowTipTextAsSingleLine) {
                mTipTextSl = new StaticLayout(mTipText, mTipPaint, BGAQRCodeUtil.getScreenResolution(getContext()).x, Layout.Alignment.ALIGN_CENTER, 1.0f, 0, true);
            } else {
                mTipTextSl = new StaticLayout(mTipText, mTipPaint, mRectWidth - 2 * mTipBackgroundRadius, Layout.Alignment.ALIGN_CENTER, 1.0f, 0, true);
            }
        }

        if (mIsCenterVertical) {
            int screenHeight = BGAQRCodeUtil.getScreenResolution(getContext()).y;
            if (mToolbarHeight == 0) {
                mTopOffset = (screenHeight - mRectHeight) / 2;
            } else {
                mTopOffset = (screenHeight - mRectHeight) / 2 + mToolbarHeight / 2;
            }
        }

        calFramingRect();

        postInvalidate();
    }

    public boolean getIsBarcode() {
        return mIsBarcode;
    }
}