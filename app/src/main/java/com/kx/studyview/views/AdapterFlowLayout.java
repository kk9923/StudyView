package com.kx.studyview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.kx.studyview.adapter.FlowAdapter;

/**
 * Created by admin on 2018/8/26.
 */
public class AdapterFlowLayout  extends ViewGroup{

    public AdapterFlowLayout(Context context) {
        super(context);
    }

    public AdapterFlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AdapterFlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lineWidth = 0;
        int lineHeight = 0;
        int height = 0;
        int width = 0;
        int count = getChildCount();

        for (int i = 0; i < count; i++) {
            View childView = getChildAt(i);
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
            MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
            int childHeight = childView.getMeasuredHeight()+lp.topMargin+lp.bottomMargin;
            int childWidth = childView.getMeasuredWidth()+lp.leftMargin+lp.rightMargin;

            //  当前的行宽  +   此控件的宽度  大于 测量模式下的宽度    需要换行
            if (lineWidth + childWidth > getMeasuredWidth()){

                //换行后根据 当前行宽 和 控件 宽度 比较，去宽度最大值
                width = Math.max(lineWidth,childWidth);

                //  换行后,先将换行之前的行高累加， 没有加上换行之后的行高，    根据最后一个控件所处的位置来确定
                height += lineHeight;

                //在需要换行的情况下，下一行的宽高就是此控件的宽高
                lineHeight = childHeight;
                lineWidth = childWidth ;

            }else {   //  不需要换行  ,   不换行的条件下，算出行的最大高度
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight , childHeight);

            }
            //  计算最后一个控件时的情况，   分两种情况
            //  1  最后一个控件处于一行的末尾，   代表不需要换行，根据之前计算的行高加上本行的行高即可
            //  2  最后一个控件单独一行，         需要换行        根据之前计算的行高加上该控件的高度即可，
            if (i == count -1){
                height += lineHeight ;
                width = Math.max(lineWidth,width);
            }
        }
        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth : width ,(measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight : height);
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
            int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
            int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;

            //  当前的行宽  +   此控件的宽度  大于 测量模式下的宽度    需要换行
            if (lineWidth + childWidth > getMeasuredWidth()){
                top += lineHeight ;
                left = 0;

                lineHeight = childHeight;
                lineWidth = childWidth;

            }else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight ,childHeight);

            }
            int childLeft = left +lp.leftMargin;
            int childTop =  top + lp.topMargin;
            int childRight = childLeft + child.getMeasuredWidth() ;
            int childBottom = childTop + child.getMeasuredHeight();

            child.layout(childLeft,childTop,childRight,childBottom);
            left += childWidth;

        }

    }
    private FlowAdapter mFlowAdapter ;

    public void setFlowAdapter(FlowAdapter flowAdapter) {
        mFlowAdapter = flowAdapter;
    }

    @Override
    protected void onAttachedToWindow() {
        if (mFlowAdapter!=null){
            addItemView();
        }
        super.onAttachedToWindow();
    }

    private void addItemView() {
        int childCount = mFlowAdapter.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View view = mFlowAdapter.getView(i);
            if (mListener!=null){
                int finalI = i;
                view.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.OnItemClick(finalI,view);
                    }
                });
            }
            addView(view);
        }

    }
    public  interface  OnItemClickListener {
        void OnItemClick(int position ,View view);
    }
    OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
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
