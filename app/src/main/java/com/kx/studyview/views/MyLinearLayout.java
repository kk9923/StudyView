package com.kx.studyview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2018/7/26.
 */
public class MyLinearLayout extends ViewGroup {
    public MyLinearLayout(Context context) {
        this(context, null);
    }

    public MyLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int childCount = getChildCount();

        //记录自身的宽高
        int mWidth = 0;
        int mHeight = 0;

        int childWidth = 0;
        int childHeight = 0;

        MarginLayoutParams params = null;

        for (int i = 0; i < childCount; i++) {
            //测量子控件
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            params = (MarginLayoutParams) child.getLayoutParams();
            //获得子控件的高度和宽度
            childHeight = child.getMeasuredHeight() + params.topMargin+params.bottomMargin ;
            childWidth = child.getMeasuredWidth()+params.leftMargin+params.rightMargin ;
            //得到最大宽度，并且累加高度
            mHeight += childHeight;
            mWidth = Math.max(childWidth, mWidth);
        }
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth : mWidth, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight : mHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int top = 0;
        int count = getChildCount();
        for (int i=0;i<count;i++) {

            View child = getChildAt(i);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            int childWidth = child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;

            child.layout(0, top, childWidth, top + childHeight);
            top += childHeight;
        }
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }
}
