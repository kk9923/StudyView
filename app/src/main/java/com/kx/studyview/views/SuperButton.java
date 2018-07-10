package com.kx.studyview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.kx.studyview.R;

/**
 * Created by admin on 2018/7/10.
 */
public class SuperButton extends android.support.v7.widget.AppCompatButton {
    private Context mContext ;
    private GradientDrawable mGradientDrawable ;
    private int defaultColor = 0x20000000;

    //shape的四种样式
    public static final int RECTANGLE = 0;
    public static final int OVAL = 1;
    public static final int LINE = 2;
    public static final int RING = 3;
    private int shapeType = RECTANGLE;
    // 填充色
    private int solidColor;
    // 矩形Shape下，四个角的圆角程度
    private float cornersRadius;
    // 左上角角度
    private float cornersTopLeftRadius;
    // 右上角角度
    private float cornersTopRightRadius;
    // 左下角角度
    private float cornersBottomLeftRadius;
    // 右下角角度
    private float cornersBottomRightRadius;
    //  描边的宽度
    private int  strokeWidth ;
    //  描边的颜色
    private int  strokeColor ;
    //  描边的虚线线段的宽度
    private float  strokeDashWidth ;
    //  描边的虚线线段之间的间隔
    private float  strokeDashGap ;
    //  shape的宽和高
    private int sizeWidth;
    private int sizeHeight;

    public SuperButton(Context context) {
        this(context,null);
    }
    public SuperButton(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SuperButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context ;
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.SuperButton);
        shapeType = ta.getInt(R.styleable.SuperButton_shape, GradientDrawable.RECTANGLE);
        solidColor = ta.getColor(R.styleable.SuperButton_solidColor, defaultColor);
        cornersRadius = ta.getDimensionPixelSize(R.styleable.SuperButton_cornersRadius, 0);
        cornersTopLeftRadius = ta.getDimensionPixelSize(R.styleable.SuperButton_topLeftRadius, 0);
        cornersTopRightRadius = ta.getDimensionPixelSize(R.styleable.SuperButton_topRightRadius, 0);
        cornersBottomLeftRadius = ta.getDimensionPixelSize(R.styleable.SuperButton_bottomLeftRadius, 0);
        cornersBottomRightRadius = ta.getDimensionPixelSize(R.styleable.SuperButton_bottomRightRadius, 0);

        strokeWidth = ta.getDimensionPixelSize(R.styleable.SuperButton_strokeWidth,0);
        strokeDashWidth = ta.getDimensionPixelSize(R.styleable.SuperButton_strokeDashWidth, 0);
        strokeDashGap = ta.getDimensionPixelSize(R.styleable.SuperButton_strokeDashGap, 0);
        strokeColor = ta.getColor(R.styleable.SuperButton_strokeColor, defaultColor);

        sizeWidth = ta.getDimensionPixelSize(R.styleable.SuperButton_sizeWidth, 0);
        sizeHeight = ta.getDimensionPixelSize(R.styleable.SuperButton_sizeHeight, dp2px(48));

        ta.recycle();
        setBackgroundShape();
        setRadius();
        setStroke();
        setSize();
        setBackground(mGradientDrawable);
    }

    /**
     * 设置描边信息
     */
    private void setStroke() {
        mGradientDrawable.setStroke(strokeWidth,strokeColor,strokeDashWidth,strokeDashGap);
    }

    /**
     * 设置背景图形形状
     */
    private void setBackgroundShape() {
        mGradientDrawable =new GradientDrawable();
        switch (shapeType){
            case RECTANGLE:
                mGradientDrawable.setShape(GradientDrawable.RECTANGLE);
                break;
            case OVAL:
                mGradientDrawable.setShape(GradientDrawable.OVAL);
                break;
            case LINE:
                mGradientDrawable.setShape(GradientDrawable.LINE);
                break;
            case RING:
                mGradientDrawable.setShape(GradientDrawable.RING);
                break;
        }
        mGradientDrawable.setColor(solidColor);
    }

    /**
     * 设置四个角的角度，只有在shape为矩形时有效
     */
    private void setRadius() {
        if (shapeType == GradientDrawable.RECTANGLE) {
            if (cornersRadius >0 ){
                 mGradientDrawable.setCornerRadius(cornersRadius);
            }else {
                mGradientDrawable.setCornerRadii(
                        new float[]
                                {
                                        cornersTopLeftRadius, cornersTopLeftRadius,
                                        cornersTopRightRadius, cornersTopRightRadius,
                                        cornersBottomRightRadius, cornersBottomRightRadius,
                                        cornersBottomLeftRadius, cornersBottomLeftRadius
                                }
                );
            }
        }
    }
    private void setSize() {
        if (shapeType == RECTANGLE) {
            mGradientDrawable.setSize(sizeWidth, sizeHeight);
        }
    }
    public  int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }
}
