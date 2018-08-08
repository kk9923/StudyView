package com.kx.studyview.views;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.BounceInterpolator;

/**
 * Created by admin  on 2018/8/6.
 * 仿QQ消息红点拖拽效果view
 */
public class MyQQBezierView  extends View{
    private Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Path mPath;
    private Paint textPaint;
    public MyQQBezierView(Context context) {
        this(context,null);
    }
    public MyQQBezierView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public MyQQBezierView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        circlePaint.setColor(Color.RED);
        circlePaint.setAntiAlias(true);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mPath = new Path();

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(sp2px(13));
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        maxDistance = dp2px(100);
        fixedCircleStartRadius = dp2px(15);
        dragCircleRadius = dp2px(15);
        fixedCircleRadius = fixedCircleStartRadius ;
        mPath.addCircle(width/2,height/2,fixedCircleRadius, Path.Direction.CCW);
        mRegion.set(width/2 -(int)fixedCircleRadius ,height/2 - (int)fixedCircleRadius,width/2 +(int)fixedCircleRadius,height/2 +(int)fixedCircleRadius);
        mRegion.setPath(mPath,mRegion);
        fixedPoint.set(w/2,h/2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //  如果超出拖拽的最远距离，只绘制拖拽圆
        if (isOutOfRang){
            if ( !disappear){
                drawDragCircle(canvas);
            }
        }else {
            drawFixedCircle(canvas);
            if (isCanDrag){
                drawDragCircle(canvas);
                drawBezier(canvas);
            }
        }

        if (!disappear){
            if (msgCount >= 0) {
                if (dragPoint.x==0||dragPoint.y==0){
                    // 将文字绘制在固定圆上
                    drawText(canvas, msgCount, fixedPoint);
                }else {
                    // 将文字绘制在拖拽圆上
                    drawText(canvas, msgCount, dragPoint);
                }
            }
        }
    }

    private void drawText(Canvas canvas, int msgCount, PointF point) {
        Paint.FontMetrics fm = textPaint.getFontMetrics();
        String msg = msgCount > 99 ? "99+" : msgCount + "" ;
        float textWidth  = textPaint.measureText(msg);
        float baseLine = point.y - (fm.top+fm.bottom) /2 ;

        // TODO: 2018/8/8    textPaint.setTextAlign(Paint.Align.CENTER); 因为给textPaint 设置了 Paint.Align.CENTER
        // TODO: 2018/8/8    所以这里绘制文本时的x 值就不要 减去  textWidth 的 一半
        canvas.drawText(msg,point.x  ,baseLine,textPaint);
//        textPaint.setTextAlign(Paint.Align.LEFT);
//        canvas.drawText(msg,point.x -textWidth /2 ,baseLine,textPaint);
    }

    /**
     * 绘制贝塞尔曲线
     */
    private void drawBezier(Canvas canvas) {
        mPath.reset();
        mPath.moveTo(pointA.x,pointA.y);
        mPath.quadTo(pointControl.x,pointControl.y,pointC.x,pointC.y);
        mPath.lineTo(pointD.x,pointD.y);
        mPath.quadTo(pointControl.x,pointControl.y,pointB.x,pointB.y);
        mPath.close();
        canvas.drawPath(mPath,circlePaint);
    }

