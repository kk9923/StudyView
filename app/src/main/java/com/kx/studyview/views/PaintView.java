package com.kx.studyview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by admin on 2018/7/24.
 */
public class PaintView  extends View{

    private Paint mPaint;
    private Path mPath;

    public PaintView(Context context) {
        this(context,null);
    }
    public PaintView(Context context, @Nullable AttributeSet attrs) {
        this(context,attrs,0);
    }
    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.GREEN);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
        mPath = new Path();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.BLACK);
        mPath.moveTo(100,600);
        mPath.lineTo(300,100);
        mPath.lineTo(500,900);
        mPaint.setColor(Color.GREEN);
        mPaint.setPathEffect(new CornerPathEffect(100));
        canvas.drawPath(mPath,mPaint);
        mPaint.setColor(Color.RED);

        /**
         * public DashPathEffect(float intervals[], float phase)
         * intervals[]：表示组成虚线的各个线段的长度；整条虚线就是由intervals[]中这些基本线段循环组成的。
         * 比如，我们定义new float[] {20,10}；那这个虚线段就是由两段线段组成的，第一个可见的线段长为20，每二个线段不可见，长度为10；
         * phase：开始绘制的偏移值
         */
//使用DashPathEffect画线段
        mPaint.setPathEffect(new DashPathEffect(new float[]{20,10,100,100},0));
        canvas.translate(0,100);
        canvas.drawPath(mPath,mPaint);

   //画同一条线段,偏移值为15
        mPaint.setPathEffect(new DashPathEffect(new float[]{20,10,50,100},dx));
        mPaint.setColor(Color.YELLOW);
        canvas.translate(0,100);
        canvas.drawPath(mPath,mPaint);

    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }
    private ValueAnimator mAnimator;
    private  int dx ;

    /**
     * 不断改变第三条线的偏移值  ，动画长度值设为一个虚线的一个基线的长度
     */
    private void startAnimation(){
        mAnimator = ValueAnimator.ofInt(0,20+10+50+100);
        mAnimator.setDuration(2000);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                dx = (int)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mAnimator!=null){
            mAnimator.cancel();
            mAnimator=null ;
        }
    }
}
