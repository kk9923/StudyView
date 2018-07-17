package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.kx.studyview.R;
import com.kx.studyview.views.PayInPutView;

public class PayInPutViewActivity extends AppCompatActivity {

    private TextView mTv_psd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_in_put_view);
        PayInPutView payInPutView = findViewById(R.id.payInPutView);
        payInPutView.setOnInPutListener(new PayInPutView.onInPutListener() {
            @Override
            public void onInPut(String text) {
                mTv_psd.setText(text);
            }
        });

        mTv_psd = findViewById(R.id.tv_psd);


    }
}
