package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.kx.studyview.R;
import com.kx.studyview.views.QQBezierView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QQBezierViewActivity extends AppCompatActivity {

    @BindView(R.id.et_msg_num)
    EditText etMsgNum;
    @BindView(R.id.qqBezierView)
    QQBezierView qqBezierView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqbezier_view);
        ButterKnife.bind(this);
        qqBezierView.setOnDragBallListener(new QQBezierView.OnDragBallListener() {
            @Override
            public void onDisappear() {
               // qqBezierView.reset();
            }
        });
    }

    public void reset(View view) {
        qqBezierView.reset();
    }

    public void setMeg(View view) {
        qqBezierView.setMsgCount(etMsgNum.getText().toString().trim());
    }
}
