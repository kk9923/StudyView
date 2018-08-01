package com.kx.studyview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by admin on 2018/8/1.
 * 仿QQ消息红点拖拽效果view
 */
public class QQBezierView extends View {

    public QQBezierView(Context context) {
        this(context,null);
    }

    public QQBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public QQBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
        initPoint();
    }
    /**
     * 初始化所有点
     */
    private void initPoint() {
        pointStart = new PointF(startX, startY);
        pointEnd = new PointF(startX, startY);
        pointA = new PointF();
        pointB = new PointF();
        pointC = new PointF();
        pointD = new PointF();

        pointControl = new PointF();

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(circleColor);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        path = new Path();
        initTextPaint();
    }
    /**
     * 初始化文字画笔
     */
    private void initTextPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(sp2px(13));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        startX = w / 2;
        startY = h / 2;
        maxDistance = dp2px(100);
        radiusStart = dp2px(15);
        radiusEnd = dp2px(15);
        currentRadiusEnd = radiusEnd;
        currentRadiusStart = radiusStart;
        // 后面小圆的圆形坐标点
        pointStart.set(startX,startY);
        rect.left = (int) (startX - radiusStart);
        rect.top = (int) (startY - radiusStart);
        rect.right = (int) (startX + radiusStart);
        rect.bottom = (int) (startY + radiusStart);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if (isOutOfRang){
            if (!disappear){
                canvas.drawCircle(pointEnd.x,pointEnd.y,currentRadiusEnd,circlePaint);
            }
        }else {
            //绘制 后面的小圆
            canvas.drawCircle(pointStart.x,pointStart.y,currentRadiusStart,circlePaint);
            if (mIsCanDrag){
                canvas.drawCircle(pointEnd.x,pointEnd.y,currentRadiusEnd,circlePaint);
                    drawBezier(canvas);
            }
        }

        if (!disappear) {
            if (msgCount > 0) {
                if (pointEnd.x==0||pointEnd.y==0){
                    drawText(canvas, msgCount, pointStart);
                }else {
                    drawText(canvas, msgCount, pointEnd);
                }
            }
        }

    }

    private void drawBezier(Canvas canvas) {
        path .reset();
        path.moveTo(pointA.x,pointA.y);
        path.quadTo(pointControl.x,pointControl.y,pointC.x,pointC.y);
        path.lineTo(pointD.x,pointD.y);
        path.quadTo(pointControl.x,pointControl.y,pointB.x,pointB.y);
        path.close();
        canvas.drawPath(path,circlePaint);
    }

    private float currentX ,currentY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                //触摸点是否在圆的坐标域内
                mIsCanDrag = rect.contains((int) event.getX(), (int) event.getY());
                break;
            case MotionEvent.ACTION_MOVE :

                if (mIsCanDrag) {
                    currentX = event.getX();
                    currentY = event.getY();
                    //设置拖拽圆的坐标
                    pointEnd.set(currentX, currentY);
                    if (!isOutOfRang) {
                        setCurrentRadius();
                        setABCDOPoint();
                    }
                    invalidate();
                }


                break;
            case MotionEvent.ACTION_UP :
                if (mIsCanDrag) {
                    if (isOutOfRang) {
                        //消失动画
                        disappear = true;
                        if (onDragBallListener != null) {
                            onDragBallListener.onDisappear();
                        }
                        invalidate();
                    } else {
                        disappear = false;
                        //回弹动画
                        final float a = (pointEnd.y - pointStart.y) / (pointEnd.x - pointStart.x);
                        ValueAnimator valueAnimator = ValueAnimator.ofFloat(pointEnd.x, pointStart.x);
                        valueAnimator.setDuration(500);
                        valueAnimator.setInterpolator(new BounceInterpolator());
                        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            @Override
                            public void onAnimationUpdate(ValueAnimator animation) {
                                float x = (float) animation.getAnimatedValue();

                                float y = pointStart.y + a * (x - pointStart.x);

                                pointEnd.set(x, y);
                                setCurrentRadius();

                                setABCDOPoint();

                                invalidate();

                            }
                        });
                        valueAnimator.start();
                    }
                }
                break;
        }
        return true;
    }
    /**
     * 绘制文字
     */
    private void drawText(Canvas canvas, int msgCount, PointF point) {
        textRect.left = (int) (point.x - radiusStart);
        textRect.top = (int) (point.y - radiusStart);
        textRect.right = (int) (point.x + radiusStart);
        textRect.bottom = (int) (point.y + radiusStart);
        Paint.FontMetricsInt fontMetrics = textPaint.getFontMetricsInt();
        int baseline = (textRect.bottom + textRect.top - fontMetrics.bottom - fontMetrics.top) / 2;
        //文字绘制到整个布局的中心位置
        canvas.drawText(msgCount > 99 ? "99+" : msgCount + "", textRect.centerX(), baseline, textPaint);
    }
    private void setABCDOPoint() {
        //控制点坐标
        pointControl.set((pointStart.x + pointEnd.x) / 2.0f, (pointStart.y + pointEnd.y) / 2.0f);
        // 四个点的坐标

        float angle = (float) Math.atan((pointStart.y-pointEnd.y)/(pointEnd.x-pointStart.x));

        pointC.x = (float) (pointEnd.x - currentRadiusEnd*Math.sin(angle));
        pointC.y = (float) (pointEnd.y - currentRadiusEnd*Math.cos(angle));

        pointD.x = (float) (pointEnd.x + currentRadiusEnd*Math.sin(angle));
        pointD.y = (float) (pointEnd.y + currentRadiusEnd*Math.cos(angle));

        pointA.x = (float) (pointStart.x - currentRadiusStart*Math.sin(angle));
        pointA.y = (float) (pointStart.y - currentRadiusStart*Math.cos(angle));

        pointB.x = (float) (pointStart.x + currentRadiusStart*Math.sin(angle));
        pointB.y = (float) (pointStart.y + currentRadiusStart*Math.cos(angle));
    }

    private float getTwoPointDistance(PointF point1, PointF point2){
        return (float) Math.sqrt(Math.pow(point1.x-point2.x, 2)+Math.pow(point1.y-point2.y, 2));
    }
    /**
     * 设置当前计算的到的半径
     */
    private void setCurrentRadius() {
        //两个圆心之间的距离
        float distance = (float) Math.sqrt(Math.pow(pointStart.x - pointEnd.x, 2) + Math.pow(pointStart.y - pointEnd.y, 2));
        //拖拽距离在设置的最大值范围内才绘制贝塞尔图形
        if (distance <= maxDistance) {
            //比例系数  控制两圆半径缩放
            float percent = distance / maxDistance;

            //之所以*0.6和0.2只为了放置拖拽过程圆变化的过大和过小这个系数是多次尝试的出的
            //你也可以适当调整系数达到自己想要的效果
            currentRadiusStart = (1 - percent * 0.6f) * radiusStart;
          //  currentRadiusEnd = (1 + percent * 0.2f) * radiusEnd;

            isOutOfRang = false;
        } else {
            isOutOfRang = true;
            currentRadiusStart = radiusStart;
            currentRadiusEnd = radiusEnd;
        }
    }
    /**
     * 设置消息数
     *
     * @param count 消息个数
     */
    public void setMsgCount(String count) {
        if (TextUtils.isEmpty(count)){
            msgCount = 0 ;
        }else {
            msgCount = Integer.parseInt(count);
        }
        invalidate();
    }

    public void reset() {
        msgCount = 0;
        mIsCanDrag = false;
        isOutOfRang = false;
        disappear = false;
        pointStart.set(startX, startY);
        pointEnd.set(startX, startY);

        setABCDOPoint();
        invalidate();
    }

    public void setOnDragBallListener(OnDragBallListener onDragBallListener) {
        this.onDragBallListener = onDragBallListener;
    }
    private OnDragBallListener onDragBallListener;
    /**
     * 回调事件
     */
    public interface OnDragBallListener {
        void onDisappear();
    }
    /**
     * dp 2 px
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }
    /**
     * sp 2 px
     */
    protected int sp2px(int spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, getResources().getDisplayMetrics());
    }
    private Paint circlePaint;
    private Paint textPaint;

    private int circleColor = Color.RED;
    private float radiusStart;
    private float radiusEnd;

    private Path path;

    private int startX;
    private int startY;

    //是否可拖拽
    private boolean mIsCanDrag = false;
    //是否超过最大距离
    private boolean isOutOfRang = false;
    //最终圆是否消失
    private boolean disappear = false;

    //两圆相离最大距离
    private float maxDistance;

    //贝塞尔曲线需要的点
    private PointF pointA;
    private PointF pointB;
    private PointF pointC;
    private PointF pointD;
    //控制点坐标
    private PointF pointControl;

    //起始位置点
    private PointF pointStart;
    //拖拽位置点
    private PointF pointEnd;

    //根据滑动位置动态改变圆的半径
    private float currentRadiusStart;
    private float currentRadiusEnd;

    private Rect textRect = new Rect();
    private Rect rect= new Rect();
    //消息数
    private int msgCount = 100;
}
