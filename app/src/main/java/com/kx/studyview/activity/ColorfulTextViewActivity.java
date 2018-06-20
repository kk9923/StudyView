package com.kx.studyview.activity;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.kx.studyview.BaseActivity;
import com.kx.studyview.R;

import butterknife.BindView;


public class ColorfulTextViewActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.foregroundColorSpan)
    Button mForegroundColorSpan;
    @BindView(R.id.backgroundColorSpan)
    Button mBackgroundColorSpan;
    @BindView(R.id.relativeSizeSpan)
    Button mRelativeSizeSpan;
    @BindView(R.id.strikethroughSpan)
    Button mStrikethroughSpan;
    @BindView(R.id.underlineSpan)
    Button mUnderlineSpan;
    @BindView(R.id.superscriptSpan)
    Button mSuperscriptSpan;
    @BindView(R.id.subscriptSpan)
    Button mSubscriptSpan;
    @BindView(R.id.styleSpan)
    Button mStyleSpan;
    @BindView(R.id.imageSpan)
    Button mImageSpan;
    @BindView(R.id.clickableSpan)
    Button mClickableSpan;
    @BindView(R.id.uRLSpan)
    Button mURLSpan;
    @BindView(R.id.spannableStringBuilder)
    Button mSpannableStringBuilder;
    @BindView(R.id.content)
    TextView mContent;

    @Override
    public int getRootLayoutIds() {
        return R.layout.activity_colorful_text_view;
    }

    @Override
    protected void initData() {
        mForegroundColorSpan.setOnClickListener(this);
        mBackgroundColorSpan.setOnClickListener(this);
        mRelativeSizeSpan.setOnClickListener(this);
        mStrikethroughSpan.setOnClickListener(this);
        mUnderlineSpan.setOnClickListener(this);
        mSuperscriptSpan.setOnClickListener(this);
        mSubscriptSpan.setOnClickListener(this);
        mStyleSpan.setOnClickListener(this);
        mImageSpan.setOnClickListener(this);
        mClickableSpan.setOnClickListener(this);
        mURLSpan.setOnClickListener(this);
        mSpannableStringBuilder.setOnClickListener(this);
        mContent.setOnClickListener(this);
        mContent.setText("设置文字的前景色为淡粉色");
    }
    String text = "设置文字的前景色为淡粉色";
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.foregroundColorSpan:
                System.out.println(text.length());
                SpannableString spannableString = new SpannableString("设置文字的前景色为淡粉色");
                ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorAccent));
                ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorPrimary));
                spannableString.setSpan(foregroundColorSpan1,9,text.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(foregroundColorSpan2,1,3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                mContent.setText(spannableString);
                break;
            case R.id.backgroundColorSpan:
                    mContent.append("新添加的数据");
                break;
            case R.id.relativeSizeSpan:

                break;
            case R.id.strikethroughSpan:

                break;
            case R.id.underlineSpan:

                break;
            case R.id.superscriptSpan:

                break;
            case R.id.subscriptSpan:

                break;
            case R.id.styleSpan:

                break;
            case R.id.imageSpan:

                break;
            case R.id.clickableSpan:

                break;
            case R.id.uRLSpan:

                break;
            case R.id.spannableStringBuilder:

                break;
        }
    }
}
