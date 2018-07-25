package com.kx.studyview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2018/7/25.
 * 仿支付宝  咻一咻  扩散动画
 */
public class DiffuseView extends View {
    /**
     * 判断是否扩散中
     */
    private boolean isShowing = true ;
    private Paint mPaint;
    // 透明度集合
    private List<Integer> mAlphas = new ArrayList<>();
    // 扩散圆半径集合
    private List<Integer> mWidths = new ArrayList<>();
    public DiffuseView(Context context) {
        this(context,null);
    }

    public DiffuseView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public DiffuseView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(0x0059ccf5);
        // 先添加一个初始值
        mAlphas.add(255);
        mWidths.add(0);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        int size = mAlphas.size();
        setBackgroundColor(Color.TRANSPARENT);// 颜色：完全透明
        for (int i = 0; i <size ; i++) {
            Integer alpha = mAlphas.get(i);
            mPaint.setAlpha(alpha);
            Integer circleRadius = mWidths.get(i);
            canvas.drawCircle(getWidth()/2,getHeight()/2,circleRadius + 50,mPaint);
            //   1  相当于扩散速度， 每次绘制都向外移动一个像素
            if (isShowing && alpha > 0 && circleRadius <255) {


                mWidths.set( i,circleRadius+ 1);
                mAlphas.set( i,alpha- 1);
            }
        }
            if (isShowing && mWidths.get(mWidths.size() - 1)  ==255 /5) {
                mAlphas.add(255);
                mWidths.add(0);
            }

        if (isShowing && mWidths.size() == 10) {
            mWidths.remove(0);
            mAlphas.remove(0);
        }
        invalidate();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        start();
    }

    // 执行动画
    public void start() {
        isShowing = true;
        invalidate();
    }

    // 停止动画
    public void stop() {
        isShowing = false;
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        mAlphas.clear();
        mWidths.clear();
    }
}
