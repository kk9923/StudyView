package com.kx.studyview.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.InputFilter;
import android.util.AttributeSet;

import com.kx.studyview.R;

/**
 * Created by admin  on 2018/7/17.
 */
public class PayInPutView extends android.support.v7.widget.AppCompatEditText {

    private Paint mPaint,mCirClePaint;
    private int mWidth ,mHeight;
    private Rect mRect;
    // 最大密码输入个数
    private  int maxCount = 6;

    //每个小矩形的宽度
    private int mItemRectWidht;
    // 当前输入密码的个数
    private int currentTextCount;
    // 黑圈圈的起始x值
    private int mBlackCircleStartX;


    public PayInPutView(Context context) {
        this(context,null);
     //   init();
    }

    public PayInPutView(Context context, AttributeSet attrs) {
        this(context, attrs, R.attr.editTextStyle);
      //  init();
    }

    public PayInPutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2f);


        mCirClePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirClePaint.setStyle(Paint.Style.FILL);
        mCirClePaint.setStrokeWidth(2f);
        mRect = new Rect();
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setCursorVisible(false);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxCount)});


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        mWidth = w;
        mHeight = h ;
        mRect.left = 0;
        mRect.top = 0;
        mRect.right = mWidth;
        mRect.bottom = mHeight;

        mItemRectWidht = mWidth/ maxCount;
        mBlackCircleStartX = mItemRectWidht / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
       // super.onDraw(canvas);
        //绘制背景矩形边框
        canvas.drawRect(mRect,mPaint);
        // 绘制 竖线
        for (int i = 1; i < maxCount; i++) {
            canvas.drawLine(mItemRectWidht * i ,0,mItemRectWidht * i ,mHeight,mPaint);
        }
        // 绘制 密码 黑圈圈
        for (int i = 0; i <  currentTextCount; i++) {
            canvas.drawCircle(mBlackCircleStartX +i * mItemRectWidht , mHeight/2,10,mCirClePaint);
        }

    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        //  当前输入的密码个数
        currentTextCount = text.toString().trim().length();
      //  if (currentTextCount > )
     //   invalidate();
        if (mOnInPutListener!=null){
            mOnInPutListener.onInPut(text.toString().trim());
        }
    }

    public interface onInPutListener{
        void onInPut(String text);
    }
    onInPutListener mOnInPutListener;

    public void setOnInPutListener(onInPutListener onInPutListener) {
        mOnInPutListener = onInPutListener;
    }
}
