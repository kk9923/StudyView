package com.kx.studyview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


/**
 * Created by admin  on 2018/7/9.
 * 自定义水波纹效果,通过 贝塞尔 函数实现
 */
public class BezierWaveView extends View {
    private Paint mBezierPaint ;
    private Path mBezierPath = new Path();
    //波纹的宽度
    private int waveWidth;
    //波纹的高度
    private int waveHeight;
    private int mWidth,mHeight;
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
        mBezierPaint.setColor(Color.WHITE);
        waveWidth = 200;
        waveHeight = 100;
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
       // canvas.translate(0 ,50);
        mBezierPath.reset();
       //  向下封闭
       mBezierPath.moveTo(0,100);
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


       // mBezierPath.rCubicTo(getWidth()/4,0,getWidth()*3/4,getHeight(),getWidth(),getHeight()/2);

        for (int i = -waveWidth; i < mWidth + waveWidth ; i+= waveWidth) {
            //mBezierPath.rQuadTo(waveWidth/4 , -waveHeight,waveHeight/2,0);
              //  mBezierPath.rQuadTo(waveWidth/4 ,waveHeight,waveWidth/2,0);
            mBezierPath.rCubicTo(waveWidth / 4, -waveHeight, waveWidth *3/ 4  , waveHeight, waveWidth, 0);

        }


      //  mBezierPath.lineTo(getWidth(),getHeight());

        canvas.drawPath(mBezierPath,mBezierPaint);

    }
}
