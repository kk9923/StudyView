package com.kx.studyview.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.kx.studyview.utils.LogUtils;

/**
 * Created by admin on 2018/7/21.
 */
public class LayoutContainer extends ViewGroup {
    public LayoutContainer(Context context) {
        this(context,null);
    }

    public LayoutContainer(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LayoutContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

//    @Override
//    public LayoutParams generateLayoutParams(AttributeSet attrs) {
//        return new MarginLayoutParams(getContext(),attrs);
//    }

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        /**
         * 获得此ViewGroup上级容器为其推荐的宽和高，以及计算模式
         */
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        // 计算出所有的childView的宽和高
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        /**
         * 记录如果是wrap_content是设置的宽和高
         */
        int wrapContentWidth = 0;
        int wrapContentHeight = 0;
        //  子View的数量
        int childCount = getChildCount();

        // 分别算出  左  上  右  下  四个边的宽和高， 当 ViewGroup的测量模式为wrap_content 时 设置ViewGroup的宽高为  左  上  右  下  四个边的宽高的最大值

        int leftHeight = 0;    //  左边根据子View 算出来的高度
        int topWidth = 0;      //  顶部根据子View 算出来的宽度
        int rightHeight = 0;   //  右边根据子View 算出来的高度
        int bottomWidth = 0;   //  底边根据子View 算出来的宽度

        //  每个子View的LayoutParams
        MarginLayoutParams childParams = null;

        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //   获得 每个子View的测量宽高
            int measuredHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            childParams = (MarginLayoutParams) getLayoutParams();
            LogUtils.e("i =  "  +  i +
                    "  childParams.leftMargin=   " +childParams.leftMargin +
                    "  childParams.topMargin=   "  +childParams.topMargin +
                    "  childParams.rightMargin=   "  +childParams.rightMargin +
                    "  childParams.bottomMargin=   "  +childParams.bottomMargin



            );
            //  顶部的两个子View
            if ( i ==0  || i == 1){
                //  计算出顶部的实际宽度
                topWidth += measuredWidth +childParams.leftMargin +childParams.rightMargin ;
            }
            if ( i ==2  || i == 3){
                //  计算出底部的实际宽度
                bottomWidth += measuredWidth +childParams.leftMargin +childParams.rightMargin ;
            }
            if ( i ==0  || i == 2){
                //  计算出左边的实际高度
                leftHeight += measuredHeight +childParams.topMargin +childParams.bottomMargin ;
            }
            if ( i ==1  || i == 3){
                //  计算出右边的实际高度
                rightHeight += measuredHeight +childParams.topMargin +childParams.bottomMargin ;
            }
        }
        //   算出在wrap_content情况下，ViewGroup的宽高(就是子View的宽高加一起)
        //  顶部和底部的宽取最大值
        wrapContentWidth = Math.max(topWidth,bottomWidth);
        //  左边和右边的高取最大值
        wrapContentHeight = Math.max(leftHeight,rightHeight);
        //   当ViewGroup的测量模式为EXACTLY(宽度为具体dp值  或者 为  match_parent) 时，设置宽度为系统测量的值，否则设置为我们计算的值
        setMeasuredDimension((widthMode == MeasureSpec.EXACTLY )? sizeWidth :wrapContentWidth,(heightMode == MeasureSpec.EXACTLY ) ? sizeHeight : wrapContentHeight);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //  每个子View的LayoutParams
        MarginLayoutParams childParams = null;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            //   获得 每个子View的测量宽高
            int measuredHeight = childView.getMeasuredHeight();
            int measuredWidth = childView.getMeasuredWidth();
            childParams = (MarginLayoutParams) getLayoutParams();

            int childLeft  = 0, childTop = 0 ,childRight = 0 ,childBottom = 0 ;

            switch (i){
                case 0:
                    childLeft = childParams.leftMargin ;

                    childTop = childParams.topMargin;


                    break;
                case 1:
                    childLeft =getWidth() -  measuredWidth -  childParams.leftMargin - childParams.rightMargin ;

                    childTop = childParams.topMargin;

                    break;
                case 2:
                    childLeft = childParams.leftMargin ;

                    childTop = getHeight() - measuredHeight  - childParams.topMargin- childParams.bottomMargin;

                    break;
                case 3:
                    childLeft = getWidth() -  measuredWidth -  childParams.leftMargin - childParams.rightMargin ;

                    childTop = getHeight() - measuredHeight  - childParams.topMargin- childParams.bottomMargin;

                    break;
            }
            childRight = childLeft + measuredWidth ;
            childBottom = childTop + measuredHeight;
            childView.layout(childLeft,childTop,childRight,childBottom);

        }

    }
}