    /**
     * 绘制拖拽圆
     */
    private void drawDragCircle(Canvas canvas) {

        canvas.drawCircle(dragPoint.x,dragPoint.y,dragCircleRadius,circlePaint);
    }
    /**
     * 绘制固定圆
     */
    private void drawFixedCircle(Canvas canvas) {
        canvas.drawCircle(width /2 ,height/2 ,fixedCircleRadius,circlePaint);
    }
    private  float firstDownX,firstDownY;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN :
                isCanDrag = mRegion.contains((int) event.getX(), (int) event.getY());
                if (isCanDrag){
                    dragPoint.x = event.getX();
                    dragPoint.y = event.getY();
                    firstDownX =  dragPoint.x;
                    firstDownY =  dragPoint.y;
                }
                break;
            case MotionEvent.ACTION_MOVE :
                if (isCanDrag){
                    dragPoint.x = event.getX();
                    dragPoint.y = event.getY();
                    //  只有在没有超出最大移动距离的时候绘制 贝塞尔曲线
                    if (!isOutOfRang){
                        serFixCircleRadius();
                        setBezierPoint();
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP :
                    // 代表只有   DOWN  和UP事件， 没有MOVE 事件
                    if (firstDownX == dragPoint.x && firstDownY == dragPoint.y){
                        return true;
                    }
                    if (isCanDrag ){
                        if (isOutOfRang){
                            disappear = true;
                            if (mDragBallListener!=null){
                                mDragBallListener.onDisappear();
                            }
                            invalidate();
                        }else {
                            disappear = false;
                            // 松手的时两圆心的距离小于最大距离， 添加回弹动画
                            // 松手时， 两圆心的连线 和 x 轴 的 夹角 的正切值
                            float angle = (dragPoint.y- fixedPoint.y)/(dragPoint.x-fixedPoint.x);
                            // 根据  x  值来添加动画
                            ValueAnimator valueAnimator = ValueAnimator.ofFloat(dragPoint.x, fixedPoint.x);
                            valueAnimator.setDuration(500);
                            valueAnimator.setInterpolator(new BounceInterpolator());
                            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                @Override
                                public void onAnimationUpdate(ValueAnimator animation) {
                                    //   根据数学知识算出动画过程中  拖拽圆  圆心的坐标
                                    float x = (float) animation.getAnimatedValue();
                                    float y =   (x - fixedPoint.x) * angle + fixedPoint.y ;

                                    dragPoint.set(x, y);
                                    serFixCircleRadius();
                                    setBezierPoint();
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
     * 通过拖拽圆的拖拽距离比例来设置固定圆的半径
     */
    private void serFixCircleRadius() {
        //两个圆心之间的距离
        float distance = (float) Math.sqrt(Math.pow(fixedPoint.x - dragPoint.x, 2) + Math.pow(fixedPoint.y - dragPoint.y, 2));
        if (distance <= maxDistance){
            float percent = distance / maxDistance;
            fixedCircleRadius = (1- percent*0.6f ) * fixedCircleStartRadius;

            isOutOfRang = false;
        }else {
            isOutOfRang = true;
        }
    }

    /**
     * 设置四个起始点和控制点的坐标
     */
    private void setBezierPoint() {
        //控制点坐标
        pointControl.set((fixedPoint.x + dragPoint.x) / 2.0f, (fixedPoint.y + dragPoint.y) / 2.0f);

        float angle = (float) Math.atan((fixedPoint.y-dragPoint.y)/(dragPoint.x-fixedPoint.x));

        pointA.x = (float) (fixedPoint.x - fixedCircleRadius* Math.sin(angle));
        pointA.y = (float) (fixedPoint.y - fixedCircleRadius* Math.cos(angle));

        pointB.x = (float) (fixedPoint.x + fixedCircleRadius*Math.sin(angle));
        pointB.y = (float) (fixedPoint.y + fixedCircleRadius*Math.cos(angle));

        pointC.x = (float) (dragPoint.x - dragCircleRadius*Math.sin(angle));
        pointC.y = (float) (dragPoint.y - dragCircleRadius*Math.cos(angle));

        pointD.x = (float) (dragPoint.x + dragCircleRadius*Math.sin(angle));
        pointD.y = (float) (dragPoint.y + dragCircleRadius*Math.cos(angle));
    }
    public void reset() {
        msgCount = 0;
        isCanDrag = false;
        isOutOfRang = false;
        disappear = false;
        fixedPoint.set(width/2, height/2);
        dragPoint.set(width/2, height/2);
        fixedCircleRadius = fixedCircleStartRadius;
        setBezierPoint();
        invalidate();
    }
    // fixed    固定
    // drag     拖拽
    private PointF pointA = new PointF();
    private PointF pointB = new PointF();
    private PointF pointC = new PointF();
    private PointF pointD = new PointF();
    // 固定圆圆心
    private PointF fixedPoint = new PointF();
    // 拖拽圆圆心
    private PointF dragPoint = new PointF();
    //  控制点
    private PointF pointControl = new PointF();
    //是否可拖拽
    private boolean isCanDrag = false;
    //是否超过最大距离
    private boolean isOutOfRang = false;
    //拖拽圆是否消失
    private boolean disappear = false;
    //两圆相离最大距离
    private float maxDistance;
    //  固定圆半径
    private  float fixedCircleRadius ;
    private float fixedCircleStartRadius;
    //  拖拽圆半径
    private  float dragCircleRadius ;
    private int width ,height ;
    Region mRegion = new Region();
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
    /**
     * 得到两个点之间的距离
     */
    private float getTwoPointDistance(PointF point1, PointF point2){
        return (float) Math.sqrt(Math.pow(point1.x-point2.x, 2)+Math.pow(point1.y-point2.y, 2));
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
    //消息数
    private int msgCount = 100;
    public interface  OnDragBallListener {
       void onDisappear();
    }
    OnDragBallListener mDragBallListener;

    public void setOnDragBallListener(OnDragBallListener dragBallListener) {
        mDragBallListener = dragBallListener;
    }
}