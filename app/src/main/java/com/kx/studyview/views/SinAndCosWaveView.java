package com.kx.studyview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import com.kx.studyview.R;

/**
 * Created by admin on 2018/6/21.
 * 自定义水波纹效果,通过 Sin 和 Cos 三角函数实现
 */

public class SinAndCosWaveView extends View {
    private static final String TAG = "WaveView";
    private int mWidth,mHeight;
    private Path  mAbovePath,mBelowWavePath;
    private Paint mAboveWavePaint,mBelowWavePaint;
    private float φ = 0f;
    public SinAndCosWaveView(Context context) {
        this(context,null);
    }

    public SinAndCosWaveView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SinAndCosWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //初始化路径
        mAbovePath = new Path();
        mBelowWavePath = new Path();
        //初始化画笔
        mAboveWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAboveWavePaint.setAntiAlias(true);
        mAboveWavePaint.setStyle(Paint.Style.FILL);
        mAboveWavePaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));

        mBelowWavePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBelowWavePaint.setAntiAlias(true);
        mBelowWavePaint.setStyle(Paint.Style.FILL);
        mBelowWavePaint.setColor(ContextCompat.getColor(context, R.color.colorAccent));
        mBelowWavePaint.setAlpha(80);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
    float y,y2;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 通过三角函数方式实现
        //角速度
        double w = 2 * Math.PI / getWidth();
        // y = Asin(wx + φ )+k;  通过不断改变  φ 来实现水平位移的效果。
        mAbovePath.reset();
        mBelowWavePath.reset();
        // 将 Path的起点移动到View的左下角，
        mAbovePath.moveTo(getLeft(),getBottom());
        mBelowWavePath.moveTo(getLeft(),getBottom());
        for (int i = 0; i <= getWidth(); i+= 20) {
            y = (float) (8 * Math.cos(w * i + φ) +8);
            y2 = (float) (8 * Math.sin(w * i + φ));

            mAbovePath.lineTo(i,y);
            mBelowWavePath.lineTo(i,y2);
        }
        // 将 Path的终点移动到View的右下角，形成一个完整的波浪闭环。
        mAbovePath.lineTo(getRight(),getBottom());
       mBelowWavePath.lineTo(getRight(),getBottom());
        canvas.drawPath(mAbovePath,mAboveWavePaint);
        canvas.drawPath(mBelowWavePath,mBelowWavePaint);
        φ -= 0.1f ;
        //   每20ms就回调一次onDraw()方法。
        postInvalidateDelayed(20);

    }
}
