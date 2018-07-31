package com.kx.studyview.views;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Created by admin on 2018/7/31.
 */
public class MySwitchButton extends View {
    private  int mWidth ,mHeight ;
    // 背景色的画笔
    private Paint mBackGroundPaint;
    // 选中时的背景色
    private  int selectBackGroundColor = 0xff67D351;
    // 未选中时的背景色
    private  int unSelectBackGroundColor = 0xffffffff;
    private RectF mBackgroundRectF;
    private Paint buttonPaint,strokePaint;
    private  int defaultPadding = 6;

    public MySwitchButton(Context context) {
        this(context,null);
    }
    public MySwitchButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MySwitchButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        shadowRadius = dp2px(2.5f);
        shadowOffset = dp2px(1.5f);
        shadowColor =  0X33000000 ;
        mBackGroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBackGroundPaint.setColor(unSelectBackGroundColor);
        mBackGroundPaint.setStrokeWidth(2);

        buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        buttonPaint.setColor(Color.WHITE);
        buttonPaint.setShadowLayer(shadowRadius,0,shadowOffset,shadowColor);

        strokePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(2);
        strokePaint.setColor(0XffDDDDDD);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h ;
        mBackgroundRectF = new RectF(defaultPadding,defaultPadding,mWidth-defaultPadding,mHeight-defaultPadding);
        buttonRadius = (mHeight - defaultPadding *2 ) / 2 ;
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawBackground(canvas);
        drawButton(canvas);


    }

    /**
     * 绘制圆形按钮
     */
    private void drawButton(Canvas canvas) {
        canvas.drawCircle(mHeight/2  ,mHeight/2,buttonRadius-4,buttonPaint);

        canvas.drawCircle(mHeight/2  ,mHeight/2, buttonRadius-4, strokePaint);


    }


    /**
     * 绘制背景色
     * @param canvas
     */
    private void drawBackground(Canvas canvas) {

        canvas.drawRoundRect(mBackgroundRectF,mHeight/2,mHeight/2,mBackGroundPaint);

        canvas.drawRoundRect(mBackgroundRectF,mHeight/2,mHeight/2,strokePaint);
    }

    private static float dp2px(float dp){
        Resources r = Resources.getSystem();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
    }

    /**
     * 阴影半径
     */
    private float shadowRadius;
    /**
     * 阴影Y偏移px
     */
    private float shadowOffset;
    /**
     * 阴影颜色
     */


    private int shadowColor ;

    /**
     * 背景半径
     */
    private float viewRadius;
    /**
     * 按钮半径
     */
    private float buttonRadius;
    private final android.animation.ArgbEvaluator argbEvaluator
            = new android.animation.ArgbEvaluator();
}
