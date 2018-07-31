package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.kx.studyview.R;
import com.kx.studyview.views.ExpandableTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExpandableTextViewActivity extends AppCompatActivity {

    @BindView(R.id.expandableTextView)
    ExpandableTextView expandableTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_text_view);
        ButterKnife.bind(this);
       // expandableTextView.setText("我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容我是内容");
    }
}
