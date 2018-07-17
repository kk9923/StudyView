package com.kx.studyview.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

/**
 * Created by admin on 2018/7/17.
 * 炫酷的提交按钮
 */
public class AnimationButton extends View {
    /**
     * 背景颜色
     */
    private int bg_color = 0xffbc7d53;
    private Paint mPaint,textPaint;
    private  int mWidth, mHeight ;
    private RectF mRectf;
    /**
     * 圆角矩形  x,y 的值
     */
    private  int circleRadius ;
    /**
     * 按钮文字字符串
     */
    private String buttonString = "确认完成";
    private Rect mTextRect;
    /**
     * 动画执行时间
     */
    private int duration = 1000;
    /**
     * view向上移动距离
     */
    private int move_distance = 300;
    /**
     * 当由半圆矩形变为圆形时  半圆矩形的中心到圆形中心的距离
     */
    private  int defaultTwoCircleDistance ;
    private  int twoCircleDistance ;
    private AnimatorSet mAnimatorSet;

    public AnimationButton(Context context) {
        this(context,null);
    }

    public AnimationButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public AnimationButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setAntiAlias(true);
        mPaint.setColor(bg_color);
        mRectf = new RectF();


        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(40);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);

        mTextRect = new Rect();
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                reset();
            }
        });
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        defaultTwoCircleDistance = (mWidth - mHeight )/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        drawRect(canvas);
        drawText(canvas);
    }

    /**
     * 绘制文字
     */
    private void drawText(Canvas canvas) {
        mTextRect.left = 0;
        mTextRect.top = 0;
        mTextRect.right = mWidth;
        mTextRect.bottom = mHeight;
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (mTextRect.bottom + mTextRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        //文字绘制到整个布局的中心位置
        canvas.drawText(buttonString, mTextRect.centerX(), baseline, textPaint);
    }

    /**
     * 绘制 初始状态下的矩形
     */
    private void drawRect(Canvas canvas) {
        mRectf .left = twoCircleDistance;
        mRectf. top=0;
        mRectf.right = mWidth - twoCircleDistance;
        mRectf.bottom = mHeight;
        canvas.drawRoundRect(mRectf,circleRadius,circleRadius,mPaint);
    }
    public void startAnimation(){
       mAnimatorSet.play(set_rect_to_circle_animation()).with(set_rect_to_angle_animation()).before(move_to_up_animation());
        mAnimatorSet.start();
    }

    /**
     * 矩形变为圆角矩形的动画
     * 将circleRadius 从0变为   高度的一半，则矩形成为两边为半圆的圆角矩形
     */
    private ValueAnimator set_rect_to_angle_animation() {
        ValueAnimator rectToAngleAnimation  = ValueAnimator.ofInt(0,mHeight/2);
        rectToAngleAnimation.setDuration(duration);
        rectToAngleAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                 circleRadius = (int) animation.getAnimatedValue();
                 invalidate();
            }
        });
        return rectToAngleAnimation;
    }

    /**
     * 半圆矩形变为圆形的动画
     */
    private ValueAnimator set_rect_to_circle_animation() {
        ValueAnimator rectToCircleAnimation  = ValueAnimator.ofInt(0,defaultTwoCircleDistance);
        rectToCircleAnimation.setDuration(duration);
        rectToCircleAnimation.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                 twoCircleDistance = (int) animation.getAnimatedValue();
                int alpha = 255 - (twoCircleDistance * 255) / defaultTwoCircleDistance;
                textPaint.setAlpha(alpha);
                invalidate();
            }
        });
        return rectToCircleAnimation;
    }
    /**
     * 圆形向上位移动画
     */
    private ValueAnimator move_to_up_animation() {
        ObjectAnimator objectAnimator  = ObjectAnimator.ofFloat(this, "translationY", 0, 0 - move_distance);
        objectAnimator.setDuration(duration);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        return objectAnimator;
    }
    /**
     * 动画还原
     */
    public void reset() {
       // startDrawOk = false;
        circleRadius = 0;
        twoCircleDistance = 0;
        textPaint.setAlpha(255);
        setTranslationY(getTranslationY() + move_distance);
        invalidate();
    }

}

