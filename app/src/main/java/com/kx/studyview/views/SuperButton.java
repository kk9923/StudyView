package com.kx.studyview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
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
    private int defaultSelectorColor = 0x20000000;
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
    // 状态为pressed时的背景色
    private int selectorPressedColor;
    // 状态为disable时的背景色
    private int selectorDisableColor;
    // 状态为normal时的背景色
    private int selectorNormalColor;
    // 是否使用StateListDrawable作为背景
    private boolean useSelector;

    private int gradientOrientation;
    //渐变色的显示方式
    public static final int TOP_BOTTOM = 0;
    public static final int TR_BL = 1;
    public static final int RIGHT_LEFT = 2;
    public static final int BR_TL = 3;
    public static final int BOTTOM_TOP = 4;
    public static final int BL_TR = 5;
    public static final int LEFT_RIGHT = 6;
    public static final int TL_BR = 7;

    private int gradientAngle;
    private int gradientCenterX;
    private int gradientCenterY;
    private int gradientGradientRadius;
    //渐变色开始颜色
    private int gradientStartColor;
    //渐变色中间颜色
    private int gradientCenterColor;
    //渐变色结束颜色
    private int gradientEndColor;

    private int gradientType;

    //"linear"	线形渐变。这也是默认的模式
    private static final int linear = 0;
    //"radial"	辐射渐变。startColor即辐射中心的颜色
    private static final int radial = 1;
    //"sweep"	扫描线渐变。
    private static final int sweep = 2;

    private boolean gradientUseLevel;

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

        selectorPressedColor = ta.getColor(R.styleable.SuperButton_selectorPressedColor, defaultSelectorColor);
        selectorDisableColor = ta.getColor(R.styleable.SuperButton_selectorDisableColor, defaultSelectorColor);
        selectorNormalColor = ta.getColor(R.styleable.SuperButton_selectorNormalColor, defaultSelectorColor);
        useSelector = ta.getBoolean(R.styleable.SuperButton_useSelector, false);

        gradientOrientation = ta.getInt(R.styleable.SuperButton_gradientOrientation, -1);
        gradientAngle = ta.getDimensionPixelSize(R.styleable.SuperButton_gradientAngle, 0);
        gradientCenterX = ta.getDimensionPixelSize(R.styleable.SuperButton_gradientCenterX, 0);
        gradientCenterY = ta.getDimensionPixelSize(R.styleable.SuperButton_gradientCenterY, 0);
        gradientGradientRadius = ta.getDimensionPixelSize(R.styleable.SuperButton_gradientGradientRadius, 0);

        gradientStartColor = ta.getColor(R.styleable.SuperButton_gradientStartColor, -1);
        gradientCenterColor = ta.getColor(R.styleable.SuperButton_gradientCenterColor, -1);
        gradientEndColor = ta.getColor(R.styleable.SuperButton_gradientEndColor, -1);

        gradientType = ta.getInt(R.styleable.SuperButton_gradientType, linear);
        gradientUseLevel = ta.getBoolean(R.styleable.SuperButton_gradientUseLevel, false);
        ta.recycle();
        setClickable(true);
        setBackground(useSelector ? getSelector() :getDrawable(0));
    }
    public StateListDrawable getSelector() {
        StateListDrawable stateListDrawable = new StateListDrawable();

        //注意该处的顺序，只要有一个状态与之相配，背景就会被换掉
        //所以不要把大范围放在前面了，如果sd.addState(new[]{},normal)放在第一个的话，就没有什么效果了
        stateListDrawable.addState(new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, getDrawable(android.R.attr.state_pressed));
        stateListDrawable.addState(new int[]{-android.R.attr.state_enabled}, getDrawable(-android.R.attr.state_enabled));
        stateListDrawable.addState(new int[]{}, getDrawable(android.R.attr.state_enabled));

        return stateListDrawable;
    }

    private Drawable getDrawable(int selectorState) {
        mGradientDrawable =new GradientDrawable();
        setBackgroundShape();
        setRadius();
        setStroke();
        setSize();
        setOrientation();
        setSelectorColor(selectorState);
        return  mGradientDrawable ;
    }

    private void setSelectorColor(int selectorState) {
        if (gradientOrientation == -1){
            switch (selectorState) {
                     // 按下状态
                case android.R.attr.state_pressed:
                    mGradientDrawable.setColor(selectorPressedColor);
                    break;
                    //   diseEnabled状态
                case -android.R.attr.state_enabled:
                    mGradientDrawable.setColor(selectorDisableColor);
                    break;
                case android.R.attr.state_enabled:
                    // enabled  状态
                      mGradientDrawable.setColor(selectorNormalColor);
                    break;
            }
        }
    }

    /**
     * 设置背景颜色
     * 如果设定的有Orientation 就默认为是渐变色的Button，否则就是纯色的Button
     */
    private void setOrientation() {
        if (gradientOrientation != -1) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                // 设置渐变色的方向，默认八种。
                mGradientDrawable.setOrientation(getOrientation(gradientOrientation));

                if (gradientCenterColor == -1) {
                    mGradientDrawable.setColors(new int[]{gradientStartColor, gradientEndColor});
                } else {
                    mGradientDrawable.setColors(new int[]{gradientStartColor, gradientCenterColor, gradientEndColor});
                }

                switch (gradientType) {
                    case linear:
                        mGradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT);
                        break;
                    case radial:
                        mGradientDrawable.setGradientType(GradientDrawable.RADIAL_GRADIENT);
                        mGradientDrawable.setGradientRadius(gradientGradientRadius);

                        break;
                    case sweep:
                        mGradientDrawable.setGradientType(GradientDrawable.SWEEP_GRADIENT);
                        break;
                }
                mGradientDrawable.setUseLevel(gradientUseLevel);

                if (gradientCenterX != 0 && gradientCenterY != 0) {
                    mGradientDrawable.setGradientCenter(gradientCenterX, gradientCenterY);
                }

            }
        } else {
            mGradientDrawable.setColor(solidColor);
        }
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
    /**
     * 设置颜色渐变类型
     *
     * @param gradientOrientation gradientOrientation
     * @return Orientation
     */
    private GradientDrawable.Orientation getOrientation(int gradientOrientation) {
        GradientDrawable.Orientation orientation = null;
        switch (gradientOrientation) {
            case TOP_BOTTOM:
                orientation = GradientDrawable.Orientation.TOP_BOTTOM;
                break;
            case TR_BL:
                orientation = GradientDrawable.Orientation.TR_BL;
                break;
            case RIGHT_LEFT:
                orientation = GradientDrawable.Orientation.RIGHT_LEFT;
                break;
            case BR_TL:
                orientation = GradientDrawable.Orientation.BR_TL;
                break;
            case BOTTOM_TOP:
                orientation = GradientDrawable.Orientation.BOTTOM_TOP;
                break;
            case BL_TR:
                orientation = GradientDrawable.Orientation.BL_TR;
                break;
            case LEFT_RIGHT:
                orientation = GradientDrawable.Orientation.LEFT_RIGHT;
                break;
            case TL_BR:
                orientation = GradientDrawable.Orientation.TL_BR;
                break;
        }
        return orientation;
    }
    public  int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources().getDisplayMetrics());
    }
}
