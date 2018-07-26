package com.kx.studyview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2018/7/26.
 * 自定义流式布局
 */
public class FlowLayout extends ViewGroup {
    public FlowLayout(Context context) {
        super(context);
    }
    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);


        int lineWidth = 0;
        int lineHeight = 0;
        int height = 0;
        int width = 0;
        int count = getChildCount();
        for (int i=0;i<count;i++){
            View child = getChildAt(i);
            measureChild(child,widthMeasureSpec,heightMeasureSpec);

            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = child.getMeasuredWidth() + lp.leftMargin +lp.rightMargin;
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

            if (lineWidth + childWidth > measureWidth){
                //需要换行
                width = Math.max(lineWidth,width);
                height += lineHeight;
                //因为由于盛不下当前控件，而将此控件调到下一行，所以将此控件的高度和宽度初始化给lineHeight、lineWidth
                lineHeight = childHeight;
                lineWidth = childWidth;
            }else{
                // 否则累加值lineWidth,lineHeight取最大高度
                lineHeight = Math.max(lineHeight,childHeight);
                lineWidth += childWidth;
            }

            //最后一行是不会超出width范围的，所以要单独处理
            if (i == count -1){
                height += lineHeight;
                width = Math.max(width,lineWidth);
            }

        }
        //当属性是MeasureSpec.EXACTLY时，那么它的高度就是确定的，
        // 只有当是wrap_content时，根据内部控件的大小来确定它的大小时，大小是不确定的，属性是AT_MOST,此时，就需要我们自己计算它的应当的大小，并设置进去
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth
                : width, (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight: height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int count = getChildCount();
        int lineWidth = 0;//累加当前行的行宽
        int lineHeight = 0;//当前行的行高
        //当前坐标的top坐标和left坐标
        int top=0, left=0;

        for (int i=0;i<count;i++) {
            View child = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
            int childHeight = child.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            int childWidth = child.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;

            if (lineWidth + childWidth > getMeasuredWidth()){
                //需要换行
               left = 0;
               top += lineHeight;

                //因为由于盛不下当前控件，而将此控件调到下一行，所以将此控件的高度和宽度初始化给lineHeight、lineWidth
                lineHeight =childHeight;
                lineWidth = childWidth;
            }else{
                // 否则累加值lineWidth,lineHeight取最大高度
                lineHeight = Math.max(lineHeight,childHeight);
                lineWidth += childWidth;
            }

            int childLeft = left + lp.leftMargin;
            int childTop = top+lp.topMargin;
            int childRight = childLeft + child.getMeasuredWidth();
            int childBottom = childTop+child.getMeasuredHeight();
            child.layout(childLeft, childTop, childRight, childBottom);
            //将left置为下一子控件的起始点
            left += childWidth ;
        }


    }

    /**
     * ViewGroup  默认的LayoutParams 自能得到宽高属性，
     *         MarginLayoutParams 支持获取Margin值
     */
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
