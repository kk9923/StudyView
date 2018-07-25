package com.kx.studyview.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kx.studyview.R;
import com.kx.studyview.views.DiffuseView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GradientActivity extends AppCompatActivity {

    @BindView(R.id.diffuseView)
    DiffuseView diffuseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient);
        ButterKnife.bind(this);
    }

    public void start(View view) {
        diffuseView.start();
    }

    public void stop(View view) {
        diffuseView.stop();
    }
}
