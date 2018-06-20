package com.kx.studyview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * Created by admin on 2018/6/20.
 */

public abstract class BaseActivity  extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getRootLayoutIds());
        ButterKnife.bind(this);
        initData();
    }

    protected abstract void initData();

    public abstract  int getRootLayoutIds();
}
