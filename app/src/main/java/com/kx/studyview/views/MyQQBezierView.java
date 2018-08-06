package com.kx.studyview.views;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin  on 2018/8/6.
 * 仿QQ消息红点拖拽效果view
 */
public class MyQQBezierView  extends View{
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    public MyQQBezierView(Context context) {
        this(context,null);
    }
    public MyQQBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MyQQBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint.setColor();
    }
    private PointF pointA = new PointF();
    private PointF pointB = new PointF();
    private PointF pointC = new PointF();
    private PointF pointD = new PointF();
    private PointF pointControl = new PointF();

}
