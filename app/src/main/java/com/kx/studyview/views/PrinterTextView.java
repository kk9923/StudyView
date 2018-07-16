package com.kx.studyview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.animation.LinearInterpolator;

public class PrinterTextView extends android.support.v7.widget.AppCompatTextView {
    private StringBuilder stringBuilder ;
    private String mPrintText  = "我是默认的打印字符串";
    private Rect mTextRect;
    private ValueAnimator mValueAnimator;

    public PrinterTextView(Context context) {
        this(context,null);
    }

    public PrinterTextView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PrinterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        stringBuilder = new StringBuilder();
        mTextRect = new Rect();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mTextRect.left = getPaddingLeft();
        mTextRect.top = getPaddingTop();
        mTextRect.right = getWidth() - getPaddingRight();
        mTextRect.bottom = getHeight() - getPaddingBottom();

        Paint.FontMetricsInt fontMetricsInt = getPaint().getFontMetricsInt();
        int baseLine  = (mTextRect.bottom+mTextRect.top - fontMetricsInt.bottom - fontMetricsInt.top)/2;
        canvas.drawText(stringBuilder.toString(),getPaddingLeft(),baseLine,getPaint());

    }
    /**
     * 设置需要逐字显示的字符串
     * @param printText
     * @return
     */
    public PrinterTextView setPrintText(String printText){
        if (printText!=null && printText.length()>0){
             mPrintText = printText ;
        }
        stringBuilder = new StringBuilder();
        return this;
    }

    private int currentIndex = -1 ;
    public void startAnimation(){
        mValueAnimator = ValueAnimator.ofInt(0,mPrintText.length()-1);
        mValueAnimator.setDuration(200 * mPrintText.length());
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int index = (int) animation.getAnimatedValue();
                    if (index!=currentIndex){

                        currentIndex = index ;

                        stringBuilder.append(mPrintText.substring(index,index+1));

                        invalidate();

                    }
            }
        });
        mValueAnimator.start();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        startAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mValueAnimator!=null){
            mValueAnimator.cancel();
            mValueAnimator =null;
        }
    }
}
