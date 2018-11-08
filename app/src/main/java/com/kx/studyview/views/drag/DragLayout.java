package com.kx.studyview.views.drag;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.kx.studyview.R;

/**
 * Created by admin on 2018/11/8.
 */
public class DragLayout extends FrameLayout {

    private View dragView;
    private ViewDragHelper mViewDragHelper;
    private GestureDetector mGestureDetector ;
    private int mChildBottom;

    public DragLayout(@NonNull Context context) {
        this(context,null);
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DragLayout, 0, 0);
        mFixHeight = a.getDimensionPixelOffset(R.styleable.DragLayout_fix_height, mFixHeight);
        a.recycle();
        mGestureDetector = new GestureDetector(context,mGestureListener);
        mViewDragHelper = ViewDragHelper.create(this,1f,mCallback );
    }

    ViewDragHelper.Callback mCallback = new ViewDragHelper.Callback() {
        @Override
        public boolean tryCaptureView(@NonNull View child, int pointerId) {
            return  child==dragView;
        }

        /**
         *  限制移动的上下边界
         */
        @Override
        public int clampViewPositionVertical(@NonNull View child, int top, int dy) {
            if (top <= mHeight -  child.getHeight()){
                top =  mHeight -  child.getHeight();
            }else if (top >=  mHeight - mFixHeight ){
                top = mHeight - mFixHeight;
            }
            return top;
        }
        /**
         *  松手时回调
         */
        @Override
        public void onViewReleased(@NonNull View releasedChild, float xvel, float yvel) {
            int controlY = mHeight - releasedChild.getHeight() / 2 ;
            int top = releasedChild.getTop();
            // 向上位移
            if (top < controlY){
                mViewDragHelper.smoothSlideViewTo(releasedChild,0,mHeight - releasedChild.getHeight());
            }else if (top > controlY){
                // 向上位移
                mViewDragHelper.smoothSlideViewTo(releasedChild,0,mHeight -mFixHeight);
            }
            ViewCompat.postInvalidateOnAnimation(DragLayout.this);
        }
    };

    @Override
    public void computeScroll() {
        if (mViewDragHelper.continueSettling(true)) {
            ViewCompat.postInvalidateOnAnimation(DragLayout.this);
        }
        super.computeScroll();
    }
    /**
     * 手势监听
     */
    private GestureDetector.OnGestureListener mGestureListener = new GestureDetector.SimpleOnGestureListener() {
        // 是否是按下的标识，默认为其他动作，true为按下标识，false为其他动作
        private boolean isDownTouch;

        @Override
        public boolean onDown(MotionEvent e) {
            isDownTouch = true;
            return super.onDown(e);
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            System.out.println("onSingleTapUp");
            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (isDownTouch) {
//                // 如果为上下滑动则控制拖拽
//                if (Math.abs(distanceY) > Math.abs(distanceX)) {
//                    _stopAllScroller();
//                    mDragHelper.captureChildView(mDragView, 0);
//                    mIsDrag = true;
//                }
//                isDownTouch = false;
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }
    };
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mViewDragHelper.processTouchEvent(event);
        return true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        // 调用父类的方法，避免可能出现的 IllegalArgumentException: pointerIndex out of range
        super.onInterceptTouchEvent(ev);
        if (mViewDragHelper.isViewUnder(dragView, (int) ev.getX(), (int) ev.getY())){
            return true ;
        }
        return super.onInterceptTouchEvent(ev);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = h;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mMaxHeight == 0) {
            // 未设置最大高度则为布局高度的 1/2
            mMaxHeight = getMeasuredHeight() / 2;
        } else if (mMaxHeight > getMeasuredHeight()) {
            // MODE_DRAG 模式最大高度不超过布局高度
            mMaxHeight = getMeasuredHeight() / 2;
        }
        View childView = getChildAt(1);
        MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
        int childWidth = childView.getMeasuredWidth();
        int childHeight = childView.getMeasuredHeight();
        // 限定视图的最大高度
        if (childHeight > mMaxHeight) {
            childView.measure(MeasureSpec.makeMeasureSpec(childWidth - lp.leftMargin - lp.rightMargin, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec(mMaxHeight - lp.topMargin - lp.bottomMargin, MeasureSpec.EXACTLY));
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        View childView = getChildAt(1);
        MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
        int childWidth = childView.getMeasuredWidth();
        int childHeight = childView.getMeasuredHeight();

        if (mFixHeight > childHeight){
            mFixHeight = childHeight;
        }

        int childLeft  = lp.leftMargin;
        int childTop  = bottom - mFixHeight;
        int childright  = lp.rightMargin+ childWidth;
        if (mChildBottom==0){
            mChildBottom = bottom + childHeight - mFixHeight;
        }
        childView.layout(childLeft,childTop,childright, mChildBottom);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        dragView = getChildAt(1);
    }

    // 整个布局高度
    private int mHeight;
    // 文本缩放时的固定高度
    private int mFixHeight ;
    // 文本完全显示的最大高度
    private int mMaxHeight;
    // DragView的Top属性值
    private int mDragViewTop = 0;



}
