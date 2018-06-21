package com.kx.studyview.activity;


import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
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
        SpannableString spannableString  ;
        switch (v.getId()){
            case R.id.foregroundColorSpan:
                text = "设置文字的前景色为淡粉色";
                spannableString  = new SpannableString(text);
                ForegroundColorSpan foregroundColorSpan1 = new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorAccent));
                ForegroundColorSpan foregroundColorSpan2 = new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorPrimary));
                spannableString.setSpan(foregroundColorSpan1,9,text.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(foregroundColorSpan2,1,3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                ClickableSpan clickableSpan  = new ClickableSpan() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("我是点击的内容" + ( view instanceof TextView ));
                    }

                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setUnderlineText(false);
                    }
                };
                spannableString.setSpan(clickableSpan, 1 ,3 ,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                mContent.setMovementMethod(LinkMovementMethod.getInstance());
                mContent.setText(spannableString);
                break;
            case R.id.backgroundColorSpan:
                text = "设置文字的背景色为淡绿色";
                spannableString  = new SpannableString(text);
                BackgroundColorSpan colorSpan = new BackgroundColorSpan(Color.parseColor("#AC00FF30"));
                spannableString.setSpan(colorSpan,9,text.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                mContent.setText(spannableString);
                break;
            case R.id.relativeSizeSpan:
                text = "设置文字相对大小";
                spannableString  = new SpannableString(text);
                RelativeSizeSpan sizeSpan01 = new RelativeSizeSpan(1.2f);
                RelativeSizeSpan sizeSpan02 = new RelativeSizeSpan(1.5f);
                RelativeSizeSpan sizeSpan03 = new RelativeSizeSpan(1.8f);
                RelativeSizeSpan sizeSpan04 = new RelativeSizeSpan(2.0f);
                spannableString.setSpan(sizeSpan01,0,1,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(sizeSpan02,1,2,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(sizeSpan03,4,5,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(sizeSpan04,6,text.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                mContent.setText(spannableString);
                break;
            case R.id.strikethroughSpan:
                text = "文本设置中删除线";
                spannableString  = new SpannableString(text);
                StrikethroughSpan strikethroughSpan1 = new StrikethroughSpan();
                StrikethroughSpan strikethroughSpan2= new StrikethroughSpan();
                StrikethroughSpan strikethroughSpan3 = new StrikethroughSpan();
                spannableString.setSpan(strikethroughSpan1,0,1,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(strikethroughSpan2,2,3,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(strikethroughSpan3,5,text.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                mContent.setText(spannableString);
                break;
            case R.id.underlineSpan:
                text = "文本设置中下划线";
                spannableString  = new SpannableString(text);
                UnderlineSpan underlineSpan1 = new UnderlineSpan();
                UnderlineSpan underlineSpan2 = new UnderlineSpan();
                UnderlineSpan underlineSpan3= new UnderlineSpan();
                spannableString.setSpan(underlineSpan1,0,1,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(underlineSpan2,2,4,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(underlineSpan3,5,text.length(),Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                mContent.setText(spannableString);
                break;
            case R.id.superscriptSpan:
               // text = "为文字设置上标";
                // 结合 SuperscriptSpan 和 RelativeSizeSpan , 展示数据公式 2^2+3^2=13
               text = "22+32=13";
                spannableString  = new SpannableString(text);
                SuperscriptSpan superscriptSpan1 = new SuperscriptSpan();
                SuperscriptSpan superscriptSpan2 = new SuperscriptSpan();
               // spannableString.setSpan(underlineSpan1,0,1,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
               // spannableString.setSpan(underlineSpan2,2,4,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(superscriptSpan1,1,2,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(superscriptSpan2,4,5,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                RelativeSizeSpan relativeSizeSpan1 = new RelativeSizeSpan(0.7f);
                RelativeSizeSpan relativeSizeSpan2 = new RelativeSizeSpan(0.7f);
                spannableString.setSpan(relativeSizeSpan1,1,2,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(relativeSizeSpan2,4,5,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                mContent.setText(spannableString);
                break;
            case R.id.subscriptSpan:
                // text = "为文字设置下标";
                // 结合 SuperscriptSpan 和 RelativeSizeSpan , 展示数据公式 2^2+3^2=13
                text = "22+32=13";
                spannableString  = new SpannableString(text);
                SubscriptSpan subscriptSpan1 = new SubscriptSpan();
                SubscriptSpan subscriptSpan2 = new SubscriptSpan();
                // spannableString.setSpan(underlineSpan1,0,1,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                // spannableString.setSpan(underlineSpan2,2,4,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(subscriptSpan1,1,2,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(subscriptSpan2,4,5,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                RelativeSizeSpan relativeSizeSpan3 = new RelativeSizeSpan(0.7f);
                RelativeSizeSpan relativeSizeSpan4 = new RelativeSizeSpan(0.7f);
                spannableString.setSpan(relativeSizeSpan3,1,2,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(relativeSizeSpan4,4,5,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                mContent.setText(spannableString);
                break;
            case R.id.styleSpan:
                text = "为文字设置粗体、斜体、粗斜体风格";
                spannableString  = new SpannableString(text);
                StyleSpan styleSpan_B  = new StyleSpan(Typeface.BOLD);    // 粗体，
                StyleSpan styleSpan_I  = new StyleSpan(Typeface.ITALIC);  // 斜体
                StyleSpan styleSpan_B_I  = new StyleSpan(Typeface.BOLD_ITALIC);  // 粗体 + 斜体

                spannableString.setSpan(styleSpan_B, 5, 7, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(styleSpan_I, 8, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(styleSpan_B_I, 11, 14, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

                mContent.setText(spannableString);
                break;
            case R.id.imageSpan:
                text = " 在文本中添加表情(表情)";
                spannableString  = new SpannableString(text);
                Drawable drawable = getResources().getDrawable(R.drawable.qq);
               drawable.setBounds(0,0,60,60);
                ImageSpan imageSpan = new ImageSpan(drawable);
                spannableString.setSpan(imageSpan,7,9,Spanned.SPAN_INCLUSIVE_INCLUSIVE);
                mContent.setText(spannableString);

                break;
            case R.id.clickableSpan:
                text = "我是可点击的文本、超链接";
                spannableString  = new SpannableString(text);
                ForegroundColorSpan foregroundColorSpan3 = new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorAccent));
                ForegroundColorSpan foregroundColorSpan4 = new ForegroundColorSpan(ContextCompat.getColor(this,R.color.colorPrimary));
                spannableString.setSpan(foregroundColorSpan3,9,spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableString.setSpan(foregroundColorSpan4,1,3, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

                ClickableSpan clickableSpan2  = new ClickableSpan() {
                    //  点击事件的处理
                    @Override
                    public void onClick(View view) {
                        TextView textView = (TextView) view;
                        System.out.println("我是点击的内容      " + ( textView.getText()) );
                    }
                     // 去掉下划线
                    @Override
                    public void updateDrawState(TextPaint ds) {
                        ds.setUnderlineText(false);
                    }
                };
                spannableString.setSpan(clickableSpan2, 1 ,3 ,Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                mContent.setMovementMethod(LinkMovementMethod.getInstance());
                mContent.setHighlightColor(Color.parseColor("#36969696"));
                mContent.setText(spannableString);
                break;
            case R.id.uRLSpan:
                text = "我是可点击的超链接";
                spannableString  = new SpannableString(text);
                URLSpan urlSpan = new URLSpan("http://www.jianshu.com/users/dbae9ac95c78");
                spannableString.setSpan(urlSpan,6,text.length(),Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
                mContent.setMovementMethod(LinkMovementMethod.getInstance());
                mContent.setText(spannableString);
                break;
            case R.id.spannableStringBuilder:

                break;
        }
    }
}
