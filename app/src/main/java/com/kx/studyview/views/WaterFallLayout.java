package com.kx.studyview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admin on 2018/7/30.
 * 简单的瀑布流容器
 */
public class WaterFallLayout extends ViewGroup {
    /**
     * 列数
     */
    private int columns = 3;
    /**
     * 水平间距
     */
    private int hSpace = 20;
    /**
     * 竖直间距
     */
    private int vSpace = 20;
    /**
     * child的宽度
     */
    private int childWidth = 0;
    /**
     * 用来存放每列图片的高度
     */
    private int top[];


    public WaterFallLayout(Context context) {
        this(context, null);
    }
    public WaterFallLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public WaterFallLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        top = new int[columns];
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        // 每个子View的宽度一样，
        childWidth = (sizeWidth - (columns - 1) * hSpace) / columns;

        int childCount = getChildCount();
        clearTop();
        WaterFallLayoutParams params;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            params = (WaterFallLayoutParams) childView.getLayoutParams();
            //  根据子View的宽高比和childWidth来算出每个子View在控件中的高度
            int childHeight = childView.getMeasuredHeight() * childWidth / childView.getMeasuredWidth();
            int minHeightColumn = getMinHeightColumn();

            // 将 child的上下左右四个信息保存在LayoutParams中
            params.left= (childWidth + hSpace)* minHeightColumn;
            // child总是放在高度最小的位置，所以根据minHeightColumn求出最小高度
            params.top = top[minHeightColumn];
            params.right = params.left + childWidth ;
            params.bottom = params.top+childHeight ;

            top[minHeightColumn]  += childHeight + vSpace;
        }

        int wrapWidth;
        if (childCount < columns) {
            wrapWidth = childCount * childWidth + (childCount - 1) * hSpace;
        } else {
            wrapWidth = sizeWidth;
        }
        int wrapHeight;
        wrapHeight = top[getMaxHeightColumn()];
        setMeasuredDimension(widthMode == MeasureSpec.AT_MOST ? wrapWidth : sizeWidth, wrapHeight);

    }

    /**
     * 未使用 自定义LayoutParams的onLayout方法
     */
//    @Override
//    protected void onLayout(boolean changed, int l, int t, int r, int b) {
//            int childCount = getChildCount();
//           clearTop();
//            for (int i = 0; i < childCount; i++) {
//                View childView = getChildAt(i);
//                //  根据子View的宽高比和childWidth来算出每个子View在控件中的高度
//                int childHeight = childView.getMeasuredHeight() * childWidth / childView.getMeasuredWidth();
//
//                int minHeightColumn = getMinHeightColumn();
//                int childLeft = (childWidth + hSpace)* minHeightColumn;
//                // child总是放在高度最小的位置，所以根据minHeightColumn求出最小高度
//                int childTop = top[minHeightColumn];
//                int childRight = childLeft + childWidth ;
//                int childBottom = childTop+childHeight ;
//                childView.layout(childLeft,childTop,childRight,childBottom);
//                top[minHeightColumn]  += childHeight + vSpace;
//            }
//        }
    /**
     * 使用 自定义LayoutParams的onLayout方法
     * 通过LayoutParams去保存图片的上下左右四个点的位置信息，不用在onLayout方法中再计算一边。提高性能
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        WaterFallLayoutParams params;
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            params = (WaterFallLayoutParams) childView.getLayoutParams();
            childView.layout(params.left,params.top,params.right,params.bottom);
        }
    }
    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new WaterFallLayoutParams(getContext(), attrs);
    }

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
        return new WaterFallLayoutParams(WaterFallLayoutParams.WRAP_CONTENT, WaterFallLayoutParams.WRAP_CONTENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p) {
        return new WaterFallLayoutParams(p);
    }
    /**
     * 获取高度最小的列数
     */
    public int getMinHeightColumn(){
        int minColumn = 0;
        for (int i = 0; i < columns; i++) {
            if (top[i] < top[minColumn]) {
                minColumn = i;
            }
        }
        return minColumn ;
    }
    /**
     * 获取高度最大的列数
     */
    public int getMaxHeightColumn() {
        int maxColumn = 0;
        for (int i = 0; i < columns; i++) {
            if (top[i] > top[maxColumn]) {
                maxColumn = i;
            }
        }
        return maxColumn;
    }

    /**
     * 每次计算高度之前,清空top[]数组
     */
    private void clearTop() {
        for (int i = 0; i < columns; i++) {
            top[i] = 0;
        }
    }
    public void setOnItemClickListener(final OnItemClickListener listener) {
        for (int i = 0; i < getChildCount(); i++) {
            final int index = i;
            View view = getChildAt(i);
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(v, index);
                }
            });
        }
    }
    public  interface  OnItemClickListener {
       void onItemClick(View view,int index);
    }
    public static class WaterFallLayoutParams extends ViewGroup.LayoutParams {
        public int left = 0;
        public int top = 0;
        public int right = 0;
        public int bottom = 0;

        public WaterFallLayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public WaterFallLayoutParams(int width, int height) {
            super(width, height);
        }

        public WaterFallLayoutParams(LayoutParams source) {
            super(source);
        }
    }



    /**
     * 重写该方法，避免使用自定义LayoutParams时报类型转换错位
     */
    @Override
    protected boolean checkLayoutParams(LayoutParams p) {
        return false;
    }
}
