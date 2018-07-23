package com.kx.studyview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2018/7/23.
 */
public class RegionView extends View {

    private Region mRegion;
    private Paint mPaint;
    private Path mPath;
    private RectF innerRect;
    private Rect mOutRect;

    public RegionView(Context context) {
        this(context,null);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RegionView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mRegion = new Region();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mOutRect = new Rect(50,50,200,800);
        //设置 Region的范围
        mRegion.set(mOutRect);
        mPath = new Path();
        innerRect = new RectF(60,60,180,400);
        mPath.addArc(innerRect,0,-180);
        //  setPath     将Region和Path取交集
        /**
         * 通过 setPath 方法。取Region 和  Path 所在区域的交集，并将交集区域赋值给Region，在自定义View中
         * 结合Region.contains(int x, int y ) 方法可以用来判断点击位置是否是我们想要的区域
         */
        mRegion.setPath(mPath,mRegion);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        drawRegion(canvas);
    }

    /**
     * RegionIterator类，实现了获取组成区域的矩形集的功能,
     * 由于在Canvas中没有直接绘制Region的函数，我们想要绘制一个区域，就只能通过利用RegionIterator构造矩形集来逼近的显示区域
     * @param canvas
     */
    private void drawRegion(Canvas canvas) {
        RegionIterator regionIterator = new RegionIterator(mRegion);
        Rect rect = new Rect();
        while (regionIterator.next(rect)){
            canvas.drawRect(rect,mPaint);
        }

    }
}
