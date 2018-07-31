package com.kx.studyview.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.AccelerateInterpolator;

/**
 * Created by admin on 2018/7/25.
 */
public class RadialGradientView extends android.support.v7.widget.AppCompatTextView {

    Paint mPaint ;
    int width ,height ;
    private int mCenterX;
    private int mCenterY;
    // 默认的水波纹半径
    private  int DEFAULT_CIRCLE = 30;
    private int MAX_CIRCLE;
    private ObjectAnimator mValueAnimator;

    public RadialGradientView(Context context) {
        this(context,null);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RadialGradientView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;

        MAX_CIRCLE = width;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

      //  mPaint.setShader(mRadialGradient);
        canvas.drawCircle(mCenterX,mCenterY,mCurRadius,mPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                //  记录手指按下的位置，作为圆心
                if ( mCenterX != (int) event.getX() || mCenterY!= (int) event.getY()) {
                    mCenterX = (int) event.getX();
                    mCenterY = (int) event.getY();
                    setRadius(DEFAULT_CIRCLE);
                }
                break;
            case MotionEvent.ACTION_UP :
                if (mValueAnimator !=null && mValueAnimator.isRunning()){
                    mValueAnimator.cancel();
                }
                if (mValueAnimator ==null ) {
                     mValueAnimator = ObjectAnimator.ofInt(this,"radius",DEFAULT_CIRCLE,MAX_CIRCLE);
                    mValueAnimator.setDuration(1000);
                    mValueAnimator.setInterpolator(new AccelerateInterpolator());
                }

                mValueAnimator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        setRadius(0);
                    }

                    @Override
                    public void onAnimationStart(Animator animation) {

                    }
                });
                mValueAnimator.start();
                break;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            return true;
        }
        return super.onTouchEvent(event);
    }
    //表示当前渐变半径
    private int mCurRadius = 0;
   private   RadialGradient  mRadialGradient ;
    public void setRadius(final int radius) {
        mCurRadius = radius;
        if (mCurRadius > 0) {
            mRadialGradient = new RadialGradient(mCenterX, mCenterY, mCurRadius, 0x00FFFFFF, 0xFF59ccf5, Shader.TileMode.REPEAT);
            mPaint.setShader(mRadialGradient);
        }
        postInvalidate();
    }
}
