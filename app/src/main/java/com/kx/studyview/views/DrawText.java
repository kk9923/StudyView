package com.kx.studyview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.kx.studyview.R;

/**
 * Created by admin on 2018/7/23.
 */
public class DrawText extends View {
    private String [] text = {"我","是","测","试","文","字"};
    private  float itemWidth ,itemHeight = 0;
    int width ,height;
    private Paint mLinePaint;
    private Rect mRect;
    private int mTextCount;
    private Paint mTextPaint;
    private int mOrientation;
    private int defaultOrientation = 1 ;

    public DrawText(Context context) {
        this(context,null);
    }
    public DrawText(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public DrawText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta =   context.obtainStyledAttributes(attrs, R.styleable.DrawText);
        mOrientation = ta.getInt(R.styleable.DrawText_orientation, defaultOrientation);

        ta.recycle();
        mTextCount = text.length;
        mLinePaint = new Paint();
        mLinePaint.setStrokeWidth(2);
        mLinePaint.setColor(Color.BLACK);
        mLinePaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(Color.RED);
        mTextPaint.setTextSize(50);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h ;
        mRect = new Rect(0,0,width,height);
        itemWidth = width / text.length;
        itemHeight = height / text.length;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mRect,mLinePaint);
        if (mOrientation == defaultOrientation){
            drawVertical(canvas);
        }else {
            drawHorizontal(canvas);
        }

    }

    private void drawVertical(Canvas canvas) {
        for (int i = 1; i < mTextCount; i++) {
            canvas.drawLine( 0,i * itemHeight, getWidth(), i * itemHeight,mLinePaint);
        }
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float top = fm.top;
        float bottom = fm.bottom;
        float  baseline = itemHeight/2  - (bottom + top)/2 ;
        for (int i = 0; i <mTextCount ; i++) {
            String s = text[i];
            float textWidth = mTextPaint.measureText(s);
            canvas.drawText(s, (getWidth() - textWidth)/2 ,baseline+ i * itemHeight ,mTextPaint);
        }
    }

    private void drawHorizontal(Canvas canvas) {
        for (int i = 1; i < mTextCount; i++) {
            canvas.drawLine( i * itemWidth,0, i * itemWidth, height,mLinePaint);
        }
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float top = fm.top;
        float bottom = fm.bottom;
        float  baseline = height/2  - (bottom + top)/2 ;
        for (int i = 0; i <mTextCount ; i++) {
            String s = text[i];
            float textWidth = mTextPaint.measureText(s);
            canvas.drawText(s, (itemWidth - textWidth)/2 + i * itemWidth,baseline,mTextPaint);
        }
    }
}
