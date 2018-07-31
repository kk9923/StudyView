package com.kx.studyview.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.kx.studyview.R;

/**
 * Created by admin  on 2018/7/31.
 * 可折叠的TextView,展开全文功能
 */
public class ExpandableTextView  extends LinearLayout{
    /**
     * 最大的展开行数
     */
    private  int maxExpandLines ;
    /**
     * 默认的展开行数
     */
    private  int defaultExpandLines = 5 ;
    /**
     * 显示内容的TextView
     */
    private TextView mContentView;
    /**
     * 显示展开 收起 文字 的TextView
     */
    private TextView mExpandView;
    /**
     * 用于展开，收缩的判断
     */
    private boolean isExpend = false;

    public ExpandableTextView(Context context) {
        this(context,null);
    }
    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = context.obtainStyledAttributes(attrs,R.styleable.ExpandableTextView);
        maxExpandLines = ta.getInteger(R.styleable.ExpandableTextView_maxExpandLines,defaultExpandLines);
        ta.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获取内容TextView的显示行数
        mExpandView.setVisibility(GONE);
        mContentView.setMaxLines(Integer.MAX_VALUE);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int lineCount = mContentView.getLineCount();
        //  如果TextView的行数小于最大展开行数
        if (lineCount <= maxExpandLines ){
           return;
        }
            if (!isExpend){
                mContentView.setLines(maxExpandLines);
            }
        mExpandView.setVisibility(VISIBLE);
       super.onMeasure(widthMeasureSpec, heightMeasureSpec);


    }
    public void setText(String text){
        mContentView.setText(text);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mContentView = findViewById(R.id.content_view);
        mExpandView = findViewById(R.id.expend_view);
        mExpandView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isExpend = !isExpend;
                mExpandView.setText(isExpend ? "关闭":"展开");
                requestLayout();
            }
        });
    }
}
