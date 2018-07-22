package com.kx.studyview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 2018/7/22.
 *
 */
public class BusLineView extends View{
    private String selectTextColor = "#000000";
    private String unSelectTextColor = "#88000000";
    private String unSelectLineColor = "#F1F1F1" ;
    private int defaultTextColor = 0x515151 ;
    private Paint mTextPaint,mLinePaint ,mCirClePaint ;
    private String [] texts ;
    /**
     * 如果是选中的站点，则绘制空心圆，否则绘制实心圆
     */
    private boolean isSelectStation = false;
    /**
     * 当前站点是否是位于选中站点之后的站
     */
    private boolean isAfterSelectStation = false;
    private float mTextWidth;
    private int strokeWidth = 10;
    private int circleRadius = 14;
    private Path mPath;

    public BusLineView(Context context) {
        this(context,null);
    }
    public BusLineView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public BusLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(45);
        mTextPaint.setColor(Color.parseColor("#fd5353"));

        mLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mLinePaint.setAntiAlias(true);
        mLinePaint.setTextSize(30);
        mLinePaint.setStrokeWidth(strokeWidth);
        mLinePaint.setColor(Color.parseColor("#fd5353"));

        mCirClePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirClePaint.setAntiAlias(true);

        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        drawText(canvas);
        // 如果是选中的station站点
       if (isSelectStation){
           mLinePaint.setStrokeWidth(14);
           mLinePaint.setColor(Color.parseColor(unSelectTextColor));
           canvas.drawLine(getWidth() -  mTextWidth/2 +circleRadius ,50,getWidth()  ,50,mLinePaint);
           //  绘制
           mLinePaint.setColor(Color.parseColor("#55fd5353"));
           canvas.drawLine(0,50,getWidth() - mTextWidth + strokeWidth  ,50,mLinePaint);

           mCirClePaint.setStyle(Paint.Style.STROKE);
           mCirClePaint.setStrokeWidth(12);
           mCirClePaint.setColor(Color.parseColor("#fd5353"));
           canvas.drawCircle(getWidth() -  mTextWidth/2 ,50,16,mCirClePaint);

      }else {
           if (isAfterSelectStation ) {
               mCirClePaint.setColor(Color.parseColor(selectTextColor));
               mLinePaint.setColor(Color.parseColor(unSelectTextColor));

           }else {
               mCirClePaint.setColor(Color.parseColor("#99fd5353"));
               mLinePaint.setColor(Color.parseColor("#55fd5353"));
           }

           mCirClePaint.setStyle(Paint.Style.FILL);
           canvas.drawCircle(getWidth() -  mTextWidth/2 ,50,16,mCirClePaint);

           mPath.addCircle(getWidth() -  mTextWidth/2 ,50,16, Path.Direction.CCW);
           canvas.clipPath(mPath, Region.Op.DIFFERENCE);
           mLinePaint.setStrokeWidth(14);
           canvas.drawLine(0 ,50,getWidth()   ,50,mLinePaint);
       }



    }

    private void drawText(Canvas canvas) {
        if (texts==null){
            return;
        }
        int textSize = texts.length;
        int itemHeight = 50 ;
        if (isAfterSelectStation){
            mTextPaint.setColor(Color.parseColor(selectTextColor));
        }else {
            mTextPaint.setColor(Color.parseColor("#fd5353"));
        }
        for (int i = 0; i < textSize; i++) {
            mTextWidth = mTextPaint.measureText(texts[i]);
            canvas.drawText(texts[i],getWidth() - mTextWidth, 100+itemHeight *(i+1),mTextPaint );
        }
    }

    public BusLineView setText(String text){
        int length = text.length();
        texts = new String[length];
        for (int i = 0; i < length; i++) {
            texts[i] = text.substring(i,i+1);
        }
        return this;
    }

    public BusLineView setSelectStation(boolean selectStation) {
        isSelectStation = selectStation;
        return this;
    }

    public BusLineView setAfterSelectStation(boolean afterSelectStation) {
        isAfterSelectStation = afterSelectStation;
        return this;
    }
    public void reLoad(){
        invalidate();
    }
}
