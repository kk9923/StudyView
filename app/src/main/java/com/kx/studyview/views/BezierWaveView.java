package com.kx.studyview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.kx.studyview.R;


/**
 * Created by admin  on 2018/7/9.
 * 自定义水波纹效果,通过 贝塞尔 函数实现
 */
public class BezierWaveView extends View {
    private Paint mBezierPaint, mBezierPaint2;
    private Path mBezierPath = new Path();
    private Path mBezierPath2 = new Path();
    //波纹的长度
    private int waveWidth;
    //波纹的 y值 的初始值
    private int originY ;
    private int mWidth,mHeight;
    private ValueAnimator mAnimator;

    public BezierWaveView(Context context) {
        this(context,null);
    }

    public BezierWaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public BezierWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBezierPaint.setAntiAlias(true);
        mBezierPaint.setStyle(Paint.Style.FILL);
        mBezierPaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        mBezierPaint.setAlpha(50);

        mBezierPaint2 = new Paint();
        mBezierPaint2.setAntiAlias(true);
        mBezierPaint2.setStyle(Paint.Style.FILL);
        mBezierPaint2.setColor(ContextCompat.getColor(context, R.color.colorAccent));
       // mBezierPaint2.setAlpha(50);
        waveWidth = 400;
        originY  = 80;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }
    Path clipPath = new Path();
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mBezierPath.reset();
        mBezierPath2.reset();
        clipPath.addCircle(getWidth()/2,getHeight()/2,getHeight()/2, Path.Direction.CCW);
        canvas.clipPath(clipPath);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getHeight()/2,mBezierPaint);
       //  向下封闭
     //  mBezierPath.moveTo(0,100);
//        mBezierPath.lineTo(0,getHeight()/2);
//        mBezierPath.quadTo(getWidth()/4,0,getWidth()/2,getHeight()/2);
//        mBezierPath.quadTo(getWidth()*3/4,getHeight(),getWidth(),getHeight()/2);
        //  向下封闭


//        //  向上封闭
//      //  mBezierPath.moveTo(0,getHeight());
//        mBezierPath.lineTo(0,getHeight()/2);
//        mBezierPath.quadTo(getWidth()/4,0,getWidth()/2,getHeight()/2);
//        mBezierPath.quadTo(getWidth()*3/4,getHeight(),getWidth(),getHeight()/2);
//        //  向上封闭
//        mBezierPath.lineTo(getWidth(),0);
        // 将起始点移动到屏幕外一个波长的位置
        mBezierPath.moveTo(-waveWidth + dx,originY );

        for (int i = -waveWidth; i <= mWidth + waveWidth ; i+= waveWidth) {
                mBezierPath.rQuadTo(waveWidth/4 , -50,waveWidth/2,0);
                mBezierPath.rQuadTo(waveWidth/4 ,50,waveWidth/2,0);
        }
        // 形成向下的封闭曲线
        mBezierPath.lineTo(getWidth(),getHeight());
        mBezierPath.lineTo(0,getHeight());
        canvas.drawPath(mBezierPath,mBezierPaint);

        mBezierPath2.moveTo(-waveWidth  - 50 +  dx,originY);
        for (int i = -waveWidth; i <= mWidth + waveWidth ; i+= waveWidth) {
            mBezierPath2.rQuadTo(waveWidth/4 , -50,waveWidth/2,0);
            mBezierPath2.rQuadTo(waveWidth/4 ,50,waveWidth/2,0);
        }
        // 形成向下的封闭曲线
        mBezierPath2.lineTo(getWidth(),getHeight());
        mBezierPath2.lineTo(0,getHeight());

        canvas.drawPath(mBezierPath2,mBezierPaint2);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    private  int dx ;
    private void startAnimation(){
        mAnimator = ValueAnimator.ofInt(0,waveWidth);
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
