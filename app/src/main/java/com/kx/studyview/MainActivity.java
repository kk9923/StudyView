package com.kx.studyview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kx.studyview.activity.ColorfulTextViewActivity;
import com.kx.studyview.activity.SuperButtonActivity;
import com.kx.studyview.activity.TabLayoutActivity;
import com.kx.studyview.activity.WaveViewActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.colorfulTextView).setOnClickListener(mClickListener);
        findViewById(R.id.waveView).setOnClickListener(mClickListener);
        findViewById(R.id.superButton).setOnClickListener(mClickListener);
        findViewById(R.id.tabLayout).setOnClickListener(mClickListener);
    }
    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.colorfulTextView:
                     startActivity(ColorfulTextViewActivity.class);
                break;
                case R.id.waveView:
                     startActivity(WaveViewActivity.class);
                break;
                case R.id.superButton:
                     startActivity(SuperButtonActivity.class);
                break;
                case R.id.tabLayout:
                    startActivity(TabLayoutActivity.class);
                    break;
            }
        }
    };


    public void startActivity(Class activityClass){
        Intent intent = new Intent(MainActivity.this,activityClass);
        startActivity(intent);
    }
}
